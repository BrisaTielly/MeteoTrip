package com.example.meteotrip.controller;

import com.example.meteotrip.dto.CityDto;
import com.example.meteotrip.service.CityService;
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
    public CityDto save(@RequestBody CityDto cityDto) {
        return service.save(cityDto);
    }

    @GetMapping("/list")
    public List<CityDto> list() {
        return service.findAll();
    }

    @GetMapping("/list/{id}")
    public CityDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/update/{id}")
    public CityDto update(@PathVariable Long id, @RequestBody CityDto cityDto) {
        return service.update(id, cityDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
