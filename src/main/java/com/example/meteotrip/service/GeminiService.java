package com.example.meteotrip.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.meteotrip.config.WebClientConfig;
import com.example.meteotrip.model.City;

import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {
    private WebClient webClient;

    public GeminiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> travelItinerary(City city){
        String cityString = String.format("Cidade: %s - Estado: %s - Tipo da Viagem: %s",
                city.getName(), city.getState(), city.getTripType());
        String prompt = "Baseado na seguinte cidade e estado, sugira uma lista de lugares para visitar de acordo com o tipo da viagem" + cityString;
        Map<String, Object> requestBody = Map.of("contents",
                List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var candidates = (List<Map<String, Object>>) response.get("candidates");

                    if (candidates != null && !candidates.isEmpty()) {
                        var content = (Map<String, Object>) candidates.get(0).get("content");
                        var parts = (List<Map<String, Object>>) content.get("parts");

                        if (parts != null && !parts.isEmpty()) {
                            var text = parts.get(0).get("text").toString();
                            return text;
                        }
                    }
                    return "Nenhuma recomendação gerada.";
                });
    }
}
