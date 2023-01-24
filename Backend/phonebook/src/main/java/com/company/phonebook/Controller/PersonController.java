//API Layer
package com.company.phonebook.Controller;

import com.company.phonebook.Model.Person;
import com.company.phonebook.Service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; //Enumeration of HTTP status codes.
import org.springframework.http.ResponseEntity; //adds an HttpStatusCode status code
import org.springframework.web.bind.annotation.*; //Rest Interfaces

import java.util.List;

@RestController //Defines it as a Rest Service
@RequestMapping(path = "api/v1/person") //set path for api access
public class PersonController {
    //Requesting data from Service layer
    private final PersonService personService;

    @Autowired  //Dependency Injection
    //Constructor
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    //Taking CRUD Operations (Get, Post, Delete, Put) and passing between Service & Client
    @GetMapping("/all") //Get a list of entries request
    public ResponseEntity<List<Person>> getAllPersons () {
        List<Person> Persons = personService.findAllPersons();
        return new ResponseEntity<>(Persons, HttpStatus.OK); //Returns result of request
    }

    @GetMapping("/find/{id}") //Get a specified entry based on id request
    public ResponseEntity<Person> getPersonById (@PathVariable("id") Long id) {
        Person person = personService.findPersonById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @PostMapping("/add") //Add new entry request
    public ResponseEntity<Person> addPerson(@RequestBody Person Person) {
        Person newPerson = personService.addPerson(Person);
        return new ResponseEntity<>(newPerson, HttpStatus.CREATED);
    }

    @PutMapping("/update") //Update an entry request
    public ResponseEntity<Person> updatePerson(@RequestBody Person Person) {
        Person updatePerson = personService.updatePerson(Person);
        return new ResponseEntity<>(updatePerson, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}") //Delete an entry request
    public ResponseEntity<?> deletePerson(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
