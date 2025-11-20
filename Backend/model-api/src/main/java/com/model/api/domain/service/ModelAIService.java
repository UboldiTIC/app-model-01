package com.model.api.domain.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface ModelAIService {

    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma de prueba {{platform}}.
            Usa menos de 120 caracteres y hazlo con el estilo informal.
            """)
    String generatedGreeting(@V("platform")String platform);

    @SystemMessage("""
            Eres un experto en cine que recomienda películas personalizadas según los gustos del usuariio.
            Debes recomendar máximo 3 películas.
            No incluyas películas que estén por fuera de la plataforma ModelApi.
            """)
    String generateMoviesSuggestion(@UserMessage String userMessage);
}
