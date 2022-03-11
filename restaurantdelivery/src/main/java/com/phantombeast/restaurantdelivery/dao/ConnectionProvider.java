package com.phantombeast.restaurantdelivery.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
	private static String username = "admin";
	private static String password = "Harshul1@3";
	private static String driver = "com.mysql.cj.jdbc.Driver";

	public static synchronized Connection getConnection() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
