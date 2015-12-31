/**
* This class is the action listener
* for close action/button. Clicking 
* the close button redirects the user
* to home screen, which is achieved here.
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

import com.order.mgmt.gui.panels.ViewerPanel;

public class CloseAction implements ActionListener {
	
	private ViewerPanel viewerPanel;
	
	public CloseAction(ViewerPanel viewerPanel) {
		this.viewerPanel = viewerPanel;
	}
	
	//redirect the user to home page/first screen
	public void actionPerformed(ActionEvent e) {
		viewerPanel.firstScreen();
	}

}
