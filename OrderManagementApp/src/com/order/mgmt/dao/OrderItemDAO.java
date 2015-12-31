/**
* This class provides data access operations
* across OrderItem entity mapped with orderitem table.
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
import com.order.mgmt.entities.OrderItemBean;

public class OrderItemDAO {
	
	//declare a lock variable to synchronize the
	//operations that are to be thread safe
	private final ReentrantLock reentrantLock = new ReentrantLock();
	
	//this method extracts values encapsulated in OrderItemBean
	//and inserts the data into orderitem table
	public boolean insertOrderItem(OrderItemBean orderItem) {
		boolean result = false;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the insert sql query
			String sql = "insert into orderitem values(0, '" + 
							orderItem.getProductID() +
					"', " + orderItem.getQuantity() +  
					", " + orderItem.getTotalPrice() + 
					", '" + orderItem.getOrderID() + 
					"')";
			try {
				reentrantLock.lock();
				//insert the data into orderitem table
				result = statement.executeUpdate(sql)!=0?true:false;
			} finally {
				reentrantLock.unlock();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getMessage().startsWith("Duplicate entry"))
				updateOrderItem(orderItem);
			else
				e.printStackTrace();
		}
		return result;
	}

	//this method extracts values encapsulated in OrderItemBean
	//and update the data into salesorder table
	public boolean updateOrderItem(OrderItemBean orderItem) {
		boolean result = false;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the sql update query
			String sql = "update orderitem set productid='" + orderItem.getProductID() +
					"', quantity=" + orderItem.getQuantity() + 
					", totalprice=" + orderItem.getTotalPrice() +
					" where orderid = '" + orderItem.getOrderID() +
					"' and orderitemid = " + orderItem.getOrderItemID();
			try {
				reentrantLock.lock();
				//update the orderitem table
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

	//this method deletes the orderitem whose id is passed as parameter
	public boolean deleteOrderItem(String orderItemID, String orderID) {
		boolean result = false;
		try {
			//obtain a database connection and create statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the delete sql query
			String sql = "delete from orderitem where orderid = '" +
					orderID + "' and orderitemid = " + orderItemID;
			try {
				reentrantLock.lock();
				//delete the record from orderitem table
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
	
	//this method deletes the orderitem whose id is passed as parameter
	public boolean deleteOrderItem(String orderID) {
		boolean result = false;
		try {
			//obtain a database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the sql delete query
			String sql = "delete from orderitem where orderid = '" +
					orderID + "'";
			try {
				reentrantLock.lock();
				//delete the record from orderitem table
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
	
	//this method fetches all the records from orderitem table
	//encapsulates them into OrderItemBean, adds them to the list and return
	public List<OrderItemBean> getAllOrderItems() {
		List<OrderItemBean> orderItems = null;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the sql select query
			String sql = "select distinct orderitemid, productid, quantity, totalprice, orderid from customer";
			//fire the sql query
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				orderItems = new ArrayList<OrderItemBean>();
			}
			do {
				try {
					reentrantLock.lock();
					//instantiate the OrderItemBean and populate the values
					OrderItemBean orderItem = new OrderItemBean();
					orderItem.setOrderItemID(resultSet.getString("orderitemid"));
					orderItem.setProductID(resultSet.getString("productid"));
					orderItem.setQuantity(Integer.parseInt(resultSet.getString("quantity")));
					orderItem.setTotalPrice(Double.parseDouble(resultSet.getString("totalprice")));
					orderItem.setOrderID(resultSet.getString("orderid"));
					//add the record to in memory list
					orderItems.add(orderItem);
				} finally {
					reentrantLock.unlock();
				}
			} while(resultSet.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}

	//this method fetches all the order items associated with order id passed
	//encapsulates them into OrderItemBean, adds them to the list and return
	public List<OrderItemBean> getOrderItems(String orderID) {
		List<OrderItemBean> orderItems = null;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the sql select query
			String sql = "select distinct orderitemid, productid, quantity, totalprice, orderid from orderitem where orderid = '" + orderID + "'";
			//fire the query and obtain the results
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				orderItems = new ArrayList<OrderItemBean>();
			}
			do {
				try {
					reentrantLock.lock();
					//instantiate the OrderItem bean and populate the values
					OrderItemBean orderItem = new OrderItemBean();
					orderItem.setOrderItemID(resultSet.getString("orderitemid"));
					orderItem.setProductID(resultSet.getString("productid"));
					orderItem.setQuantity(Integer.parseInt(resultSet.getString("quantity")));
					orderItem.setTotalPrice(Double.parseDouble(resultSet.getString("totalprice")));
					orderItem.setOrderID(resultSet.getString("orderid"));
					//add the record to the in memory list
					orderItems.add(orderItem);
				} finally {
					reentrantLock.unlock();
				}
			} while(resultSet.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}

	//get the order item from orderitem table for the passed in orderid and
	//orderitemid, encapsulate into the bean and return
	public OrderItemBean getOrderItemById(String orderItemID, String orderID) {
		OrderItemBean orderItem = null;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the sql select statemnt to obtain matching records
			String sql = "select distinct orderitemid, productid, quantity, totalprice, orderid from orderitem where orderitemid = " + orderItemID + "and orderid='" + orderID + "'";
			try {
				reentrantLock.lock();
				//fire the query and obtain the results
				ResultSet resultSet = statement.executeQuery(sql);
				if(resultSet.next()) {
					//instantiate the OrderItemBean, populate the values and return
					orderItem = new OrderItemBean();
					orderItem.setOrderItemID(resultSet.getString("orderitemid"));
					orderItem.setProductID(resultSet.getString("productid"));
					orderItem.setQuantity(Integer.parseInt(resultSet.getString("quantity")));
					orderItem.setTotalPrice(Double.parseDouble(resultSet.getString("totalprice")));
					orderItem.setOrderID(resultSet.getString("orderid"));
				}
			} finally {
				reentrantLock.unlock();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItem;
	}

}
