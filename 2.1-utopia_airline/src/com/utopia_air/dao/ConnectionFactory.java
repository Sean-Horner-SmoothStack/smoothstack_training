package com.utopia_air.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost/utopia";
    public static final String USER = "?user=root";
    public static final String PSWD = "&password=4Sean2DataBase@";

    private static Connection conn = null;

    /*
     * Initiating a connection to the database and returning a Connection object.
     *
     * Attempting a singleton-like implementation, perhaps changing over to full
     * doubly-locked singleton object.
     */
    public static Connection getConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL + USER + PSWD);
            } catch (SQLException e) {
                throw new RuntimeException("Error connecting to the database", e);
            }
        }

        return conn;
    }

//    // connection test
//    public static void main(String[] args) {
//        Connection conn = getConnection();
//    }

}
