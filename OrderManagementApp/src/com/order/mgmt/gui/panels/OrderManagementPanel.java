/**
* This class is a place holder for all 
* the panels - Customer, Product & SalesOrder.
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

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.order.mgmt.gui.IViewChanger;
import com.order.mgmt.listeners.CustomerAction;
import com.order.mgmt.listeners.CustomersAction;
import com.order.mgmt.listeners.ProductAction;
import com.order.mgmt.listeners.ProductsAction;
import com.order.mgmt.listeners.SalesOrderAction;
import com.order.mgmt.listeners.SalesOrdersAction;
import com.order.mgmt.utils.IOrderManagement;

public class OrderManagementPanel extends JPanel {

	//the main panel which is bound to order management application window/frame
	private final IViewChanger iViewChanger;
	//there are 6 features presented in this application
	//1. Adding a New Customer
	private JButton customer = new JButton(IOrderManagement.NEW_CUSTOMER);
	//2. Adding a New Product
	private JButton product = new JButton(IOrderManagement.NEW_PRODUCT);
	//3. Placing a New Sales Order
	private JButton salesOrder = new JButton(IOrderManagement.NEW_SALES_ORDER);
	//4. View existing Products
	private JButton products = new JButton(IOrderManagement.VIEW_PRODUCTS);
	//5. View existing Customers
	private JButton customers = new JButton(IOrderManagement.VIEW_CUSTOMERS);
	//6. View existing Sales Orders
	private JButton salesOrders = new JButton(IOrderManagement.VIEW_SALES_ORDERS);

	//public constructor that constructs the main panel
	public OrderManagementPanel(IViewChanger viewChanger) {
		this.iViewChanger = viewChanger;
		
		//bind the panel to grid layout view
		//all the buttons are listed one below the other
		JPanel featuresPanel = new JPanel(new GridLayout(6, 1));

		//add appropriate actions to each of the buttons/features defined earlier
		customer.addActionListener(new CustomerAction(iViewChanger));
		product.addActionListener(new ProductAction(iViewChanger));
		salesOrder.addActionListener(new SalesOrderAction(iViewChanger));
		customers.addActionListener(new CustomersAction(iViewChanger));
		products.addActionListener(new ProductsAction(iViewChanger));
		salesOrders.addActionListener(new SalesOrdersAction(iViewChanger));

		//add the buttons to sub panel in grid layout form
		featuresPanel.add(customer);
		featuresPanel.add(customers);
		featuresPanel.add(product);
		featuresPanel.add(products);
		featuresPanel.add(salesOrder);
		featuresPanel.add(salesOrders);
		//add the sub panel to main order management panel
		add(featuresPanel);
	}

}
