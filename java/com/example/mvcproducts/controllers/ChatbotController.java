package com.example.mvcproducts.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class ChatbotController {
        @GetMapping("/chatbot")
        public String chatbotPage() {
            return "chatbot";
        }
    }
