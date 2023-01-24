package com.company.phonebook.Exception;

public class UserNotFoundException extends RuntimeException { //RuntimeException - Superclass that allows passage of exception message through built-in constructor
    public UserNotFoundException(String s) {
        super(s);
    }
}