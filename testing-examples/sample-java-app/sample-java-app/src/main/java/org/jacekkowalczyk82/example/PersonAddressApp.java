package org.jacekkowalczyk82.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jacekkowalczyk82.example.model.Address;
import org.jacekkowalczyk82.example.model.Person;

public class PersonAddressApp {

    private SqliteDbConnection dbConnection = new SqliteDbConnection();

    //generate getters and setters and Constructor
    public SqliteDbConnection getDbConnection() {
        return dbConnection;
    }   

    public void setDbConnection(SqliteDbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public PersonAddressApp(SqliteDbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void initDatabase(SqliteDbConnection dbConnection) {
        String url = dbConnection.getUrl();

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                String createPersonTable = "CREATE TABLE IF NOT EXISTS Person (id INTEGER PRIMARY KEY, name TEXT, age INTEGER)";
                String createAddressTable = "CREATE TABLE IF NOT EXISTS Address (id INTEGER PRIMARY KEY, street TEXT, city TEXT, state TEXT, zipCode TEXT)";
                conn.createStatement().execute(createPersonTable);
                conn.createStatement().execute(createAddressTable);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Person> findPersonsByName(String nameCriteria) {
        
        String url = this.getDbConnection().getUrl();
        String query = "SELECT * FROM Person WHERE name LIKE ?";
        List<Person> persons = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + nameCriteria + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                persons.add(new Person(id, name, age));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Persons found: " + persons.toString());
        return persons;
    }

    public List<Address> findAddressesByCity(String cityCriteria) {
        String url = this.getDbConnection().getUrl();
        String query = "SELECT * FROM Address WHERE city LIKE ?";
        List<Address> addresses = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + cityCriteria + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zipCode = rs.getString("zipCode");
                addresses.add(new Address(id, street, city, state, zipCode));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return addresses;
    }

    public void addPersonToDatabase(Person person) {
        if ("Gal Anonim".equals(person.getName())) {
            throw new RuntimeException("Gal Anonim is not allowed to be added to the database");
        }
        String url = this.getDbConnection().getUrl();
        String query = "INSERT INTO Person (name, age) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, person.getName());
            pstmt.setInt(2, person.getAge());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addAddressToDatabase(Address address) {
        String url = this.getDbConnection().getUrl();
        String query = "INSERT INTO Address (street, city, state, zipCode) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, address.getStreet());
            pstmt.setString(2, address.getCity());
            pstmt.setString(3, address.getState());
            pstmt.setString(4, address.getZipCode());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

    public void listAllPersonData() {
        String url = this.getDbConnection().getUrl();
        String query = "SELECT * FROM Person";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listAllAddressData() {
        String url = this.getDbConnection().getUrl();
        String query = "SELECT * FROM Address";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String street = rs.getString("street");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String zipCode = rs.getString("zipCode");
                System.out.println("ID: " + id + ", Street: " + street + ", City: " + city + ", State: " + state + ", Zip Code: " + zipCode);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removePersonByName(String personName) {
        String url = this.getDbConnection().getUrl();
        String deleteSQL = "DELETE FROM Person WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setString(1, personName);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Person with name " + personName + " was removed successfully.");
            } else {
                System.out.println("No person found with name " + personName);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
}
