package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    		/* Database Schema
			// CREATE DATABASE capstone;
			// USE capstone;

			CREATE TABLE `registration` (
			`id` char(36) NOT NULL, 
	    	`username` char(40) DEFAULT null, 
	    	`password` char(40) DEFAULT null,
	    	`email` char(100) DEFAULT null,
	    	`isVerified` BOOLEAN DEFAULT FALSE,
	    	PRIMARY KEY (`id`)
	    	);*/
		
    private static final String dbUser = "alex";
    private static final String dbPassword = "capstone1!";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/capstone";

    public static Connection getConnectionToDatabase() {
        Connection connection = null;

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
           	connection = DriverManager.getConnection(CONN_STRING, dbUser, dbPassword);
        } catch (ClassNotFoundException e) {
        	System.out.println("Missing MySQL JDBC Driver");
        	e.printStackTrace();
        } catch (SQLException e) {
        	e.printStackTrace();
        }
        return connection;
    }
}
