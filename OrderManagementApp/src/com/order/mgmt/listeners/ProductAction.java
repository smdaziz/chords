/**
* This class is the action listener
* for Product. Its instance
* switches the view to add a new
* Product module accordingly.
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

import com.order.mgmt.gui.IViewChanger;
import com.order.mgmt.utils.IOrderManagement;

public class ProductAction implements ActionListener {
	
	private IViewChanger iViewChanger;
	
	public ProductAction(IViewChanger iViewChanger) {
		this.iViewChanger = iViewChanger;
	}
	
	//redirect user to Product panel/input form
	public void actionPerformed(ActionEvent e) {
		iViewChanger.changeViewTo(IOrderManagement.PRODUCT_FORM);
	}

}
