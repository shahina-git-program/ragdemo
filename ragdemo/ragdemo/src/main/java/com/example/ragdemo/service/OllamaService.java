package com.example.ragdemo.service;

import com.example.ragdemo.dto.OllamaRequest;
import com.example.ragdemo.dto.OllamaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OllamaService {

    @Value("${ollama.base-url}")
    private String baseUrl;

    @Value("${ollama.model}")
    private String model;

    @Autowired
    private RestTemplate restTemplate;

    public String generateResponse(String prompt) {

        String url = baseUrl + "/api/generate";

        OllamaRequest request = new OllamaRequest(model, prompt, false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OllamaRequest> entity =
                new HttpEntity<>(request, headers);

        ResponseEntity<OllamaResponse> response =
                restTemplate.postForEntity(
                        url,
                        entity,
                        OllamaResponse.class);

        return response.getBody().getResponse();
    }
}