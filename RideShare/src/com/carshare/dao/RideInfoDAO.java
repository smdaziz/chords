package com.carshare.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.carshare.beans.RideInfo;

public class RideInfoDAO {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost/carshare";
	
	private static final String DB_USER = "root";
	private static final String DB_PASS = "mysql";
	
	public List<RideInfo> getAvailableRides() {
		
		Connection connection = null;
		Statement statement = null;
		String selectRideInfoSQL = "SELECT source, dest, hh, mm, passengers, " +
				"srcLat, srcLng, destLat, destLng, " +
				"r.username as drivername, u.mobile as mobile, u.email as email " +
				"FROM ride r, user u WHERE " +
				"u.username = r.username and r.ridedate = curdate();";
		System.out.println(selectRideInfoSQL);
		List<RideInfo> ridesAvailable = null;
		
		try {
			connection = getDBConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectRideInfoSQL);
			while(resultSet.next()) {
				if(ridesAvailable == null)
					ridesAvailable = new ArrayList<RideInfo>();
				RideInfo rideInfo = new RideInfo();
				rideInfo.setDriverName(resultSet.getString("drivername"));
				rideInfo.setSource(resultSet.getString("source"));
				rideInfo.setDest(resultSet.getString("dest"));
				rideInfo.setStartTime(resultSet.getInt("hh")+":"+resultSet.getInt("mm"));
				rideInfo.setPassengers(resultSet.getInt("passengers"));
				rideInfo.setMobile(resultSet.getString("mobile"));
				rideInfo.setEmail(resultSet.getString("email"));
				rideInfo.setSrcLat(resultSet.getString("srcLat"));
				rideInfo.setSrcLng(resultSet.getString("srcLng"));
				rideInfo.setDestLat(resultSet.getString("destLat"));
				rideInfo.setDestLng(resultSet.getString("destLng"));
				ridesAvailable.add(rideInfo);
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

		return ridesAvailable;
	}
	
	public List<RideInfo> getAvailableRidesForUser(String userName) {
		
		Connection connection = null;
		Statement statement = null;
		String selectRideInfoSQL = "SELECT source, dest, hh, mm, passengers, " +
				"srcLat, srcLng, destLat, destLng, " +
				"r.username as drivername, u.mobile as mobile, u.email as email " +
				"FROM ride r, user u WHERE " +
				"u.username = r.username and r.username = '"+userName+"' and r.ridedate = curdate();";
		System.out.println(selectRideInfoSQL);
		List<RideInfo> ridesAvailable = null;
		
		try {
			connection = getDBConnection();
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(selectRideInfoSQL);
			while(resultSet.next()) {
				if(ridesAvailable == null)
					ridesAvailable = new ArrayList<RideInfo>();
				RideInfo rideInfo = new RideInfo();
				rideInfo.setDriverName(resultSet.getString("drivername"));
				rideInfo.setSource(resultSet.getString("source"));
				rideInfo.setDest(resultSet.getString("dest"));
				rideInfo.setStartTime(resultSet.getInt("hh")+":"+resultSet.getInt("mm"));
				rideInfo.setPassengers(resultSet.getInt("passengers"));
				rideInfo.setMobile(resultSet.getString("mobile"));
				rideInfo.setEmail(resultSet.getString("email"));
				rideInfo.setSrcLat(resultSet.getString("srcLat"));
				rideInfo.setSrcLng(resultSet.getString("srcLng"));
				rideInfo.setDestLat(resultSet.getString("destLat"));
				rideInfo.setDestLng(resultSet.getString("destLng"));
				ridesAvailable.add(rideInfo);
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

		return ridesAvailable;
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
