package org.jacekkowalczyk82.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.jacekkowalczyk82.example.model.Person;
import org.jacekkowalczyk82.example.model.Address;


public class PersonAddressAppBadExampleTest {


    static {
        System.out.println("Static block");
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("@BeforeAll");
    }

    @BeforeEach
    public void setUp() {
        System.out.println("@BeforeEach");
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("@AfterAll");
        //we might want to clean all added new data during all tests
    }

    @AfterEach
    public void tearDownEach() {
        System.out.println("@AfterEach");
        //we might want to clean all added new data during each test
    }

    @Test
    public void shouldCreatePerson() {
        Person person = new Person("Jan Kowalski", 30);
        
    }

    @Test
    public void shouldCreateAddress() {
        Address address = new Address("Pileckiego 18", "Koluszki", "lodzkie", "95-040");
        
    }
   

}
