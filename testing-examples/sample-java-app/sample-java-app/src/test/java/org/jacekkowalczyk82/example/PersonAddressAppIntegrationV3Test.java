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


public class PersonAddressAppIntegrationV3Test {

    private static final String DB_FILE_NAME="temp_data_integration-test-data.db";
    private static final String INTEGRATION_TEST_DATA_URL = "jdbc:sqlite:" + DB_FILE_NAME;
    private static RandomIdGenerator randomIdGenerator;
    private static PersonAddressApp personAddressApp;
    private static SqliteDbConnection dbConnection;

    private String uniqueDataId;
    private String uniquePersonName;

    private static final String PERSON_NAME_PREFIX = "Integration_v3_test_person_";
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
        //Given
        //we are using new unique data for each test so initially we should have 0 persons with this given uniq name
        assertEquals(0, personAddressApp.findPersonsByName(uniquePersonName).size(), 
            "Initially we should have 0 persons with this given unique name");
        //what can be still fixed here? 
        
        //When
        Person person = new Person(uniquePersonName, 30);
        personAddressApp.addPersonToDatabase(person);

        //Then
        Person personFromDB = personAddressApp.findPersonsByName(uniquePersonName).get(0);
        assertEquals(uniquePersonName, personFromDB.getName());
        assertEquals(30, personFromDB.getAge());
        
        
        assertEquals(1, personAddressApp.findPersonsByName(uniquePersonName).size(), 
            "After adding new person to the db we should have 1 person with this given unique name");
        //what can be still fixed here?     
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
     * 
     * 
     * 
     * 
     */


    @Test
    public void shouldAddNewPersonToTestDbFailingExample() {
        //this is hacky code to fail the example test
        Person unexpectedPerson1 = new Person(uniquePersonName, 30);
        personAddressApp.addPersonToDatabase(unexpectedPerson1);
        Person unexpectedPerson2 = new Person(uniquePersonName, 32);
        personAddressApp.addPersonToDatabase(unexpectedPerson2);

        //Given
        //we are using new unique data for each test so initially we should have 0 persons with this given uniq name
        assertEquals(0, personAddressApp.findPersonsByName(uniquePersonName).size(), 
            "Initially we should have 0 persons with this given unique name, but acctually we have: " + 
            personAddressApp.findPersonsByName(uniquePersonName).size() + 
            " persons with this given unique name: " + uniquePersonName + ", The DB contains the following persons: " +
            personAddressApp.findPersonsByName(uniquePersonName).toString());    

        //When
        Person person = new Person(uniquePersonName, 30);
        personAddressApp.addPersonToDatabase(person);

        //Then
        assertEquals(1, personAddressApp.findPersonsByName(uniquePersonName).size(),
        "After adding 1 Person we should have 1 person with this given unique name, but acctually we have: " + 
            personAddressApp.findPersonsByName(uniquePersonName).size() + 
            " persons with this given unique name: " + uniquePersonName + ", The DB contains the following persons: " +
            personAddressApp.findPersonsByName(uniquePersonName).toString());


    }
    
}
