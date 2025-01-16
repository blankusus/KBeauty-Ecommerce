import streamlit as st
from langchain.document_loaders import PyPDFLoader
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain.vectorstores import Chroma
from langchain.embeddings import HuggingFaceEmbeddings
from transformers import pipeline, AutoTokenizer, AutoModelForSeq2SeqLM
import re


def load_and_split_pdf(pdf_path):
    loader = PyPDFLoader(pdf_path)
    documents = loader.load()
    text_splitter = RecursiveCharacterTextSplitter(chunk_size=500, chunk_overlap=200)
    return text_splitter.split_documents(documents)


def preprocess_chunks(chunks):
    cleaned_chunks = []
    for chunk in chunks:
        # Clean and normalize text
        cleaned_text = re.sub(r"\s+", " ", chunk.page_content).strip()
        cleaned_chunks.append(cleaned_text)
    return " ".join(cleaned_chunks)


def retrieve_and_summarize(question, retriever, tokenizer, summarizer_model, num_chunks=7):
    docs = retriever.get_relevant_documents(question)[:num_chunks]
    cleaned_context = preprocess_chunks(docs)

    if not cleaned_context.strip():
        return "No relevant information found."

    inputs = tokenizer.encode("Summarize: " + cleaned_context, return_tensors="pt", max_length=2048, truncation=True)
    summary_ids = summarizer_model.generate(inputs, max_length=300, min_length=150, length_penalty=2.0, num_beams=4,
                                            early_stopping=True)
    return tokenizer.decode(summary_ids[0], skip_special_tokens=True)


def generate_answer(question, context, qa_pipeline):
    if not context.strip():
        return "I couldn't generate an answer due to insufficient context."

    try:
        response = qa_pipeline(question=question, context=context)
        answer = response.get("answer", "I couldn't generate a meaningful answer.")
        return answer if answer.strip() else "I couldn't generate a meaningful answer."
    except Exception as e:
        return f"Error during QA: {e}"


st.title("Skincare Assistant")

pdf_path = ""  # Replace with your file path
if "retriever" not in st.session_state:
    splits = load_and_split_pdf(pdf_path)
    embedding = HuggingFaceEmbeddings(model_name="sentence-transformers/all-MiniLM-L6-v2")
    vectorstore = Chroma.from_documents(documents=splits, embedding=embedding)
    st.session_state.retriever = vectorstore.as_retriever()

    tokenizer = AutoTokenizer.from_pretrained("google/flan-t5-large")
    summarizer_model = AutoModelForSeq2SeqLM.from_pretrained("google/flan-t5-large")
    st.session_state.tokenizer = tokenizer
    st.session_state.summarizer_model = summarizer_model
    st.session_state.qa_pipeline = pipeline("question-answering", model="deepset/roberta-large-squad2")

if "messages" not in st.session_state:
    st.session_state.messages = []

for message in st.session_state.messages:
    with st.chat_message(message["role"]):
        st.markdown(message["content"])

if prompt := st.chat_input("Ask your skincare question:"):
    st.session_state.messages.append({"role": "user", "content": prompt})
    with st.chat_message("user"):
        st.markdown(prompt)

    with st.chat_message("assistant"):
        retriever = st.session_state.retriever
        tokenizer = st.session_state.tokenizer
        summarizer_model = st.session_state.summarizer_model
        qa_pipeline = st.session_state.qa_pipeline

        raw_context = retrieve_and_summarize(prompt, retriever, tokenizer, summarizer_model)

        answer = generate_answer(prompt, raw_context, qa_pipeline)

        formatted_answer = f"**Question:** {prompt}\n\n**Answer:** {answer}\n\n**Context Used:**\n{raw_context}"
        st.markdown(formatted_answer)
        st.session_state.messages.append({"role": "assistant", "content": formatted_answer})
