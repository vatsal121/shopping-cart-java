package com.isi.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.isi.entity.User;
import com.isi.service.DBConnector;

public class LoginManager {

	private static String getAllUsersQuery = "select * FROM users ";
	private static String whereClause = "where UserName=?";
	private static String insertUserQuery = "insert into users(UserName,PasswordSalt,PasswordHash,Role,DateCreated) "
			+ "values(?,?,?,?,?)";

	public static boolean checkUserExists(String userName) {

		Connection con;
		try {
			con = DBConnector.ConnectToDatabase(true);

			String newQuery = getAllUsersQuery + whereClause;
			PreparedStatement statement = con.prepareStatement(newQuery);
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return true;
			}
			DBConnector.CloseConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static User getUser(String userName) {
		User user = null;
		try {
			Connection con;
			con = DBConnector.ConnectToDatabase(true);
			String newQuery = getAllUsersQuery + whereClause;
			PreparedStatement statement = con.prepareStatement(newQuery);
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
//				java.util.Date createdDate = null, modifiedDate = null;
//				Timestamp createdDateTimeStamp = rs.getTimestamp("DateCreated");
//				Timestamp modifiedDateTimeStamp = rs.getTimestamp("DateModified");
//
//				if (createdDateTimeStamp != null) {
//					createdDate = new java.util.Date(createdDateTimeStamp.getTime());
//				}
//
//				if (modifiedDateTimeStamp != null) {
//					modifiedDate = new java.util.Date(modifiedDateTimeStamp.getTime());
//				}

				user = new User(rs.getInt("Id"), rs.getString("UserName"), rs.getString("PasswordSalt"),
						rs.getString("PasswordHash"), rs.getString("Role"), rs.getDate("DateCreated"),
						rs.getDate("DateModified"));
			}
			DBConnector.CloseConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public static void AddUser(User user) {
		if (user != null) {
			try {
				Connection con;
				con = DBConnector.ConnectToDatabase(true);
				PreparedStatement statement = con.prepareStatement(insertUserQuery);
				statement.setString(1, user.getUserName());
				statement.setString(2, user.getPasswordSalt());
				statement.setString(3, user.getPasswordHash());
				statement.setString(4, user.getRole());
				statement.setDate(5, user.getDateCreated());
				statement.execute();
				DBConnector.CloseConnection();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
