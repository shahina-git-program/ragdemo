package com.example.ragdemo.controller;

import com.example.ragdemo.dto.ChatRequest;
import com.example.ragdemo.dto.ChatResponse;
import com.example.ragdemo.service.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AIController {

    @Autowired
    private OllamaService service;

    @GetMapping("/ask")
    public String ask(@RequestParam String q) {
        return service.generateResponse(q);
    }

    @GetMapping("/askjson")
    public Map<String, String> askJson(@RequestParam String q) {
        return Map.of(
                "question", q,
                "answer", service.generateResponse(q)
        );
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {

        String answer = service.generateResponse(request.getMessage());

        return new ChatResponse(answer);
    }
}