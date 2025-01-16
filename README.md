# KBeauty-Ecommerce
This project is called "Korean Wave" and it is an e-commerce website that allows users to explore and shop for beauty and skincare products. 
It features a user-friendly interface with authentication, dynamic cart and wishlist management, role-based access (for users and admins) and a chatbot which uses information from a skincare related PDF. 
Details about the technologies used:

Backend:
    Java: The core programming language used to implement the application.
    Spring Framework: Specifically, Spring Boot for rapid development of RESTful and MVC-based applications.
    MariaDB: The database is used to store orders, users etc.
Frontend:
    Thymeleaf: A server-side Java templating engine for creating dynamic web pages.
    HTML5 and CSS3: For structuring and styling the web pages, with modern features like responsive design.
Security:
    BCrypt Password Encoding: To securely store user passwords.
    Role-Based Access Control (RBAC): Defines roles like USER and ADMIN to restrict access to specific pages and features.
Features and Functionality:
    Dynamic Data Management: Managed using Spring Boot and Spring Data JPA to handle entities like Product, User and Order.
    Session Management: Ensures cart and wishlist data persist throughout the user session.
    Validation: Includes server-side validation for registration (e.g., email format).
Development Tools:
    Maven: Used as the build automation tool to manage dependencies and simplify project configuration.
    IntelliJ IDEA: The primary IDE for coding and debugging.
Chatbot technologies:
    Streamlit: For creating the interactive user interface.
    LangChain: For document loading, text splitting and retrieval processes.
    Transformers: For leveraging pre-trained NLP models like Flan-T5 and RoBERTa for summarization and QA tasks.
    HuggingFace Sentence Transformers: For embedding the document chunks and enabling semantic search.
    Chroma: For storing and retrieving document embeddings efficiently.
