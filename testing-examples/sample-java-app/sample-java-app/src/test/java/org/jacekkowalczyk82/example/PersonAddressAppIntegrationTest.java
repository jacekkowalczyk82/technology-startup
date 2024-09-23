package org.jacekkowalczyk82.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.jacekkowalczyk82.example.model.Person;


public class PersonAddressAppIntegrationTest {

    private static RandomIdGenerator randomIdGenerator;

    private PersonAddressApp personAddressApp;
    private SqliteDbConnection dbConnection;

    private String testDbId;
    private String dbFileName;

    static {
        System.out.println("Static block");
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("@BeforeAll");
        randomIdGenerator = new RandomIdGenerator();

    }

    @BeforeEach
    public void setUp() {
        System.out.println("@BeforeEach");

        dbConnection = new SqliteDbConnection();
        testDbId = randomIdGenerator.generateRandomId();
        dbFileName = "test_data_" + testDbId + ".db";
        dbConnection.setUrl("jdbc:sqlite:" + dbFileName);
        personAddressApp = new PersonAddressApp(dbConnection);
        personAddressApp.initDatabase(dbConnection);
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
        try {
            System.out.println("Deleting test db file: " + dbFileName);
            Files.deleteIfExists(Path.of(dbFileName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //what can we also fix here?
        }
    }


    @Test
    public void shouldAnswerWithTrue() {
        Assertions.assertTrue(true);
    }

    @Test
    public void shouldGetTestPersonFromDB() {
        //we are usng new db for each test so we should have no person data in the db
        assertEquals(0, personAddressApp.findPersonsByName("Jan Kowalski").size());

        Person person = new Person("Jan Kowalski", 30);
        personAddressApp.addPersonToDatabase(person);

        Person personFromDB = personAddressApp.findPersonsByName("Jan Kowalski").get(0);
        assertEquals("Jan Kowalski", personFromDB.getName());
        assertEquals(30, personFromDB.getAge());
        
        //we are usng new db for each test so we should have no person data in the db
        assertEquals(1, personAddressApp.findPersonsByName("Jan Kowalski").size());
    }

    @Test
    public void shouldCreatePerson() {
        Person person = new Person("Jan Kowalski", 30);
        assertEquals("Jan Kowalski", person.getName());
        assertEquals(30, person.getAge());
    }

    @Test
    public void shouldAddNewPersonToTestDb() {
        //we are usng new db for each test so we should have no person data in the db
        assertEquals(0, personAddressApp.findPersonsByName("Jan Kowalski").size());

        Person person = new Person("Jan Kowalski", 30);
        personAddressApp.addPersonToDatabase(person);
        assertEquals(1, personAddressApp.findPersonsByName("Jan Kowalski").size());

    }
    
}
