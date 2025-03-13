package com.example.meteotrip.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${gemini.api.url:https://generativelanguage.googleapis.com}")
    private String geminiUrl;

    @Bean
    public WebClient webClient(WebClient.Builder builder){
        return builder.baseUrl(geminiUrl).build();
    }
}
