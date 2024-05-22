package com.cnexia.starwarsbackend.controllers;

import com.cnexia.starwarsbackend.entities.Person;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PeopleControllerTest {

    @Autowired
    HttpGraphQlTester httpGraphQlTester;

    Person savedPerson;

    @Test
    @Order(1)
    void createNewPerson() {
        final int randomNumber = new Random().nextInt(100 - 1) + 1;
        Person person = this.httpGraphQlTester
                .document(String.format("""
                            mutation {
                                createPerson(
                                             person: {
                                                 name: "Luke Skywalker %s",
                                                 birthYear: "19BBY",
                                                 films: "A New Hope,Snowspeeder",
                                                 vehicles: "Snowspeeder,A New Hope2"
                                             }
                                         ) {
                                             id
                                             name
                                             birthYear
                                         }
                            }
                        """, randomNumber))
                .execute()
                .errors()
                .verify()
                .path("createPerson")
                .entity(Person.class)
                .get();
        assertThat(person.getId()).isNotNull();
        assertThat(person.getName()).isEqualTo("Luke Skywalker " + randomNumber);
        assertThat(person.getBirthYear()).isEqualTo("19BBY");
        savedPerson = person;
    }

    @Test
    @Order(2)
    void recreateExistingPerson() {
        this.httpGraphQlTester
                .document(String.format("""
                            mutation {
                                createPerson(
                                             person: {
                                                 name: "%s",
                                                 birthYear: "%s",
                                                 films: "%s",
                                                 vehicles: "%s"
                                             }
                                         ) {
                                             id
                                             name
                                             birthYear
                                         }
                            }
                        """,
                        savedPerson.getName(),
                        savedPerson.getBirthYear(),
                        savedPerson.getFilms(),
                        savedPerson.getVehicles()))
                .execute()
                .errors().satisfy(errors -> assertThat(errors)
                        .anyMatch(error -> Objects
                                .requireNonNull(error.getMessage())
                                .contains("Person: " + savedPerson.getName() + " is already bookmarked")));
    }

    @Test
    @Order(3)
    void searchPeople() {
        List<Person> people = this.httpGraphQlTester
                .document("""
                            query {
                                person(name: ""){
                                                  id
                                                  name
                                                  birthYear
                                              }
                            }
                        """)
                .execute()
                .errors()
                .verify()
                .path("person")
                .entityList(Person.class)
                .get();
        assertThat(people.size()).isEqualTo(1);
    }

    @Test
    @Order(4)
    void searchPeopleByName() {
        List<Person> people = this.httpGraphQlTester
                .document(String.format("""
                            query {
                                person(name: "%s"){
                                                  id
                                                  name
                                                  birthYear
                                              }
                            }
                        """, savedPerson.getName()))
                .execute()
                .errors()
                .verify()
                .path("person")
                .entityList(Person.class)
                .get();
        assertThat(people.get(0).getId()).isNotNull();
        assertThat(people.get(0).getName()).isEqualTo(savedPerson.getName());
        assertThat(people.get(0).getBirthYear()).isEqualTo(savedPerson.getBirthYear());
    }

    @Test
    @Order(5)
    void deletePerson() {
        Integer personId = this.httpGraphQlTester
                .document(String.format("""
                            mutation {
                                 deletePerson(id: %s)
                             }
                        """, savedPerson.getId()))
                .execute()
                .errors()
                .verify()
                .path("deletePerson")
                .entity(Integer.class)
                .get();
        assertThat(personId).isEqualTo(savedPerson.getId());
    }

}
