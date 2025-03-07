package com.example.meteotrip.model;

import com.example.meteotrip.enums.TripType;
import jakarta.persistence.*;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String state;
    private TripType tripType;
}
