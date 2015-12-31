/**
* This class provides all the utility 
* functions such as inserting an entity,
* deleting, updating in addition to parsing
* text from user input to appropriate values.
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

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

import com.order.mgmt.dao.CustomerDAO;
import com.order.mgmt.dao.ProductDAO;
import com.order.mgmt.dao.SalesOrderDAO;
import com.order.mgmt.entities.CustomerBean;
import com.order.mgmt.entities.ProductBean;
import com.order.mgmt.entities.SalesOrderBean;
import com.order.mgmt.gui.DropDownEntry;

public class OrderManagementHelper {
	
	//declare a lock variable to synchronize the
	//operations that are to be thread safe
	//for each of product, customer and salesorder entities
	private static final ReentrantLock productLock = new ReentrantLock();
	private static final ReentrantLock customerLock = new ReentrantLock();
	private static final ReentrantLock salesOrderLock = new ReentrantLock();

	//this method fetches and returns all the records
	//associated with the entityCode passed
	public static List<Object> fetchAllEntities(int entityCode) {
		List<Object> records = new ArrayList<Object>();
		switch (entityCode) {
		//return all products present in database
		case IOrderManagement.PRODUCT:
			try {
				productLock.lock();
				for (ProductBean product : new ProductDAO().getAllProducts())
					records.add(product);
			} finally {
				productLock.unlock();
			}
			break;
		//return all customers present in database
		case IOrderManagement.CUSTOMER:
			try {
				customerLock.lock();
				for (CustomerBean customer : new CustomerDAO().getAllCustomers())
					records.add(customer);
			} finally {
				customerLock.unlock();
			}
			break;
		//return all sales orders present in database
		case IOrderManagement.SALES_ORDER:
			try {
				salesOrderLock.lock();
				for (SalesOrderBean salesOrder : new SalesOrderDAO().getAllSalesOrders())
			records.add(salesOrder);
			} finally {
				salesOrderLock.unlock();
			}
			break;
		}

		return records;
	}

	//this method inserts/persists an entity
	//associated with the entityCode passed
	public static Object insertEntity(Object entity, int entityCode) throws Exception {
		switch (entityCode) {
			//save product information in database
			case IOrderManagement.PRODUCT:
				try {
					productLock.lock();
					new ProductDAO().insertProduct((ProductBean) entity);
				} finally {
					productLock.unlock();
				}
				break;
			//save customer information in database
			case IOrderManagement.CUSTOMER:
				try {
					customerLock.lock();
					new CustomerDAO().insertCustomer((CustomerBean) entity);
				} finally {
					customerLock.unlock();
				}
				break;
			//save sales order information in database
			case IOrderManagement.SALES_ORDER:
				try {
					salesOrderLock.lock();
					new SalesOrderDAO().insertSalesOrder((SalesOrderBean) entity);
				} finally {
					salesOrderLock.unlock();
				}
				break;
		}
		return entity;
	}

	//this method deletes an entity
	//associated with the entityCode passed
	public static boolean deleteEntity(String _id, int entityCode) {
		switch (entityCode) {
		//delete product information corresponding to the id passed
		case IOrderManagement.PRODUCT:
			try {
				productLock.lock();
				new ProductDAO().deleteProduct(_id);
			} finally {
				productLock.unlock();
			}
			break;
		//delete customer information corresponding to the id passed
		case IOrderManagement.CUSTOMER:
			try {
				customerLock.lock();
				new CustomerDAO().deleteCustomer(_id);
			} finally {
				customerLock.unlock();
			}
			break;
		//delete sales order information corresponding to the id passed
		case IOrderManagement.SALES_ORDER:
			try {
				salesOrderLock.lock();
				new SalesOrderDAO().deleteSalesOrder(_id);
			} finally {
				salesOrderLock.unlock();
			}
			break;
		}
		return true;
	}

	//extract the double value from text/string field present on form/GUI
	public static Double extractValue(String _value) {
		try {
			//if value is null return 0
			if (null == _value)
				return 0D;
			//if value is empty, return 0
			else if ("".equalsIgnoreCase(_value) || _value.trim().length() == 0)
				return 0D;
			//otherwise parse and extract the value
			else
				return Double.parseDouble(_value);
		} catch (Exception e) {
			//incase of exceptions, return 0
			return 0D;
		}
	}

	//this method fetches and returns the entity matching 
	//the passed in id, associated with the entityCode passed
	public static Object fetchEntity(String _id, int entityCode) {
		Object object = null;
		switch (entityCode) {
		//return the matching product record, by encapsulating into an object
		case IOrderManagement.PRODUCT:
			try {
				productLock.lock();
				object = new ProductDAO().getProductById(_id);
			} finally {
				productLock.unlock();
			}
			break;
		//return the matching customer record, by encapsulating into an object
		case IOrderManagement.CUSTOMER:
			try {
				customerLock.lock();
				object = new CustomerDAO().getCustomerById(_id);
			} finally {
				customerLock.unlock();
			}
			break;
		//return the matching sales order record, by encapsulating into an object
		case IOrderManagement.SALES_ORDER:
			try {
				salesOrderLock.lock();
				object = new SalesOrderDAO().getSalesOrderById(_id);
			} finally {
				salesOrderLock.unlock();
			}
			break;
		}
		return object;
	}

	//return the price for product id passed
	public static double getPriceFor(String productID) {
		 return new ProductDAO().getPrice(productID);
	}

	//this method returns all the records present in the table
	//matching the entityCode
	public static List<DropDownEntry> fetchCorrespondingEntities(int entityCode) {
		List<DropDownEntry> dropDownItems = null;

		switch (entityCode) {
		//return all the products information present in database
		case IOrderManagement.PRODUCT:
			try {
				productLock.lock();
				for (ProductBean product : new ProductDAO().getAllProducts()) {
					if (dropDownItems == null)
						dropDownItems = new ArrayList<DropDownEntry>();
					//add each product to the dropdown
					dropDownItems.add(new DropDownEntry(product.getProductID(),
							product.getName()));
				}
			} finally {
				productLock.unlock();
			}
			break;
		//return all the customers information present in database
		case IOrderManagement.CUSTOMER:
			try {
				customerLock.lock();
				for (CustomerBean customer : new CustomerDAO().getAllCustomers()) {
					if (dropDownItems == null)
						dropDownItems = new ArrayList<DropDownEntry>();
					//add each customer to the dropdown
					dropDownItems.add(new DropDownEntry(customer.getCustomerID(),
							customer.getName()));
				}
			} finally {
				customerLock.unlock();
			}
			break;
		}

		return dropDownItems;
	}

}
