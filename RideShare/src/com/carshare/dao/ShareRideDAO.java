package com.carshare.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.carshare.beans.ShareRide;

public class ShareRideDAO {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/carshare";
	
	private static final String DB_USER = "root";
	private static final String DB_PASS = "mysql";
	
	public boolean insertShareRide(ShareRide shareRide) {
		
		Connection connection = null;
		Statement statement = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date myDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(myDate.getTime());
		String insertShareRideSQL = "INSERT INTO SHARERIDE(username, rideby, ridedate) VALUES" +
				"('" +
				shareRide.getUserName() + "', '" + shareRide.getRideBy() + "', '" + date + "')";
		System.out.println(insertShareRideSQL);
		String updateRideSQL = "UPDATE RIDE SET passengers = passengers + 1 where ridedate = curdate() and username = '" + shareRide.getRideBy() + "'";
		boolean transactionSuccesful = false;
		System.out.println(updateRideSQL);
		
		try {
			connection = getDBConnection();
			statement = connection.createStatement();
			statement.executeUpdate(insertShareRideSQL);
			statement.executeUpdate(updateRideSQL);
			transactionSuccesful = true;
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

		return transactionSuccesful;
	}
	
	public String getShareRideDetails(String userName) {
		
		Connection connection = null;
		Statement statement = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date myDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(myDate.getTime());
		String rideDetailsSQL = "SELECT r.username, r.source, r.dest, u.mobile, u.email " +
				"FROM ride r, user u WHERE " +
				"r.username = (SELECT rideby " +
								"FROM shareride WHERE " +
								"username = '"+userName+"' " +
								"and ridedate = CURDATE()) AND r.username = u.username;";
		System.out.println(rideDetailsSQL);
		String details = null;
		
		try {
			connection = getDBConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(rideDetailsSQL);
			while(resultSet.next()) {
				details = "<b>Ride provider name : </b>"+resultSet.getString("userName")+"<br>"+
							"<b>Ride provider Start location : </b>"+resultSet.getString("source")+"<br>"+
							"<b>Ride provider Destination : </b>"+resultSet.getString("dest")+"<br>"+
							"<b>Ride provider Mobile : </b>"+resultSet.getString("mobile")+"<br>"+
							"<b>Ride provider Email : </b>"+resultSet.getString("email")+"<br>";
			}
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

		return details;
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
