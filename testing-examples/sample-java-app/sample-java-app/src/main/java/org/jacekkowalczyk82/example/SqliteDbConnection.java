package org.jacekkowalczyk82.example;

public class SqliteDbConnection {
    // JDBC driver name and database URL 
    // private String url = "jdbc:sqlite:sample.db";

    private static final String DEFAULT_URL = "jdbc:sqlite:sample.db";

    private String url;
    
    //please generate getters and setters and constructor
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }   

    public SqliteDbConnection(String url) {
        this.url = url;
    }

    public SqliteDbConnection() {
        this.url = DEFAULT_URL;
    }   

}
