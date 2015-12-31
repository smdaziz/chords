/**
* This class maps data from database entity
* customer to java entity Customer, 
* binding and unbinding content on GUIs.
*
* @author  Mohammed Abdul Aziz, Syed
* @cwid	   50143871
* @course  CSCI 531 Project
* @email   msyed6@leomail.tamuc.edu
* @version 1.0
* @since   12-01-2015
*/

//declare a package
package com.order.mgmt.dto;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.order.mgmt.entities.CustomerBean;
import com.order.mgmt.utils.IOrderManagement;
import com.order.mgmt.utils.OrderManagementHelper;

public class CustomerDTO extends OrderManagementDTO {

	//declare a lock variable to synchronize the
	//operations that are to be thread safe
	private final ReentrantLock reentrantLock = new ReentrantLock();
	
	//Customer headers that can be presented to the user
	private static final String headers[];
	
	//initialize the Customer related headers
	static {
		headers = new String[] {IOrderManagement.CUSTOMER_ID, IOrderManagement.CUSTOMER_NAME, IOrderManagement.ADDRESS, IOrderManagement.PHONE1, IOrderManagement.PHONE2};
	}

	//public constructor
	public CustomerDTO() {
		super(headers, 0);
	}

	//return the associated entity code that proves
	//itself as Customer entity
	@Override
	public int getEntityCode() {
		return IOrderManagement.CUSTOMER;
	}

	//convert the customer information into table format
	//that is presented to the user on GUI
	@Override
	public String[][] toTable(List<Object> list) {
		String[][] customers = new String[list.size()][headers.length];
		try {
			reentrantLock.lock();
			int rowIndex = 0;
			for (Object object : list) {
				CustomerBean customer = (CustomerBean) object;
				customers[rowIndex][0] = customer.getCustomerID();
				customers[rowIndex][1] = customer.getName();
				customers[rowIndex][2] = customer.getAddress();
				customers[rowIndex][3] = customer.getPhone1();
				customers[rowIndex][4] = customer.getPhone2();
				rowIndex++;
			}
		} finally {
			reentrantLock.unlock();
		}
		return customers;
	}
}
