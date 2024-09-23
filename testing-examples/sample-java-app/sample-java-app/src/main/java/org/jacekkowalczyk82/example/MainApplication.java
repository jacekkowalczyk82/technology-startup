package org.jacekkowalczyk82.example;

import org.jacekkowalczyk82.example.model.Person;
import org.jacekkowalczyk82.example.model.Address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


/**
 * Sample java app
 *
 */
public class MainApplication { 
    


    public static void main(String[] args) {

        SqliteDbConnection dbConnection = new SqliteDbConnection();
        dbConnection.setUrl("jdbc:sqlite:sample.db");
        PersonAddressApp personAddressApp = new PersonAddressApp(dbConnection);

        Person person = new Person("Jan Kowalski", 30);
        Address address = new Address("123 Main St", "Springfield", "IL", "62701");

        System.out.println(person);
        System.out.println(address);

    //this inits the database, creates tables and add one Person and one Address record
    // saveToDatabase(dbConnection, person, address);
     

    System.out.println("--------------------------");

    System.out.println("Data saved to database");
    personAddressApp.listAllPersonData();
    personAddressApp.listAllAddressData();

    // Adding new Addresses
    System.out.println("--------------------------");
    System.out.println("Adding new Addresses" );
    personAddressApp.addAddressToDatabase(new Address("Sezamkowa 123", "Lodz", "LODZ", "90-123"));


    System.out.println("--------------------------");
    System.out.println("Adding new Persons" );
    personAddressApp.addPersonToDatabase(new Person("Jacek Kowalczyk", 42));
    personAddressApp.addPersonToDatabase(new Person("John Smith", 25));
    personAddressApp.addPersonToDatabase(new Person("Jacek Nowak", 35));
    personAddressApp.addPersonToDatabase(new Person("John Kowalski", 45));
    personAddressApp.addPersonToDatabase(new Person("Jacek placek", 55));
    personAddressApp.addPersonToDatabase(new Person("John Maclain", 65));

    System.out.println("--------------------------");
    System.out.println("Persons with name containing 'Jacek'");
    personAddressApp.findPersonsByName("Jacek").forEach(System.out::println);

    System.out.println("--------------------------");
    System.out.println("Persons with name containing 'John'");
    personAddressApp.findPersonsByName("John").forEach(System.out::println);
}

private static void saveToDatabase(SqliteDbConnection dbConnection, Person person, Address address) {
    String url = dbConnection.getUrl();

    try (Connection conn = DriverManager.getConnection(url)) {
        if (conn != null) {
            String createPersonTable = "CREATE TABLE IF NOT EXISTS Person (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
            String createAddressTable = "CREATE TABLE IF NOT EXISTS Address (id INTEGER PRIMARY KEY, street TEXT, city TEXT, state TEXT, zipCode TEXT)";
            conn.createStatement().execute(createPersonTable);
            conn.createStatement().execute(createAddressTable);

            String insertPerson = "INSERT INTO Person (id, name, age) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertPerson)) {
                pstmt.setInt(1, person.getId());
                pstmt.setString(2, person.getName());
                pstmt.setInt(3, person.getAge());
                pstmt.executeUpdate();
            }

            String insertAddress = "INSERT INTO Address (id, street, city, state, zipCode) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertAddress)) {
                pstmt.setInt(1, address.getId());
                pstmt.setString(2, address.getStreet());
                pstmt.setString(3, address.getCity());
                pstmt.setString(4, address.getState());
                pstmt.setString(5, address.getZipCode());
                pstmt.executeUpdate();
            }
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
}

    
    

   
    
}
