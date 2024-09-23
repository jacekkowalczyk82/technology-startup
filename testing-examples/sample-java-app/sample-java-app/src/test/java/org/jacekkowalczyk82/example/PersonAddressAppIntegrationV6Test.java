package org.jacekkowalczyk82.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.jacekkowalczyk82.example.model.Person;


public class PersonAddressAppIntegrationV6Test {

    private static final String DB_FILE_NAME="temp_data_integration-test-data.db";
    private static final String INTEGRATION_TEST_DATA_URL = "jdbc:sqlite:" + DB_FILE_NAME;
    private static RandomIdGenerator randomIdGenerator;
    private static PersonAddressApp personAddressApp;
    private static SqliteDbConnection dbConnection;
    private TestInfo testInfo;

    private String uniqueDataId;
    private String uniquePersonName;

    private static final String PERSON_NAME_PREFIX = "Integration_v6_test_person ";
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
    public void setUp(TestInfo testInfo) {
        System.out.println("@BeforeEach - " + testInfo.getTestMethod().get().getName() + " with DisplayName: " + testInfo.getDisplayName());
        this.testInfo = testInfo;

        uniqueDataId = PERSON_NAME_PREFIX + randomIdGenerator.generateRandomId() ;
        uniquePersonName = uniqueDataId + " Jan Kowalski";
    }

    @AfterAll
    public static void tearDownClass() {
        System.out.println("@AfterAll");
        personAddressApp.listAllPersonData();

        //we might want to clean all added new data during all tests

        try {
            System.out.println("Deleting test db file: " + DB_FILE_NAME);
            Files.deleteIfExists(Path.of(DB_FILE_NAME));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //what can we also fix here?
        }

    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        System.out.println("@AfterEach - " + testInfo.getTestMethod().get().getName() + " with DisplayName: " + testInfo.getDisplayName());
        
        //we might want to clean all added new data during each test
        personAddressApp.removePersonByName(uniquePersonName);

        assertEquals(0, personAddressApp.findPersonsByName(uniquePersonName).size(), 
            "After Cleaning of test data we should have 0 persons with this given unique name, but acctually we have: " + 
            personAddressApp.findPersonsByName(uniquePersonName).size() + 
            " persons with this given unique name: " + uniquePersonName + ", The DB contains the following persons: " +
            personAddressApp.findPersonsByName(uniquePersonName).toString());   
    }

    @Test
    public void shouldAnswerWithTrue() {
        Assertions.assertTrue(true);
    }

    @Test
    public void shouldGetTestPersonFromDB() {
        System.out.println("@Test - " + testInfo.getTestMethod().get().getName() + " with DisplayName: " + testInfo.getDisplayName());
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
    public void shouldAddNewPersonToTestDb() {
        System.out.println("@Test - " + testInfo.getTestMethod().get().getName() + " with DisplayName: " + testInfo.getDisplayName());
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

    @ParameterizedTest
    @CsvSource({
        "Jan Kowalski",
        "Anna Nowak",
        "Alicja Majewska",
        "Robert Janson"
    })
    public void shouldAddNewPersonsToTestDb(String personName) {
        System.out.println("@ParameterizedTest - " + testInfo.getTestMethod().get().getName() + " with DisplayName: " + testInfo.getDisplayName());
        //Given
        //we are using new unique data for each test so initially we should have 0 persons with this given uniq name
        assertEquals(0, personAddressApp.findPersonsByName(personName).size(), 
            "Initially we should have 0 persons with this given unique name, but acctually we have: " + 
            personAddressApp.findPersonsByName(personName).size() + 
            " persons with this given unique name: " + personName + ", The DB contains the following persons: " +
            personAddressApp.findPersonsByName(personName).toString());    

        //When
        Person person = new Person(personName, 30);
        System.out.println("Adding new person to the DB: " + person);
        personAddressApp.addPersonToDatabase(person);

        //Then
        assertEquals(1, personAddressApp.findPersonsByName(personName).size(),
        "After adding 1 Person we should have 1 person with this given unique name, but acctually we have: " + 
            personAddressApp.findPersonsByName(personName).size() + 
            " persons with this given unique name: " + personName + ", The DB contains the following persons: " +
            personAddressApp.findPersonsByName(personName).toString());
    }
    
}
