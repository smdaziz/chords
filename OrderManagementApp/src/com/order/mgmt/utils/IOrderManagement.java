/**
* This interface is the central place
* where are all the constants are defined.
*
* @author  Mohammed Abdul Aziz, Syed
* @cwid	   50143871
* @course  CSCI 531 Project
* @email   msyed6@leomail.tamuc.edu
* @version 1.0
* @since   12-01-2015
*/

//declare a package
package com.order.mgmt.utils;

import com.order.mgmt.dto.CustomerDTO;
import com.order.mgmt.dto.ProductDTO;
import com.order.mgmt.dto.SalesOrderDTO;
import com.order.mgmt.gui.forms.CustomerForm;
import com.order.mgmt.gui.forms.ProductForm;
import com.order.mgmt.gui.forms.SalesOrderForm;
import com.order.mgmt.gui.panels.OrderManagementPanel;

public interface IOrderManagement {

	//string constants
	public static final String CUSTOMER_FORM = CustomerForm.class.getName();
	public static final String PRODUCT_FORM = ProductForm.class.getName();
	public static final String SALES_ORDER_FORM = SalesOrderForm.class.getName();
	public static final String CUSTOMER_MODEL = CustomerDTO.class.getName();
	public static final String PRODUCT_MODEL = ProductDTO.class.getName();
	public static final String SALES_ORDER_MODEL = SalesOrderDTO.class.getName();
	public static final String SALES_ORDER_PANEL = OrderManagementPanel.class.getName();
	public static final String SAVE = "save";
	public static final String DELETE = "delete";
	public static final String CLOSE = "close";
	public static final String _SAVE = "Save";
	public static final String _DELETE = "Delete";
	public static final String _CLOSE = "Close";
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public static final String JDBC_URL = "jdbc:mysql://localhost/retail_db";
	public static final String USER_NAME = "root";
	public static final String PASSWORD = "mysql";
	public static final String ORDER_ID = "Order ID";
	public static final String CUSTOMER_ID = "Customer ID";
	public static final String TOTAL_PRICE = "Total Price";
	public static final String PRODUCT_ID = "Product ID";
	public static final String PRODUCT_NAME = "Product Name";
	public static final String SUPPLIER = "Supplier";
	public static final String PRODUCT_TYPE = "Product Type";
	public static final String UNIT_PRICE = "Unit Price";
	public static final String AVAILABLE_QTY = "Available Quantity";
	public static final String STATUS = "Status";
	public static final String CUSTOMER_NAME = "Customer Name";
	public static final String ADDRESS = "Address";
	public static final String PHONE1 = "Phone1";
	public static final String PHONE2 = "Phone2";
	public static final String PHONE_1 = "Phone 1";
	public static final String PHONE_2 = "Phone 2";
	public static final String QUANTITY = "Quantity";
	public static final String _CUSTOMER = "Customer";
	public static final String _PRODUCT = "Product";
	public static final String ADD = "Add";
	public static final String ONE = "1.png";
	public static final String TWO = "2.png";
	public static final String THREE = "3.png";
	public static final String NEW_CUSTOMER = "New Customer";
	public static final String NEW_PRODUCT = "New Product";
	public static final String NEW_SALES_ORDER = "New Sales Order";
	public static final String VIEW_PRODUCTS = "View Products";
	public static final String VIEW_CUSTOMERS = "View Customers";
	public static final String VIEW_SALES_ORDERS = "View Sales Orders";

	//integer constants
	public static final int PRODUCT = 1;
	public static final int CUSTOMER = 2;
	public static final int SALES_ORDER = 3;

}
