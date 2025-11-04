package com.model.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final ModelAIService aiService;

    @Autowired
    public HelloController(ModelAIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/")
    public String hello() {
        return this.aiService.generatedGreeting();
    }
}
