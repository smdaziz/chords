/**
* This class acts as a form to
* capture user input that is part
* of a Customer entity.
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

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.order.mgmt.entities.CustomerBean;
import com.order.mgmt.gui.panels.EditorPanel;
import com.order.mgmt.utils.IOrderManagement;
import com.order.mgmt.utils.OrderManagementHelper;

public class CustomerForm extends EditorPanel {

	//declare and instantiate the layout associated with
	//the fields displayed on Customer form
	private final GridBagLayout gridBagLayout = new GridBagLayout();

	//declare the Customer related labels and fields
	private JLabel customerIDLabel = new JLabel(IOrderManagement.CUSTOMER_ID);
	private JTextField customerID = new JTextField();
	private JLabel customerNameLabel = new JLabel(IOrderManagement.CUSTOMER_NAME);
	private JTextField customerName = new JTextField();
	private JTextField address = new JTextField();
	private JLabel addressLabel = new JLabel(IOrderManagement.ADDRESS);
	private JTextField phone1 = new JTextField();
	private JLabel phone1Label = new JLabel(IOrderManagement.PHONE_1);
	private JTextField phone2 = new JTextField();
	private JLabel phone2Label = new JLabel(IOrderManagement.PHONE_2);
	//common insets format across all the fields attached
	//to form fields on gridbaglayout
	private Insets customerFormInsets = new Insets(5, 5, 5, 5);

	public CustomerForm() {
		//set the layout for this panel
		setLayout(gridBagLayout);
		//define the field widths where ever appropriate
		customerID.setColumns(10);
		customerName.setColumns(28);
		address.setColumns(28);
		phone1.setColumns(10);
		phone2.setColumns(10);

		//boot the settings associated with each of the
		//fields on Customer form
		bootIdSettings();
		bootNameSettings();
		bootAddressSettings();
		bootPhone1Settings();
		bootPhone2Settings();
	}

	private void bootIdSettings() {
		//define constraint for customer id label
		GridBagConstraints idGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		idGridSettings.gridx = 0;
		idGridSettings.gridy = 0;
		idGridSettings.insets = customerFormInsets;
		//add the label to panel
		add(customerIDLabel, idGridSettings);

		//define constraint for customer id input field
		//define the x & y location where input field is to be placed
		idGridSettings.gridx = 1;
		idGridSettings.gridy = 0;
		//add the input field to panel
		add(customerID, idGridSettings);
	}

	private void bootNameSettings() {
		//define constraint for customer name label
		GridBagConstraints nameGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		nameGridSettings.gridx = 0;
		nameGridSettings.gridy = 1;
		nameGridSettings.insets = customerFormInsets;
		//add the label to panel
		add(customerNameLabel, nameGridSettings);

		//define constraint for customer name input field
		//define the x & y location where input field is to be placed
		nameGridSettings.gridx = 1;
		nameGridSettings.gridy = 1;
		nameGridSettings.gridwidth = 3;
		//add the input field to panel
		add(customerName, nameGridSettings);
	}

	private void bootAddressSettings() {
		//define constraint for customer address label
		GridBagConstraints addressGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		addressGridSettings.gridx = 0;
		addressGridSettings.gridy = 2;
		addressGridSettings.insets = customerFormInsets;
		//add the label to panel
		add(addressLabel, addressGridSettings);

		//define constraint for customer address input field
		//define the x & y location where input field is to be placed
		addressGridSettings.gridx = 1;
		addressGridSettings.gridy = 2;
		addressGridSettings.gridwidth = 3;
		//add the input field to panel
		add(address, addressGridSettings);
	}

	private void bootPhone1Settings() {
		//define constraint for customer phone1 label
		GridBagConstraints phone1GridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		phone1GridSettings.gridx = 0;
		phone1GridSettings.gridy = 3;
		phone1GridSettings.insets = customerFormInsets;
		//add the label to panel
		add(phone1Label, phone1GridSettings);

		//define constraint for customer phone1 input field
		//define the x & y location where input field is to be placed
		phone1GridSettings.gridx = 1;
		phone1GridSettings.gridy = 3;
		//add the input field to panel
		add(phone1, phone1GridSettings);
	}

	private void bootPhone2Settings() {
		//define constraint for customer phone2 label
		GridBagConstraints phone2GridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		phone2GridSettings.gridx = 2;
		phone2GridSettings.gridy = 3;
		phone2GridSettings.insets = customerFormInsets;
		//add the label to panel
		add(phone2Label, phone2GridSettings);

		//define constraint for customer phone2 input field
		//define the x & y location where input field is to be placed
		phone2GridSettings.gridx = 3;
		phone2GridSettings.gridy = 3;
		//add the input field to panel
		add(phone2, phone2GridSettings);
	}

	//extract the fields from the passed in object
	//and bind to GUI
	public boolean weaveObject(Object o) {
		CustomerBean customer = (CustomerBean) o;
		customerID.setText(customer.getCustomerID());
		customerName.setText(customer.getName());
		phone1.setText(customer.getPhone1());
		phone2.setText(customer.getPhone2());
		address.setText(customer.getAddress());
		return false;
	}

	//construct the object by extracting the values
	//from form fields and encapsulating into an object
	public Object unWeaveObject() {
		CustomerBean customer = new CustomerBean();
		customer.setCustomerID(customerID.getText());
		customer.setName(customerName.getText());
		customer.setAddress(address.getText());
		customer.setPhone1(phone1.getText());
		customer.setPhone2(phone2.getText());
		return customer;
	}

	//return the underlying entity code
	//proving that it is Customer
	@Override
	public int getEntityCode() {
		return IOrderManagement.CUSTOMER;
	}

	//obtain the selected customer id from
	//the products listed
	@Override
	public String getSelection() {
		return customerID.getText();
	}

	//re initialize the form fields for 
	//adding a new customer
	public void release() {
		customerID.setText("");
		customerName.setText("");
		address.setText("");
		phone1.setText("");
		phone2.setText("");
	}

	//any initialization activity in future, goes here
	public void init() {

	}
}
