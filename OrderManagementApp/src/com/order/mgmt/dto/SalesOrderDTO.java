/**
* This class maps data from database entity
* salesorder to java entity SalesOrder, 
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

import com.order.mgmt.entities.SalesOrderBean;
import com.order.mgmt.utils.IOrderManagement;
import com.order.mgmt.utils.OrderManagementHelper;

public class SalesOrderDTO extends OrderManagementDTO {

	//SalesOrder headers that can be presented to the user
	private static final String[] headers;
	
	//initialize the SalesOrder related headers
	static {
		headers = new String[] {IOrderManagement.ORDER_ID, IOrderManagement.CUSTOMER_ID, IOrderManagement.TOTAL_PRICE};
	}

	//public constructor
	public SalesOrderDTO() {
		super(headers, 0);
	}

	//return the associated entity code that proves
	//itself as SalesOrder entity
	@Override
	public int getEntityCode() {
		return IOrderManagement.SALES_ORDER;
	}

	//convert the sales order into table format
	//that is presented to the user on GUI
	@Override
	public synchronized String[][] toTable(List<Object> list) {
		String[][] salesOrderData = new String[list.size()][headers.length];
		int rowIndex = 0;
		for (Object object : list) {
			SalesOrderBean salesOrder = ((SalesOrderBean) object);
			salesOrderData[rowIndex][0] = salesOrder.getOrderID();
			salesOrderData[rowIndex][1] = salesOrder.getCustomerID();
			salesOrderData[rowIndex][2] = salesOrder.getTotalPrice() + "";
			rowIndex++;
		}
		return salesOrderData;
	}
}
