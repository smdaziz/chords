/**
* This class is abstract which is implemented
* by Form editor to fetch the entity/module
* in play and retrieve the current selection.
*
* @author  Mohammed Abdul Aziz, Syed
* @cwid	   50143871
* @course  CSCI 531 Project
* @email   msyed6@leomail.tamuc.edu
* @version 1.0
* @since   12-01-2015
*/

//declare a package
package com.order.mgmt.gui.panels;

import javax.swing.JPanel;

import com.order.mgmt.gui.IViewer;

public abstract class EditorPanel extends JPanel implements IViewer {

	//this method is implemented by appropriate forms
	//which provide option to edit respective modules
	//Customer/Product/SalesOrder by returning assigned code
	public abstract int getEntityCode();
	
	//obtain the selected entity (Customer/Product/SalesOrder)
	//id from the available listing
	public abstract String getSelection();

}
