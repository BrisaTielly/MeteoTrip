package com.example.meteotrip.service;

import com.example.meteotrip.dto.CityDto;
import com.example.meteotrip.mapper.CityMapper;
import com.example.meteotrip.model.City;
import com.example.meteotrip.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public CityDto save(CityDto cityDto) {
        City city = CityMapper.mapToCity(cityDto);
        city = repository.save(city);
        return CityMapper.mapToDto(city);
    }

    public List<CityDto> findAll() {
        return repository.findAll().stream()
                .map(CityMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public CityDto findById(Long id) {
        return CityMapper.mapToDto(repository.findById(id).orElse(null));
    }

    public CityDto update(Long id, CityDto cityDto) {
        City city = repository.findById(id).orElse(null);
        //Permitir alterações parciais
        if (cityDto.getName() != null) city.setName(cityDto.getName());
        if (cityDto.getState() != null) city.setState(cityDto.getState());
        if (cityDto.getTripType() != null) city.setTripType(cityDto.getTripType());
        city = repository.save(city);
        return CityMapper.mapToDto(city);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
