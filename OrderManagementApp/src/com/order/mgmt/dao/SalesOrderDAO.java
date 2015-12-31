/**
* This class provides data access operations
* across SalesOrder entity mapped with salesorder table.
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
import com.order.mgmt.entities.SalesOrderBean;

public class SalesOrderDAO {

	//declare a lock variable to synchronize the
	//operations that are to be thread safe
	private final ReentrantLock reentrantLock = new ReentrantLock();
	
	//this method extracts values encapsulated in SalesOrderBean
	//and inserts the data into salesorder table
	public boolean insertSalesOrder(SalesOrderBean salesOrder) throws Exception {
		boolean result = false;
		try {
			//obtain database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the sql statement
			String sql = "insert into salesorder values('" + salesOrder.getOrderID() +
					"', '" + salesOrder.getCustomerID() +
					"', " + salesOrder.getTotalPrice() +
					")";
			try {
				reentrantLock.lock();
				//insert the values
				result = statement.executeUpdate(sql)!=0?true:false;
			} finally {
				reentrantLock.unlock();
			}
		} catch (SQLException e) {
			//if the record already exists, try to update it
			if(e.getMessage().startsWith("Duplicate entry"))
				updateSalesOrder(salesOrder);
			else
				throw e;
		}
		return result;
	}

	//this method extracts values encapsulated in SalesOrderBean
	//and updates the data into salesorder table
	public boolean updateSalesOrder(SalesOrderBean salesOrder) {
		boolean result = false;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the update sql
			String sql = "update salesorder set orderid='" + salesOrder.getOrderID() +
					"', customerid='" + salesOrder.getCustomerID() + 
					"', totalprice=" + salesOrder.getTotalPrice() +
					" where orderid='" + salesOrder.getOrderID() +
					"' and customerid = '" + salesOrder.getCustomerID() +
					"'";
			try {
				reentrantLock.lock();
				//update the table
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

	//this method deletes the record in salesorder
	//table associated with salesorder id passed
	public boolean deleteSalesOrder(String orderID, String customerID) {
		boolean result = false;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the delete sql
			String sql = "delete from salesorder where customerid = '" +
					customerID + "' and orderid = '" + orderID + "'";
			try {
				reentrantLock.lock();
				//delete the record from database
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

	//this method deletes the record in salesorder
	//table associated with the orderid passed
	public boolean deleteSalesOrder(String orderID) {
		boolean result = false;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the delete sql
			String sql = "delete from salesorder where orderid = '" + orderID + "'";
			try {
				reentrantLock.lock();
				//delete the record from database
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
	
	//this method fetches all the records from salesorder table
	//encapsulates them into SalesOrderBean, adds them to the list and return
	public List<SalesOrderBean> getAllSalesOrders() {
		List<SalesOrderBean> salesOrders = null;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the select query
			String sql = "select orderid, customerid, totalprice from salesorder";
			//fetch the records/results
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				salesOrders = new ArrayList<SalesOrderBean>();
			}
			do {
				try {
					reentrantLock.lock();
					//construct the bean and populate the values
					SalesOrderBean salesOrder = new SalesOrderBean();
					salesOrder.setOrderID(resultSet.getString("orderid"));
					salesOrder.setCustomerID(resultSet.getString("customerid"));
					salesOrder.setTotalPrice(Double.parseDouble(resultSet.getString("totalprice")));
					//add the objects to the list to be returned
					salesOrders.add(salesOrder);
				} finally {
					reentrantLock.unlock();
				}
			} while(resultSet.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salesOrders;
	}

	//this method fetches a particular sales order associated with the order id
	//encapsulates the data into SalesOrderBean object and returns
	public SalesOrderBean getSalesOrderById(String orderID, String customerID) {
		SalesOrderBean salesOrder = null;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the select sql
			String sql = "select orderid, customerid, totalprice from salesorder " +
					"where orderid = '" + orderID +
					"' and customerid = '" + customerID + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				try {
					reentrantLock.lock();
					//instantiate the bean and populate the values
					salesOrder = new SalesOrderBean();
					salesOrder.setOrderID(resultSet.getString("orderid"));
					salesOrder.setCustomerID(resultSet.getString("customerid"));
					salesOrder.setTotalPrice(Double.parseDouble(resultSet.getString("totalprice")));
				} finally {
					reentrantLock.unlock();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salesOrder;
	}

	//this method fetches a particular sales order associated with the order id
	//encapsulates the data into SalesOrderBean object and returns
	public SalesOrderBean getSalesOrderById(String orderID) {
		SalesOrderBean salesOrder = null;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the select sql
			String sql = "select orderid, customerid, totalprice from salesorder " +
					"where orderid = '" + orderID + "'";
			try {
				reentrantLock.lock();
				ResultSet resultSet = statement.executeQuery(sql);
				if(resultSet.next()) {
					//instantiate the SalesOrderBean and populate the values
					salesOrder = new SalesOrderBean();
					salesOrder.setOrderID(resultSet.getString("orderid"));
					salesOrder.setCustomerID(resultSet.getString("customerid"));
					salesOrder.setTotalPrice(Double.parseDouble(resultSet.getString("totalprice")));
				}
			} finally {
				reentrantLock.unlock();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return salesOrder;
	}

}
