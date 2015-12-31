/**
* This class acts as a form to
* capture user input that is part
* of a SalesOrder entity.
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

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.order.mgmt.dao.OrderItemDAO;
import com.order.mgmt.dao.ProductDAO;
import com.order.mgmt.entities.OrderItemBean;
import com.order.mgmt.entities.SalesOrderBean;
import com.order.mgmt.gui.DropDownEntry;
import com.order.mgmt.gui.panels.EditorPanel;
import com.order.mgmt.listeners.AddOrderAction;
import com.order.mgmt.utils.IOrderManagement;
import com.order.mgmt.utils.OrderManagementHelper;

public class SalesOrderForm extends EditorPanel {

	//declare and instantiate the layout associated with
	//the fields displayed on SalesOrder form
	private final GridBagLayout gridBagLayout = new GridBagLayout();

	//declare the SalesOrder related labels and fields
	private JTextField orderID = new JTextField();
	private JLabel orderIDLabel = new JLabel(IOrderManagement.ORDER_ID);
	private JTextField totalPrice = new JTextField();
	private JLabel totalPriceLabel = new JLabel(IOrderManagement.TOTAL_PRICE);
	private JTextField quantity = new JTextField();
	private JLabel quantityLabel = new JLabel(IOrderManagement.QUANTITY);

	//SalesOrder related labels and fields
	private JComboBox<DropDownEntry> customers = new JComboBox<DropDownEntry>();
	private JLabel customerLabel = new JLabel(IOrderManagement._CUSTOMER);
	private JComboBox<DropDownEntry> products = new JComboBox<DropDownEntry>();
	private JLabel productLabel = new JLabel(IOrderManagement._PRODUCT);
	private JButton addOrder = new JButton(IOrderManagement.ADD);

	//declare the table model onto which the selected order items
	//are attached and displayed
	private DefaultTableModel orderTable = new DefaultTableModel(
			new String[] {IOrderManagement._PRODUCT, IOrderManagement.QUANTITY, IOrderManagement.UNIT_PRICE, IOrderManagement.TOTAL_PRICE}, 0) {
		@Override
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable order = new JTable(orderTable);
	//common insets format across all the fields attached
	//to form fields on gridbaglayout
	private Insets salesOrderInsets = new Insets(5, 5, 5, 5);

	public SalesOrderForm() {
		//set the layout for this panel
		setLayout(gridBagLayout);
		//define the field widths where ever appropriate
		orderID.setColumns(10);
		customers.setSelectedItem("<None>");
		totalPrice.setColumns(10);
		//making total price field non-editable
		totalPrice.setEditable(false);
		products.setSelectedItem("<None>");
		quantity.setColumns(10);
		order.setFillsViewportHeight(true);

		//boot the settings associated with each of the
		//fields on Sales Order form
		bootIdSettings();
		bootCustomerSettings();
		bootPriceSettings();
		bootOrderSettings();
		bootProductSettings();
		bootQuantitySettings();
		bootOrderItemSettings();

	}

	private void bootIdSettings() {
		//define constraint for sales order id label
		GridBagConstraints idGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		idGridSettings.gridx = 0;
		idGridSettings.gridy = 0;
		idGridSettings.insets = salesOrderInsets;
		//add the label to panel
		add(orderIDLabel, idGridSettings);

		//define constraint for sales order id input field
		//define the x & y location where input field is to be placed
		idGridSettings.gridx = 1;
		idGridSettings.gridy = 0;
		idGridSettings.fill = GridBagConstraints.HORIZONTAL;
		idGridSettings.gridwidth = 2;
		idGridSettings.weightx = 0.5;
		//add the input field to panel
		add(orderID, idGridSettings);
	}

	private void bootCustomerSettings() {
		//define constraint for sales order - customer label
		GridBagConstraints customerGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		customerGridSettings.gridx = 3;
		customerGridSettings.gridy = 0;
		customerGridSettings.insets = salesOrderInsets;
		//add the label to panel
		add(customerLabel, customerGridSettings);

		//define constraint for sales order - customer dropdown
		//define the x & y location where input field is to be placed
		customerGridSettings.gridx = 4;
		customerGridSettings.gridy = 0;
		customerGridSettings.fill = GridBagConstraints.HORIZONTAL;
		customerGridSettings.gridwidth = 2;
		customerGridSettings.weightx = 0.5;
		//add the input field to panel
		add(customers, customerGridSettings);
	}

	private void bootPriceSettings() {
		//define constraint for sales order item price label
		GridBagConstraints totalPriceGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		totalPriceGridSettings.gridx = 0;
		totalPriceGridSettings.gridy = 1;
		totalPriceGridSettings.insets = salesOrderInsets;
		//add the label to panel
		add(totalPriceLabel, totalPriceGridSettings);

		//define constraint for sales order total price field
		//define the x & y location where output field is to be placed
		totalPriceGridSettings.gridx = 1;
		totalPriceGridSettings.gridy = 1;
		totalPriceGridSettings.fill = GridBagConstraints.HORIZONTAL;
		totalPriceGridSettings.gridwidth = 2;
		totalPriceGridSettings.weightx = 0.5;
		//add the output field to panel
		add(totalPrice, totalPriceGridSettings);
	}

	private void bootOrderSettings() {
		//define constraint for sales order details label
		GridBagConstraints orderDetailsGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		orderDetailsGridSettings.gridx = 0;
		orderDetailsGridSettings.gridy = 2;
		orderDetailsGridSettings.gridwidth = 1;
		orderDetailsGridSettings.insets = salesOrderInsets;
		//add the label to panel
		add(new JLabel("Order Details"), orderDetailsGridSettings);

		//add a logical separator
		JSeparator separator = new JSeparator();
		//define constraint for sales order separator field
		separator.setPreferredSize(new Dimension(10, 1));
		orderDetailsGridSettings.fill = GridBagConstraints.BOTH;
		//define the x & y location where this field is to be placed
		orderDetailsGridSettings.gridx = 1;
		orderDetailsGridSettings.gridy = 2;
		orderDetailsGridSettings.gridwidth = 6;
		//add the separator to panel
		add(separator, orderDetailsGridSettings);
	}

	private void bootProductSettings() {
		//define constraint for sales order - product label
		GridBagConstraints productGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		productGridSettings.gridx = 0;
		productGridSettings.gridy = 3;
		productGridSettings.insets = salesOrderInsets;
		//add the label to panel
		add(productLabel, productGridSettings);

		//define constraint for sales order product combobox
		//define the x & y location where product combobox is to be placed
		productGridSettings.gridx = 1;
		productGridSettings.gridy = 3;
		productGridSettings.fill = GridBagConstraints.HORIZONTAL;
		productGridSettings.gridwidth = 2;
		productGridSettings.weightx = 0.5;
		//add the input field to panel
		add(products, productGridSettings);
	}

	private void bootQuantitySettings() {
		//define constraint for sales order item quantity label
		GridBagConstraints quantityGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		quantityGridSettings.gridx = 3;
		quantityGridSettings.gridy = 3;
		quantityGridSettings.insets = salesOrderInsets;
		//add the label to panel
		add(quantityLabel, quantityGridSettings);

		//define constraint for sales order quantity input field
		//define the x & y location where input field is to be placed
		quantityGridSettings.gridx = 4;
		quantityGridSettings.gridy = 3;
		quantityGridSettings.fill = GridBagConstraints.HORIZONTAL;
		quantityGridSettings.gridwidth = 2;
		quantityGridSettings.weightx = 0.5;
		//add the input field to panel
		add(quantity, quantityGridSettings);
	}

	private void bootOrderItemSettings() {
		//since the order items may exceed the limited area presented
		//on screen, add it to a scroll pane so that users can scroll and view
		//complete items ordered
		JScrollPane scrollPane = new JScrollPane(order, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		//define constraint for sales order item label
		GridBagConstraints orderGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		orderGridSettings.gridx = 0;
		orderGridSettings.gridy = 4;
		orderGridSettings.insets = new Insets(2, 5, 2, 5);
		//add the label to panel
		add(addOrder, orderGridSettings);

		//define constraint for sales order item scroll pane
		orderGridSettings.fill = GridBagConstraints.BOTH;
		//define the x & y location where order item scroll pane is to be placed
		orderGridSettings.gridwidth = 7;
		orderGridSettings.gridheight = 8;
		orderGridSettings.gridx = 0;
		orderGridSettings.weightx = 1;
		orderGridSettings.gridy = 5;
		orderGridSettings.weighty = 1;
		orderGridSettings.ipady = 40;
		//add the action listener, which takes care of
		//adding selected order to the table
		addOrder.addActionListener(new AddOrderAction(this));
		//add the input field to panel
		add(scrollPane, orderGridSettings);
	}

	//this method takes care of adding the selected order
	//to the table displayed on the screen
	public void enterOrder() {
		//retrieve selected product details
		DropDownEntry selectedProduct = (DropDownEntry) products.getSelectedItem();

		//ensure that atleast one product is selected to proceed
		if (selectedProduct == null) {
			JOptionPane.showMessageDialog(this, "Select a product to proceed");
			return;
		}

		//obtain product details for selected products
		String productID = selectedProduct.getEntryKey();
		//get the price of selected product
		double unitPrice = OrderManagementHelper.getPriceFor(productID);
		Integer selectedQuantity = 0;

		//obtain the quantity of products ordered
		try {
			selectedQuantity = Integer.parseInt(quantity.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Invalid Quantity Selected");
			return;
		}

		//keep track of total price for order
		double _totalPrice = selectedQuantity * unitPrice;
		//add a row to the table for selected product and quantity
		orderTable.addRow(new String[] { productID, "" + selectedQuantity, "" + unitPrice, "" + _totalPrice });

		//display the total price on to the screen
		double totPrice = OrderManagementHelper.extractValue(totalPrice.getText());
		totPrice += _totalPrice;
		totalPrice.setText("" + totPrice);
	}

	//extract the fields from the passed in object
	//and bind to GUI
	public boolean weaveObject(Object o) {
		SalesOrderBean salesOrder = (SalesOrderBean) o;
		orderID.setText(salesOrder.getOrderID());
		double totalPrice = 0;
		customers.addItem(new DropDownEntry(salesOrder.getCustomerID(), "<None>"));
		List<OrderItemBean> orderItems = new OrderItemDAO().getOrderItems(salesOrder.getOrderID());
		//reconstruct the table by adding the selected order items
		for(OrderItemBean orderItem : orderItems) {
			double unitPrice = orderItem.getTotalPrice()/orderItem.getQuantity();
			orderTable.addRow(new String[] { orderItem.getProductID(), "" + orderItem.getQuantity(), "" + unitPrice+"", "" + orderItem.getTotalPrice() });
			totalPrice += orderItem.getTotalPrice();
		}
		this.totalPrice.setText(totalPrice+"");
		return false;
	}

	//construct the object by extracting the values
	//from form fields and encapsulating into an object
	public Object unWeaveObject() {
		SalesOrderBean salesOrder = new SalesOrderBean();
		salesOrder.setOrderID(orderID.getText());
		salesOrder.setCustomerID(((DropDownEntry)customers.getSelectedItem()).getEntryKey());
		salesOrder.setTotalPrice(OrderManagementHelper.extractValue(totalPrice.getText()));

		ProductDAO productDAO = new ProductDAO();
		OrderItemDAO orderItemDAO = new OrderItemDAO();
		//for a selected order item, update the product table
		//and order item table accordingly
		for(int i = 0; i < orderTable.getRowCount(); i++) {
			String productID = (String) orderTable.getValueAt(i, 0);
			String quantity = (String) orderTable.getValueAt(i, 1);
			String totalPrice = (String) orderTable.getValueAt(i, 3);
			//reduce the product quantity after subtracting selected quantity
			productDAO.updateProductQuantity(productID, quantity);
			OrderItemBean orderItem = new OrderItemBean();
			orderItem.setOrderID(orderID.getText());
			orderItem.setProductID(productID);
			orderItem.setQuantity(Integer.parseInt(quantity));
			orderItem.setTotalPrice(Double.parseDouble(totalPrice));
			//insert the order items into a separate orderitem table
			orderItemDAO.insertOrderItem(orderItem);
		}

		return salesOrder;
	}

	//return the underlying entity code
	//proving that it is SalesOrder
	public int getEntityCode() {
		return IOrderManagement.SALES_ORDER;
	}

	//obtain the selected order id from
	//the sales orders listed
	public String getSelection() {
		return orderID.getText();
	}

	//re initialize the form fields for 
	//placing a new sales order
	public void release() {
		orderID.setText("");
		customers.removeAllItems();
		products.removeAllItems();
		quantity.setText("");
		totalPrice.setText("");
		orderTable.setRowCount(0);
	}

	//initialize the products and customers
	//dropdown by fetching all active products
	//and customers from database
	public void init() {
		List<DropDownEntry> _products = OrderManagementHelper.fetchCorrespondingEntities(IOrderManagement.PRODUCT);
		for (DropDownEntry item : _products)
			products.addItem(item);

		List<DropDownEntry> _customers = OrderManagementHelper.fetchCorrespondingEntities(IOrderManagement.CUSTOMER);
		for (DropDownEntry item : _customers)
			customers.addItem(item);
	}
}
