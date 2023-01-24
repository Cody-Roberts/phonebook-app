package com.company.phonebook;

import com.company.phonebook.Model.Person;
import com.company.phonebook.Service.PersonService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PhonebookApplicationTests {
	@Autowired
	private PersonService personService;
	@Test
	void contextLoads() {
	}
	//Add
	@Test
	void testAddPerson() {
		Person person = new Person();
		person.setFirstName("Charles");
		person.setMiddleInitial('F');
		person.setLastName("Unittest");
		person.setPhoneNumber("527.112.3443");
		person.setEmail("cody.roberts@wmich.edu");
		person.setAddress("122 East Test St");
		Person actual = personService.addPerson(person);
		assertSame(actual, person);
	}
	@Test
	void testAddPersonWherePersonNull() {
		assertThrows(InvalidDataAccessApiUsageException.class, () -> personService.addPerson(null));
	}
	@Test
	void testAddPersonWherePersonInvalidFirstNameBlank() {
		Person person = new Person();
			person.setFirstName("");
			person.setMiddleInitial('F');
			person.setLastName("Unittest");
			person.setPhoneNumber("527.112.3443");
			person.setEmail("cody.roberts@wmich.edu");
			person.setAddress("122 East Test St");
		boolean isValid = person.nameIsValid();
		assertFalse(isValid);

		boolean added = addPersonToRepository(person);
		assertFalse(added);
	}

	private boolean addPersonToRepository(Person person) {
		if(person.nameIsValid()){
			// Add person to repository
			return true;
		}
		return false;
	}

	//Update
	@Test
	void testUpdatePersonValid() {
		Person person = new Person();
		person.setId(102L); //Have to get the id from Postman to match the id you want to update test
		person.setFirstName("Update3");
		person.setMiddleInitial('F');
		person.setLastName("Unittest");
		person.setPhoneNumber("527.112.3443");
		person.setEmail("cody.roberts@wmich.edu");
		person.setAddress("122 East Test St");
		Person actual = personService.updatePerson(person);
		assertEquals(actual, person);
	}
	@Test
	void testUpdatePersonInvalid() {
		assertThrows(InvalidDataAccessApiUsageException.class, () -> personService.updatePerson(null));
	}



}
