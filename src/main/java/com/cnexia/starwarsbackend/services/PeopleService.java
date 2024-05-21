package com.cnexia.starwarsbackend.services;

import com.cnexia.starwarsbackend.entities.Person;
import com.cnexia.starwarsbackend.exceptions.BusinessException;
import com.cnexia.starwarsbackend.mappers.PersonMapper;
import com.cnexia.starwarsbackend.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    PersonMapper personMapper;

    public Person createPerson(Person person) {
        if(peopleRepository.existsByName(person.getName())){
            throw new BusinessException(String.format("Person: %s is already bookmarked", person.getName()), HttpStatus.BAD_REQUEST);
        }
        return personMapper.entityToDto(peopleRepository.save(person));
    }

    public List<Person> personByName(String name) {
        return peopleRepository.findByName(name);
    }

    public List<Person> persons() {
        return peopleRepository.findAll();
    }

    public Integer deletePerson(Integer id) {
        if(!peopleRepository.existsById(id)){
            throw new BusinessException(String.format("Person with id: %s is not bookmarked", id), HttpStatus.BAD_REQUEST);
        }
        peopleRepository.deleteById(id);
        return id;
    }
}
