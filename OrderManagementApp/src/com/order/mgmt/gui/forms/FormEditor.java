/**
* This class which is a panel
* provides standard functionalities
* which are common to all the modules of the
* application - Customer, Product & SalesOrder.
*
* @author  Mohammed Abdul Aziz, Syed
* @cwid	   50143871
* @course  CSCI 531 Project
* @email   msyed6@leomail.tamuc.edu
* @version 1.0
* @since   12-01-2015
*/

//declare a package
package com.order.mgmt.gui.forms;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.order.mgmt.dao.OrderItemDAO;
import com.order.mgmt.gui.IViewer;
import com.order.mgmt.gui.IPresenter;
import com.order.mgmt.gui.IViewChanger;
import com.order.mgmt.gui.panels.EditorPanel;
import com.order.mgmt.gui.panels.OrderManagementPanel;
import com.order.mgmt.utils.IOrderManagement;
import com.order.mgmt.utils.OrderManagementHelper;

public class FormEditor extends JPanel implements ActionListener, IPresenter {

	//declare all GUI components associated with
	//basic form editor, which takes user inputs
	//irrespective of module
	private EditorPanel editorPanel;
	private final IViewChanger iViewChanger;
	private JToolBar orderManagementOptions;
	//this field stores entity code, which is unique
	//to the module, so that appropriate screen is presented to the user
	private int entityCode;

	//public constructor to bind appropriate module panel
	public FormEditor(EditorPanel editorPanel, IViewChanger iViewChanger) {
		//associates border layout with this panel
		this.setLayout(new BorderLayout());
		//create a tool bar for options such as Save, Delete, Close
		orderManagementOptions = new JToolBar();
		this.iViewChanger = iViewChanger;

		//add following options to save, delete and close
		//a customer, product or sales order
		add(orderManagementOptions, BorderLayout.PAGE_START);
		addOptionTo(IOrderManagement.SAVE, IOrderManagement._SAVE);
		addOptionTo(IOrderManagement.DELETE, IOrderManagement._DELETE);
		addOptionTo(IOrderManagement.CLOSE, IOrderManagement._CLOSE);

		this.editorPanel = editorPanel;
		//obtain the underlying entity code which describes the module
		this.entityCode = editorPanel.getEntityCode();

		//bring the menu options to the center
		add(editorPanel, BorderLayout.CENTER);
	}

	//this method ties up appropriate image icon
	//with save, delete or close option so that
	//it provides a good view
	void addOptionTo(String instruction, String instructionText) {
		String imageName = null;
		if(IOrderManagement.SAVE.equalsIgnoreCase(instruction))
			imageName = IOrderManagement.ONE;
		else if(IOrderManagement.DELETE.equalsIgnoreCase(instruction))
			imageName = IOrderManagement.TWO;
		else
			imageName = IOrderManagement.THREE;
		URL imageURL = FormEditor.class.getResource(imageName);

		//add the image on to the button
		JButton instructionButton = new JButton();
		//set the action command to each button
		instructionButton.setActionCommand(instruction);
		//set the tool tip so that user atleast see the text
		//just in case the image is not loaded or missing
		instructionButton.setToolTipText(instructionText);
		//associate the action listener to perform necessary
		//action on click of the button
		instructionButton.addActionListener(this);

		//ensure that atleast text is displayed just in case image is missing
		if (null == imageURL) {
			instructionButton.setText(instructionText);
		} else {
			//other wise set the default image icon
			ImageIcon defaultIcon = new ImageIcon(imageURL, instructionText);
			instructionButton.setIcon(defaultIcon);
		}
		//add the button to tool bar option
		orderManagementOptions.add(instructionButton);
	}

	//since the same class also acts as action listener
	//we override the actionPerformed method to take
	//necessary steps in case of user action
	public void actionPerformed(ActionEvent event) {
		String instruction = event.getActionCommand();
		//if user hits close button, redirect him to start screen/home page
		if (instruction.equals(IOrderManagement.CLOSE)) {
			startScreen();
		} else if (instruction.equals(IOrderManagement.SAVE)) {
			//if user hits save button, save the state of
			//underlying entity to the database
			Object entity = editorPanel.unWeaveObject();
			try {
				entity = OrderManagementHelper.insertEntity(entity, entityCode);
//				editorPanel.weaveObject(entity);
			} catch (Exception e) {
				e.printStackTrace();
				//display appropriate message if the entity can not be saved for some reason
				JOptionPane.showMessageDialog(this, "Record cannot be saved..!!");
			}
		} else if (instruction.equals(IOrderManagement.DELETE)) {
			//if the user selects delete action, delete corresponding record
			//from database
			boolean result = OrderManagementHelper.deleteEntity(editorPanel.getSelection(), entityCode);
			new OrderItemDAO().deleteOrderItem(editorPanel.getSelection());
			if (result) {
				//if record successfully delete, redirect the user to start screen/home page
				startScreen();
			} else {
				//otherwise, display the error message
				JOptionPane.showMessageDialog(this, "Record cannot be deleted..!!");
			}
		}
	}

	//this method returns the panel associated with
	//underlying module to the user
	public IViewer getUnderlyingPresenter() {
		return editorPanel;
	}

	//this method redirects the user to home screen
	private void startScreen() {
		iViewChanger.changeViewTo(IOrderManagement.SALES_ORDER_PANEL);
	}

}
