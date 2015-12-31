/**
* This class is the main class,
* which kick starts the order management
* application. It bind together all the modules,
* associates the views and triggers the thread.
*
* @author  Mohammed Abdul Aziz, Syed
* @cwid	   50143871
* @course  CSCI 531 Project
* @email   msyed6@leomail.tamuc.edu
* @version 1.0
* @since   12-01-2015
*/

//declare a package
package com.order.mgmt.main;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.order.mgmt.dto.CustomerDTO;
import com.order.mgmt.dto.ProductDTO;
import com.order.mgmt.dto.SalesOrderDTO;
import com.order.mgmt.gui.IPresenter;
import com.order.mgmt.gui.IViewChanger;
import com.order.mgmt.gui.forms.CustomerForm;
import com.order.mgmt.gui.forms.FormEditor;
import com.order.mgmt.gui.forms.ProductForm;
import com.order.mgmt.gui.forms.SalesOrderForm;
import com.order.mgmt.gui.panels.OrderManagementPanel;
import com.order.mgmt.gui.panels.ViewerPanel;
import com.order.mgmt.utils.IOrderManagement;

public class OrderManagementApp implements IViewChanger {

	//this map stores the module name
	//and corresponding panel instance
	private static final HashMap<String, IPresenter> presentationMapper;;
	//declare the main frame
	private JFrame orderManagementFrame;
	//declare the main panel
	private JPanel orderManagementPanel;
	//declare product module related components
	private static final FormEditor productEditor;
	private static final ProductDTO product = new ProductDTO();
	private static final ProductForm productForm = new ProductForm();
	private static final OrderManagementApp orderManagementAppWindow;
	//declare customer module related components
	private static final FormEditor customerEditor;
	private static final CustomerDTO customer = new CustomerDTO();
	private static final CustomerForm customerForm = new CustomerForm();
	private static final OrderManagementPanel orderManagementAppPanel;
	//declare sales order module related components
	private static final FormEditor salesOrderEditor;
	private static final SalesOrderDTO salesOrder = new SalesOrderDTO();
	private static final SalesOrderForm salesOrderForm = new SalesOrderForm();
	
	static {
		presentationMapper = new HashMap<String, IPresenter>();
		orderManagementAppWindow = new OrderManagementApp();
		//instantiate and initialize product module
		productEditor = new FormEditor(productForm, orderManagementAppWindow);
		//instantiate and initialize customer module
		customerEditor = new FormEditor(customerForm, orderManagementAppWindow);
		//instantiate and initialize sales order module
		salesOrderEditor = new FormEditor(salesOrderForm, orderManagementAppWindow);
		orderManagementAppPanel = new OrderManagementPanel(orderManagementAppWindow);
		orderManagementAppWindow.orderManagementPanel.add(orderManagementAppPanel, IOrderManagement.SALES_ORDER_PANEL);
	}

	public OrderManagementApp() {
		//construct the main window
		orderManagementFrame = new JFrame("Order Management Application - TAMUC");
		//close the main window on exit, by default
		orderManagementFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set the boundaries for main application window
		orderManagementFrame.setBounds(450, 125, 500, 500);
		//associate the main panel with card layout
		orderManagementPanel = new JPanel(new CardLayout());
		//add the panel to main frame/window
		orderManagementFrame.add(orderManagementPanel);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//bind the product, customer and sales order modules to main window
					orderManagementAppWindow.refreshPanel(productEditor, IOrderManagement.PRODUCT_FORM);
					orderManagementAppWindow.refreshPanel(customerEditor, IOrderManagement.CUSTOMER_FORM);
					orderManagementAppWindow.refreshPanel(salesOrderEditor, IOrderManagement.SALES_ORDER_FORM);
					//bind the models associated with each of the modules to the view
					orderManagementAppWindow.refreshPanel(new ViewerPanel(orderManagementAppWindow, customer), IOrderManagement.CUSTOMER_MODEL);
					orderManagementAppWindow.refreshPanel(new ViewerPanel(orderManagementAppWindow, product), IOrderManagement.PRODUCT_MODEL);
					orderManagementAppWindow.refreshPanel(new ViewerPanel(orderManagementAppWindow, salesOrder), IOrderManagement.SALES_ORDER_MODEL);
					//ensure that the main window is visible
					orderManagementAppWindow.orderManagementFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//refresh the panel with appropriate view/module
	void refreshPanel(IPresenter iPresenter, String _id) {
		presentationMapper.put(_id, iPresenter);
		//add this view - sub panel to main panel
		orderManagementPanel.add((Component) iPresenter, _id);
	}

	//toggle between various views/modules
	//by safely resetting the current panel
	//and reinitializing the new panel
	public void changeViewTo(String _id) {
		IPresenter iPresenter = getViewCorrespondingTo(_id);
		if (iPresenter != null) {
			//release previous panel safely
			iPresenter.getUnderlyingPresenter().release();
			//initialize new panel
			iPresenter.getUnderlyingPresenter().init();
		}
		//present the newly toggled panel to user
		((CardLayout) orderManagementPanel.getLayout()).show(orderManagementPanel, _id);
	}

	//get the panel mapped with _id
	public IPresenter getViewCorrespondingTo(String _id) {
		return presentationMapper.get(_id);
	}
}
