/**
* This class provides data access operations
* across Product entity mapped with product table.
*
* @author  Mohammed Abdul Aziz, Syed
* @cwid	   50143871
* @course  CSCI 531 Project
* @email   msyed6@leomail.tamuc.edu
* @version 1.0
* @since   12-01-2015
*/

//declare a package
package com.order.mgmt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.order.mgmt.db.DataBase;
import com.order.mgmt.entities.CustomerBean;
import com.order.mgmt.entities.ProductBean;

public class ProductDAO {
	
	//declare a lock variable to synchronize the
	//operations that are to be thread safe
	private final ReentrantLock reentrantLock = new ReentrantLock();
	
	//this method extracts values encapsulated in ProductBean
	//and inserts the data into product table
	public boolean insertProduct(ProductBean product) throws Exception {
		boolean result = false;
		try {
			//obtain database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the insert sql
			String sql = "insert into product values('" + product.getProductID() +
					"', '" + product.getName() + 
					"', '" + product.getSupplier() + 
					"', '" + product.getProductType() + 
					"', " + product.getUnitPrice() + 
					", " + product.getAvailableQuantity() + 
					", '" + product.getStatus() + 
					"')";
			try {
				reentrantLock.lock();
				//insert the data into product table
				result = statement.executeUpdate(sql)!=0?true:false;
			} finally {
				reentrantLock.unlock();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if(e.getMessage().startsWith("Duplicate entry"))
				updateProduct(product);
			else
				throw e;
		}
		return result;
	}

	//this method extracts values encapsulated in ProductBean
	//and updates the data into product table
	public boolean updateProduct(ProductBean product) {
		boolean result = false;
		try {
			//obtain database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the update sql
			String sql = "update product set productid='" + product.getProductID() +
					"', name='" + product.getName() + 
					"', supplier='" + product.getSupplier() +
					"', producttype='" + product.getProductType() +
					"', unitprice=" + product.getUnitPrice() +
					", availablequantity=" + product.getAvailableQuantity() +
					", status='" + product.getStatus() +
					"' where productid = '" + product.getProductID() +
					"'";
			try {
				reentrantLock.lock();
				//update the product table
				result = statement.executeUpdate(sql)!=0?true:false;
			} finally {
				reentrantLock.unlock();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	//this method updates the product quantity for the product
	//whose id is passed as parameter
	public boolean updateProductQuantity(String productID, String qty) {
		boolean result = false;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the update sql
			String sql = "update product set availablequantity = availableQuantity - " + qty +
					" where productid = '" + productID + "'";
			try {
				reentrantLock.lock();
				//update the product table
				result = statement.executeUpdate(sql)!=0?true:false;
			} finally {
				reentrantLock.unlock();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	//this method deletes product record whose id is passed as parameter
	public boolean deleteProduct(String productID) {
		boolean result = false;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the delete sql query
			String sql = "delete from product where productid = '" +
					productID + "'";
			try {
				reentrantLock.lock();
				//delete the record from product table
				result = statement.executeUpdate(sql)!=0?true:false;
			} finally {
				reentrantLock.unlock();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	//this method fetches all the records from product table
	//encapsulates them into ProductBean, adds them to the list and return
	public List<ProductBean> getAllProducts() {
		List<ProductBean> products = null;
		try {
			//obtain the database connection and create statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the sql select query
			String sql = "select productid, name, supplier, producttype, unitprice, availablequantity, status from product";
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				products = new ArrayList<ProductBean>();
			}
			do {
				try {
					reentrantLock.lock();
					//instantiate the product bean and populate the values
					ProductBean product = new ProductBean();
					product.setProductID(resultSet.getString("productid"));
					product.setName(resultSet.getString("name"));
					product.setSupplier(resultSet.getString("supplier"));
					product.setProductType(resultSet.getString("producttype"));
					product.setUnitPrice(Double.parseDouble(resultSet.getString("unitprice")));
					product.setAvailableQuantity(Integer.parseInt(resultSet.getString("availablequantity")));
					product.setStatus(resultSet.getString("status"));
					//add the record to in memory list
					products.add(product);
				} finally {
					reentrantLock.unlock();
				}
			} while(resultSet.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	//this method returns the product data whose id
	//matches the passed product id, by encapsulating
	//into ProductBean
	public ProductBean getProductById(String productID) {
		ProductBean product = null;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the sql select query
			String sql = "select productid, name, supplier, producttype, unitprice, availablequantity, status from product where productid = '" + productID + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next()) {
				try {
					reentrantLock.lock();
					//instantiate the product bean and populate the values
					product = new ProductBean();
					product.setProductID(resultSet.getString("productid"));
					product.setName(resultSet.getString("name"));
					product.setSupplier(resultSet.getString("supplier"));
					product.setProductType(resultSet.getString("producttype"));
					product.setUnitPrice(Double.parseDouble(resultSet.getString("unitprice")));
					product.setAvailableQuantity(Integer.parseInt(resultSet.getString("availablequantity")));
					product.setStatus(resultSet.getString("status"));
				} finally {
					reentrantLock.unlock();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}
	
	//this method returns the price of the product id passed
	public double getPrice(String productID) {
		double price = 0;
		try {
			//obtain the database connection and create the statement
			Statement statement = DataBase.getConnection().createStatement();
			//form the sql select query
			String sql = "select unitprice from product where productid = '" + productID + "'";
			try {
				reentrantLock.lock();
				//fire the sql query and obtain the results
				ResultSet resultSet = statement.executeQuery(sql);
				if(resultSet.next())
					price = resultSet.getDouble("unitprice");
			} finally {
				reentrantLock.unlock();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return price;
	}

}
