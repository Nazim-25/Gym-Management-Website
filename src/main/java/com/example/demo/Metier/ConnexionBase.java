package com.example.demo.Metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



//Class responsible for database connection
public class ConnexionBase {
	
	// Database connection information
	public static final String Host = "jdbc:mysql://localhost:3306/";
	public static final String DB_Name = "sportsworld";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";

	 // Connection instance
	private static Connection connect = null;
	
	// Constructor establishing the database connection
	public ConnexionBase() {
		try {
			 // Establishing a connection using DriverManager with provided credentials and settings
			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/sportsworld?useUnicode=true&characterEncoding=UTF-8&&serverTimezone=GMT",
					USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Driver Manager Erreur " + e);
		}
	}

	// Method to get the connection instance
	public static Connection getConnection() {
		if (connect == null) {
			 // If the connection is null, create a new instance of ConnexionBase to establish connection
			new ConnexionBase();
		}
		// Return the connection instance
		return connect;
	}
}
