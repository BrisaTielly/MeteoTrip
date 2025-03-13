package com.example.meteotrip.controller;

import com.example.meteotrip.dto.CityDto;
import com.example.meteotrip.mapper.CityMapper;
import com.example.meteotrip.model.City;
import com.example.meteotrip.service.CityService;
import com.example.meteotrip.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/travel")
public class TravelItineraryController {
    private final GeminiService geminiService;
    private final CityService cityService;

    public TravelItineraryController(GeminiService geminiService, CityService cityService) {
        this.geminiService = geminiService;
        this.cityService = cityService;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> travelItinerary() {
        CityDto byId = cityService.findById(1L);
        City cityMapper = CityMapper.mapToCity(byId);
        return geminiService.travelItinerary(cityMapper)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
