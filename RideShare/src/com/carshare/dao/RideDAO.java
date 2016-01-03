package com.carshare.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.carshare.beans.Ride;

public class RideDAO {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/carshare";
	
	private static final String DB_USER = "root";
	private static final String DB_PASS = "mysql";
	
	public boolean insertRide(Ride ride) {
		
		Connection connection = null;
		Statement statement = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date myDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(myDate.getTime());
		String insertRideSQL = "INSERT INTO RIDE(username, source, dest, hh, mm, passengers, srclat, srclng, destlat, destlng, ridedate) VALUES" +
				"('" +
				ride.getUserName() + "', '" +ride.getSource() + "', '" + ride.getDest() + "', " + 
				ride.getHh() + ", " + ride.getMm() + ", " + ride.getPassengers() + ", '" + 
				ride.getSrcLat() + "', '" + ride.getSrcLng() + "', '" + ride.getDestLat() + "', '" + 
				ride.getDestLng() + "', '" + date + "')";
		System.out.println(insertRideSQL);
		boolean recordInserted = false;
		
		try {
			connection = getDBConnection();
			statement = connection.createStatement();
			statement.executeUpdate(insertRideSQL);
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
