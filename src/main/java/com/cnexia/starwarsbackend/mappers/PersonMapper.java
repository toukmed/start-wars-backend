package com.cnexia.starwarsbackend.mappers;

import com.cnexia.starwarsbackend.entities.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person dtoToEntity(Person person){
        return Person.builder()
                .name(person.getName())
                .birthYear(person.getBirthYear())
                .eyeColor(person.getEyeColor())
                .hairColor(person.getHairColor())
                .skinColor(person.getSkinColor())
                .mass(person.getMass())
                .gender(person.getGender())
                .height(person.getHeight())
                .films(String.join(",", person.getFilms()))
                .vehicles(String.join(",", person.getVehicles()))
                .build();
    }

    public Person entityToDto(Person person){
        return Person.builder()
                .id(person.getId())
                .name(person.getName())
                .birthYear(person.getBirthYear())
                .eyeColor(person.getEyeColor())
                .hairColor(person.getHairColor())
                .skinColor(person.getSkinColor())
                .mass(person.getMass())
                .gender(person.getGender())
                .height(person.getHeight())
                .build();
    }
}
