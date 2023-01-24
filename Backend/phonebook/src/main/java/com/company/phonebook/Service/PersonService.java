//Service Layer
package com.company.phonebook.Service;

import com.company.phonebook.Model.Person;
import com.company.phonebook.Repository.PersonRepository;
import com.company.phonebook.Exception.UserNotFoundException;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired; //Dependency Injection: supplying a resource that a given piece of code requires
import org.springframework.stereotype.Service; //Declaring as Spring bean for dependency injection.
import org.springframework.transaction.annotation.Transactional; //Provides a way for Spring to inject behaviors before, after, or around method calls into the object
import java.util.List;

@Service //Indicates that an annotated class is a Service class
@Transactional //Defines a transaction attribute on a class
@RequiredArgsConstructor //Constructor
public class PersonService {
    //Service requesting data from Database layer
    private final @NotNull PersonRepository personRepository;
//    @Autowired  //Dependency Injection


    //CRUD Operation Services (Get, Post, Delete, Put)
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public Person updatePerson(Person person) {
        return personRepository.save(person);
    }

    public Person findPersonById(Long id) {
        return personRepository.findPersonById(id).orElseThrow(() -> new UserNotFoundException("User id: " + id + " was not found"));
    }

    public void deletePerson(Long id){
        personRepository.deletePersonById(id);
    }
}
