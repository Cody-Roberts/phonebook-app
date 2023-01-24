//Model
package com.company.phonebook.Model;

import jakarta.persistence.*; //Entity, Id, GeneratedValue
import jakarta.validation.constraints.*; //Form Validation annotations
import lombok.Builder;
import lombok.Data;

import java.io.Serializable; //Converts object state to a byte stream in order to be able to recreate it in Database
@Data
@Builder
@Entity //Mapping Person class to table in database /JPA Entry
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "First Name is mandatory") //Form Validation
    private String firstName;
    private Character middleInitial;
    private String lastName;
    @Pattern(regexp = "[0-9]{3}[.][0-9]{3}[.][0-9]{4}")
    private String phoneNumber;
    @Email
    private String email;
    private String address;

    //Constructor
    public Person(){}

    //Constructor with all fields
    public Person(Long id, String firstName, char middleInitial, String lastName, String phoneNumber, String email, String address) {
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }
    public boolean nameIsValid() {
        if (firstName.trim().isEmpty()) {
            return false;
        }
        return true;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(char middleInitial) {this.middleInitial = middleInitial;}

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    //@Override replaces inherited behaviour and protects from mistakes with string conversion process
    @Override
    //representing object as a string for printing
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
