/**
* This class is the action listener
* for Products. Its instance
* switches the view to Products module
* accordingly.
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

public class ProductsAction implements ActionListener {
	
	private IViewChanger iViewChanger;
	
	public ProductsAction(IViewChanger iViewChanger) {
		this.iViewChanger = iViewChanger;
	}
	
	//redirect user to Product panel
	public void actionPerformed(ActionEvent e) {
		iViewChanger.changeViewTo(IOrderManagement.PRODUCT_MODEL);
	}

}
