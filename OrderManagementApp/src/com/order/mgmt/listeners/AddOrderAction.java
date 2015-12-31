/**
* This class is the action listener
* for adding a new Sales Order item 
* entry.
*
* @author  Mohammed Abdul Aziz, Syed
* @cwid	   50143871
* @course  CSCI 531 Project
* @email   msyed6@leomail.tamuc.edu
* @version 1.0
* @since   12-01-2015
*/

//declare a package
package com.order.mgmt.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.order.mgmt.gui.forms.SalesOrderForm;

public class AddOrderAction implements ActionListener {
	
	private SalesOrderForm salesOrderForm;
	
	public AddOrderAction(SalesOrderForm salesOrderForm) {
		this.salesOrderForm = salesOrderForm;
	}

	//add the selected order item to the table
	public void actionPerformed(ActionEvent e) {
		salesOrderForm.enterOrder();
	}

}
