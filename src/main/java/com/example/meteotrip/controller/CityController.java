package com.example.meteotrip.controller;

import com.example.meteotrip.dto.CityDto;
import com.example.meteotrip.mapper.CityMapper;
import com.example.meteotrip.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {
    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<CityDto> save(@RequestBody CityDto cityDto) {
        CityDto savedCity = service.save(cityDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCity);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list() {
        List<CityDto> cities = service.findAll();
        if (cities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma cidade cadastrada.");
        }
        return ResponseEntity.ok(cities);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        CityDto cityDto = service.findById(id);
        if (cityDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade com ID " + id + " não encontrada.");
        }
        return ResponseEntity.ok(cityDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CityDto cityDto) {
        CityDto existingCity = service.findById(id);
        if (existingCity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade com ID " + id + " não encontrada.");
        }
        CityDto updatedCity = service.update(id, cityDto);
        return ResponseEntity.ok(updatedCity);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        CityDto cityDto = service.findById(id);
        if (cityDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cidade com ID " + id + " não encontrada.");
        }
        service.delete(id);
        return ResponseEntity.ok("Cidade com ID " + id + " deletada com sucesso.");
    }
}
