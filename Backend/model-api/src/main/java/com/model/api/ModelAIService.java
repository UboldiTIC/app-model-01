package com.model.api;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface ModelAIService {

    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma Model Api.
            Usa menos de 120 caracteres y hazlo con el estilo informal.
            """)
    String generatedGreeting();
}
