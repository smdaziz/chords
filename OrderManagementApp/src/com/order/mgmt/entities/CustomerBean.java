/**
* This class maps data from customer
* table into java object of type CustomerBean.
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

//this class maps to customer table
//with columns - customerid, name, address, phone1, phone2
public class CustomerBean {

	//instance variables mapping to columns in customer table
	private String customerID;
	private String name;
	private String address;
	private String phone1;
	private String phone2;

	//public constructor
	public CustomerBean() {
		//No args constructor
	}

	//getters and setters to access private instance variables
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	
	@Override
	public String toString() {
		return "{" + customerID + ", " + name + ", " + address + ", " + phone1 + ", " + phone2 + "}";
	}

}
