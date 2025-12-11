package com.model.api.web.controller;

import com.model.api.domain.service.ModelAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Greetings", description = "Welcome to the Model API.")
public class HelloController {
    private final String platform;
    private final ModelAIService aiService;

    @Autowired
    public HelloController(@Value("${spring.application.name}") String platform, ModelAIService aiService) {
        this.platform = platform;
        this.aiService = aiService;
    }

    @GetMapping("/hello")
    @Operation(
            summary = "AI-powered welcome.",
            description = "This endpoint provides an AI-generated greeting for the user.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The greeting was generated successfully."),
                    @ApiResponse(responseCode = "404", description = "The greeting could not be generated; please try again.")
            }
    )
    public String hello() {
        return this.aiService.generatedGreeting(platform);
    }
}
