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


public class PersonAddressAppIntegrationV2Test {

    private static final String INTEGRATION_TEST_DATA_URL = "jdbc:sqlite:temp_data_integration-test-data.db";
    private static RandomIdGenerator randomIdGenerator;
    private static PersonAddressApp personAddressApp;
    private static SqliteDbConnection dbConnection;

    private String uniqueDataId;
    private String uniquePersonName;

    private static final String PERSON_NAME_PREFIX = "Integration_v2_test_person_";
    static {
        System.out.println("Static block");
    }

    @BeforeAll
    public static void setUpClass() {
        System.out.println("@BeforeAll");
        randomIdGenerator = new RandomIdGenerator();

        dbConnection = new SqliteDbConnection();
       
        dbConnection.setUrl(INTEGRATION_TEST_DATA_URL);
        personAddressApp = new PersonAddressApp(dbConnection);
        personAddressApp.initDatabase(dbConnection);

    }

    @BeforeEach
    public void setUp() {
        System.out.println("@BeforeEach");
        uniqueDataId = PERSON_NAME_PREFIX + randomIdGenerator.generateRandomId() ;
        uniquePersonName = uniqueDataId + " Jan Kowalski";
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("@AfterAll");
        personAddressApp.listAllPersonData();

        //we might want to clean all added new data during all tests
    }

    @AfterEach
    public void tearDown() {
        System.out.println("@AfterEach");
        //we might want to clean all added new data during each test
    }

    @Test
    public void shouldAnswerWithTrue() {
        Assertions.assertTrue(true);
    }

    @Test
    public void shouldGetTestPersonFromDB() {
        //we are using new unique data for each test so initially we should have 0 persons with this given uniq name
        assertEquals(0, personAddressApp.findPersonsByName(uniquePersonName).size());
        //what can we fix here ? 

        Person person = new Person(uniquePersonName, 30);
        personAddressApp.addPersonToDatabase(person);

        Person personFromDB = personAddressApp.findPersonsByName(uniquePersonName).get(0);
        assertEquals(uniquePersonName, personFromDB.getName());
        assertEquals(30, personFromDB.getAge());
        
        
        assertEquals(1, personAddressApp.findPersonsByName(uniquePersonName).size());
    }

    @Test
    public void shouldCreatePerson() {
        Person person = new Person(uniquePersonName, 30);
        assertEquals(uniquePersonName, person.getName());
        assertEquals(30, person.getAge());
    }

    @Test
    public void shouldAddNewPersonToTestDb() {
        //Given
        //we are using new unique data for each test so initially we should have 0 persons with this given uniq name
        assertEquals(0, personAddressApp.findPersonsByName(uniquePersonName).size());
        //what can we fix here ? 

        //When
        Person person = new Person(uniquePersonName, 30);
        personAddressApp.addPersonToDatabase(person);

        //Then
        assertEquals(1, personAddressApp.findPersonsByName(uniquePersonName).size());

    }
    
}
