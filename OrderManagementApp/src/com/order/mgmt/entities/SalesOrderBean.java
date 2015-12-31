/**
* This class maps data from salesorder
* table into java object of type SalesOrderBean.
*
* @author  Mohammed Abdul Aziz, Syed
* @cwid	   50143871
* @course  CSCI 531 Project
* @email   msyed6@leomail.tamuc.edu
* @version 1.0
* @since   12-01-2015
*/

//declare a package
package com.order.mgmt.entities;

//this class maps to salesorder table
//with columns - orderid, customerid, totalprice
public class SalesOrderBean {

	//instance variables mapping to columns in salesorder table
	private String orderID;
	private String customerID;
	private double totalPrice;

	//public constructor
	public SalesOrderBean() {
		//No args constructor
	}

	//getters and setters to access private instance variables
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
