package com.cnexia.starwarsbackend.controllers;

import com.cnexia.starwarsbackend.entities.Person;
import com.cnexia.starwarsbackend.services.PeopleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @MutationMapping
    public Person createPerson(@Argument Person person) {
        log.info("Create new person with name {}", person.getName());
        return peopleService.createPerson(person);
    }

    @QueryMapping
    public List<Person> person(@Argument String name) {
        log.info("Fetching person by name {}", name);
        return peopleService.personByName(name);
    }

    @MutationMapping
    public Integer deletePerson(@Argument Integer id) {
        log.info("Deleting the bookmarked person with id: {}", id);
        return peopleService.deletePerson(id);
    }
}
