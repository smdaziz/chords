/**
* This class maps data from orderitem
* table into java object of type OrderItemBean.
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

//this class maps to orderitem table
//with columns - orderitemid, productid, quantity, totalprice, orderid
public class OrderItemBean {

	//instance variables mapping to columns in orderitem table
	private String orderItemID;
	private String productID;
	private int quantity;
	private double totalPrice;
	private String orderID;

	//public constructor
	public OrderItemBean() {
		//No args constructor
	}

	//getters and setters to access private instance variables
	public String getOrderItemID() {
		return orderItemID;
	}

	public void setOrderItemID(String orderItemID) {
		this.orderItemID = orderItemID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

}
