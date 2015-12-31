/**
* This class provides data access operations
* across Customer entity mapped with customer table.
*
* @author  Mohammed Abdul Aziz, Syed
* @cwid	   50143871
* @course  CSCI 531 Project
* @email   msyed6@leomail.tamuc.edu
* @version 1.0
* @since   12-01-2015
*/

//declare a package
package com.order.mgmt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.order.mgmt.db.DataBase;
import com.order.mgmt.entities.CustomerBean;

public class CustomerDAO {
	
	//declare a lock variable to synchronize the
	//operations that are to be thread safe
	private final ReentrantLock reentrantLock = new ReentrantLock();
	
	//this method extracts values encapsulated in CustomerBean
	//and inserts the data into customer table
	public boolean insertCustomer(CustomerBean customer) throws Exception {
		boolean result = false;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the sql insert statement
			String sql = "insert into customer values('" + customer.getCustomerID() +
					"', '" + customer.getName() + 
					"', '" + customer.getAddress() + 
					"', " + customer.getPhone1() +
					", " + customer.getPhone2() +
					")";
			try {
				reentrantLock.lock();
				//insert the record into customer table
				result = statement.executeUpdate(sql)!=0?true:false;
			} finally {
				reentrantLock.unlock();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getMessage().startsWith("Duplicate entry"))
				updateCustomer(customer);
			else
				throw e;
		}
		return result;
	}

	//this method extracts values encapsulated in CustomerBean
	//and updates the data into customer table
	public boolean updateCustomer(CustomerBean customer) {
		boolean result = false;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the update query
			String sql = "update customer set name='" + customer.getName() +
					"', address='" + customer.getAddress() + 
					"', phone1=" + customer.getPhone1() +
					", phone2=" + customer.getPhone2() +
					" where customerid = '" + customer.getCustomerID() +
					"'";
			try {
				reentrantLock.lock();
				//update the customer table
				result = statement.executeUpdate(sql)!=0?true:false;
			} finally {
				reentrantLock.unlock();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	//this method deletes the customer record whose id is passed as parameter
	public boolean deleteCustomer(String customerID) {
		boolean result = false;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the sql delete statement
			String sql = "delete from customer where customerid = '" +
					customerID + "'";
			try {
				reentrantLock.lock();
				//delete the matching record from customer table
				result = statement.executeUpdate(sql)!=0?true:false;
			} finally {
				reentrantLock.unlock();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//this method fetches all the records from customer table
	//encapsulates them into CustomerBean, adds them to the list and return
	public List<CustomerBean> getAllCustomers() {
		List<CustomerBean> customers = null;
		try {
			//obtain database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the select query
			String sql = "select customerid, name, address, phone1, phone2 from customer";
			//obtain the results by executing the query
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				customers = new ArrayList<CustomerBean>();
			}
			do {
				try {
					reentrantLock.lock();
					//instantiate the CustomerBean and populate the values
					CustomerBean customer = new CustomerBean();
					customer.setCustomerID(resultSet.getString("customerid"));
					customer.setName(resultSet.getString("name"));
					customer.setAddress(resultSet.getString("address"));
					customer.setPhone1(resultSet.getString("phone1"));
					customer.setPhone2(resultSet.getString("phone2"));
					//add the records to in memory list
					customers.add(customer);
				} finally {
					reentrantLock.unlock();
				}
			} while(resultSet.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}

	//this method returns customer record for id passed
	public CustomerBean getCustomerById(String customerID) {
		CustomerBean customer = null;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the sql select statement
			String sql = "select customerid, name, address, phone1, phone2 from customer where customerid = '" + customerID + "'";
			//execute the sql select and obtain the results
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				try {
					reentrantLock.lock();
					//instantiate the CustomerBean and populate the values
					customer = new CustomerBean();
					customer.setCustomerID(resultSet.getString("customerid"));
					customer.setName(resultSet.getString("name"));
					customer.setAddress(resultSet.getString("address"));
					customer.setPhone1(resultSet.getString("phone1"));
					customer.setPhone2(resultSet.getString("phone2"));
				} finally {
					reentrantLock.unlock();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customer;
	}

}
