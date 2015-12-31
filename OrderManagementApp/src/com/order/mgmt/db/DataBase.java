/**
* This class provides standard access to DB
* connection. This is the centrailzed class
* through which all database management happens.
*
* @author  Mohammed Abdul Aziz, Syed
* @cwid	   50143871
* @course  CSCI 531 Project
* @email   msyed6@leomail.tamuc.edu
* @version 1.0
* @since   12-01-2015
*/

//declare a package
package com.order.mgmt.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

import com.order.mgmt.utils.IOrderManagement;

public class DataBase {
	
	//declare a lock variable to synchronize the
	//operations that are to be thread safe
	private static final ReentrantLock reentrantLock = new ReentrantLock();

	//JDBC Driver Name and DataBase URL
	static final String jdbcDriver;
	static final String dbURL;

	//Database UserName and Password
	static final String userName;
	static final String password;
	
	//initialize the driver name, db url
	//user name and password during class loading
	static {
		jdbcDriver = IOrderManagement.JDBC_DRIVER;
		dbURL = IOrderManagement.JDBC_URL;
		userName = IOrderManagement.USER_NAME;
		password = IOrderManagement.PASSWORD;
	}
	
	//ensure that the class can not be instantiated
	private DataBase() {
		
	}

	//this method loads appropriate JDBC driver
	//establishes the DB connection by setting the
	//transactions to auto commit and return
	public static Connection getConnection() {
		Connection connection = null;
		try {
			//load the driver
			Class.forName(jdbcDriver);
			try {
				reentrantLock.lock();
				//establish connection
				connection = DriverManager.getConnection(dbURL, userName, password);
				//ensure that transactions are auto-committed
				connection.setAutoCommit(true);
			} finally {
				reentrantLock.unlock();
			}
		} catch(SQLException exception) {
			System.out.println("Error occured while accessing DataBase : " 
					+ dbURL + " with credentials User Name : " 
					+ userName + " Password : " + password);
		} catch(Exception exception) {
			
		} finally {
			//this can be extended to add some code
			//that can be executed during application shutdown
			//and release the connection
		}
		return connection;
	}

}
