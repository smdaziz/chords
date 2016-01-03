package com.carshare.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.carshare.beans.User;

public class UserDAO {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/carshare";
	
	private static final String DB_USER = "root";
	private static final String DB_PASS = "mysql";
	
	public boolean insertUser(User user) {
		
		Connection connection = null;
		Statement statement = null;
		String insertUserSQL = "INSERT INTO USER(lastname, firstname, email, mobile, username, password) VALUES" +
				"('" +
				user.getFirstName()+"', '" +user.getLastName()+"', '" +user.getEmail()+"', '" +
				user.getMobile()+"', '" +user.getUserName()+"', '" +user.getPassword()+"')";
		boolean recordInserted = false;
		
		try {
			connection = getDBConnection();
			statement = connection.createStatement();
			statement.executeUpdate(insertUserSQL);
			recordInserted = true;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return recordInserted;
	}
	
	public boolean userExists(String userName, String password) {
		
		Connection connection = null;
		Statement statement = null;
		String selectUserSQL = "SELECT * FROM user WHERE username = '" +userName+ "' AND password = '" +password+ "'";
		boolean userExists = false;
		
		try {
			connection = getDBConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectUserSQL);
			if(resultSet.next())
				userExists = true;
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return userExists;
	}
	
	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {
			Class.forName(JDBC_DRIVER);
			dbConnection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return dbConnection;
	}

}
