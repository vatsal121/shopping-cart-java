package com.isi.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Code by : Vatsal Chauhan
 * Date: 18-Aug-2020
 */


public class DBConnector {

	private static final String ConnectionURL = "jdbc:mysql://localhost:3306/";

	private static final String DBName = "shoppingdb_vmp";
	private static final String UserName = "root";
	private static final String Password = "abc123...";
	private static final String DriverName = "com.mysql.jdbc.Driver";

	private static Connection connection = null;
//	public DBConnector() {
//
//	}

//	public DBConnector(String connectionURL, String dBName, String userName, String password, String driverName) {
//		ConnectionURL = connectionURL;
//		DBName = dBName;
//		UserName = userName;
//		Password = password;
//		DriverName = driverName;
//	}

	public static Connection ConnectToDatabase(Boolean autoCommit) throws ClassNotFoundException {

		
		try {
			Class.forName(DriverName);
			connection = DriverManager.getConnection(ConnectionURL + DBName, UserName, Password);

			connection.setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static ResultSet ExecuteQuery(Connection connection, String query) {
		ResultSet rs = null;
		try {
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void CloseConnection() throws SQLException {
		connection.close();
	}
}
