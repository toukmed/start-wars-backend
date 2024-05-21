package com.cnexia.starwarsbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(name = "cargo_capacity")
    private String cargoCapacity;
    private String consumables;
    @Column(name = "cost_in_credits")
    private String costInCredits;
    private String crew;
    private String length;
    private String manufacturer;
    @Column(name = "max_atmosphering_speed")
    private String maxAtmospheringSpeed;
    private String model;
    private String passengers;
    @Column(name = "vehicle_class")
    private String vehicleClass;
}
