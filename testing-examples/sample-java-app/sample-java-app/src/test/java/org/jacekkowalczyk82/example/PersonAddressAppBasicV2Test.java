package org.jacekkowalczyk82.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.jacekkowalczyk82.example.model.Address;
import org.jacekkowalczyk82.example.model.Person;


public class PersonAddressAppBasicV2Test {

    private static final String DEFAULT_TEST_DATA_URL = "jdbc:sqlite:src/test/resources/test-data.db";
    private PersonAddressApp personAddressApp;
    private SqliteDbConnection dbConnection;

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

        dbConnection = new SqliteDbConnection();
        dbConnection.setUrl(DEFAULT_TEST_DATA_URL);
        personAddressApp = new PersonAddressApp(dbConnection);
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
    public void shouldAnswerWithTrue() {
        Assertions.assertTrue(true);
    }

    

    @Test
    public void shouldCreatePerson() {
        Person person = new Person("Jan Kowalski", 30);
        assertEquals("Jan Kowalski", person.getName());
        assertEquals(30, person.getAge());
    }

    @Test
    public void shouldGetTestPersonFromDB() {
        Person person = personAddressApp.findPersonsByName("Jan Kowalski").get(0);
        assertEquals("Jan Kowalski", person.getName());
        assertEquals(30, person.getAge());
        assertEquals(1, personAddressApp.findPersonsByName("Jan Kowalski").size());
    }

    @Test
    public void shouldCreateAddress() {
        Address address = new Address("Pileckiego 18", "Koluszki", "lodzkie", "95-040");
        assertEquals("Koluszki", address.getCity());
        assertEquals("Pileckiego 18", address.getStreet());
        assertEquals("lodzkie", address.getState());
        assertEquals("95-040", address.getZipCode());

        
    }
    
    /*
     * 
     * 
     * 
     * 
     * examples below  
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */


    @Test
    public void shouldCreateAddressV2() {
        Address address = new Address("Pileckiego 18", "Koluszki", "lodzkie", "95-040");
        assertEquals("Koluszki", address.getCity(), "City is not correct, it should be Koluszki");
        assertEquals("Pileckiego 18", address.getStreet(), "Street is not correct, it should be Pileckiego 18");
        assertEquals("lodzkie", address.getState(), "State is not correct, it should be lodzkie");
        assertEquals("95-040", address.getZipCode(), "Zip code is not correct, it should be 95-040");
        
    }

}
