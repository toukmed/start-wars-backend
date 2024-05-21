package com.cnexia.starwarsbackend.repositories;

import com.cnexia.starwarsbackend.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
}
