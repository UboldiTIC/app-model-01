package com.model.api.web.controller;

import com.model.api.domain.service.ModelAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final String platform;
    private final ModelAIService aiService;

    @Autowired
    public HelloController(@Value("${spring.application.name}") String platform, ModelAIService aiService) {
        this.platform = platform;
        this.aiService = aiService;
    }

    @GetMapping("/hello")
    public String hello() {
        return this.aiService.generatedGreeting(platform);
    }
}
