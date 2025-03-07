package com.example.meteotrip.mapper;

import com.example.meteotrip.dto.CityDto;
import com.example.meteotrip.model.City;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CityMapper {
    public City mapToCity(CityDto cityDto) {
        return City.builder()
                .id(cityDto.getId())
                .name(cityDto.getName())
                .state(cityDto.getState())
                .tripType(cityDto.getTripType())
                .build();
    }

    public CityDto mapToDto(City city) {
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .state(city.getState())
                .tripType(city.getTripType())
                .build();
    }
}
