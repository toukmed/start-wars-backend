package com.cnexia.starwarsbackend.repositories;

import com.cnexia.starwarsbackend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeopleRepository extends JpaRepository<Person, Integer> {


    boolean existsByName(String name);

    @Query("SELECT p FROM Person p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Person> findByName(String name);

    Person deleteByName(String name);
}
