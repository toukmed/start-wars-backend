package com.cnexia.starwarsbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "peoples")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String height;
    private String mass;
    @Column(name = "hair_color")
    private String hairColor;
    @Column(name = "skin_color")
    private String skinColor;
    @Column(name = "eye_color")
    private String eyeColor;
    @Column(name = "birth_year")
    private String birthYear;
    private String gender;
    private String films;
    private String vehicles;
}
