//Extend CRUD Services
package com.company.phonebook.Repository;

import com.company.phonebook.Model.Person;
import org.springframework.data.jpa.repository.JpaRepository; //Imports functions for easy CRUD operations (Hold ctrl + left click to view functions)

import java.util.Optional; //A container object which may or may not contain a non-null value

public interface PersonRepository extends JpaRepository<Person, Long> {
    void deletePersonById(Long id);

    Optional<Person> findPersonById(Long id);
}
