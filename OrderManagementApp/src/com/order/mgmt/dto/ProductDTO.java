/**
* This class maps data from database entity
* product to java entity Product, 
* binding and unbinding content on GUIs.
*
* @author  Mohammed Abdul Aziz, Syed
* @cwid	   50143871
* @course  CSCI 531 Project
* @email   msyed6@leomail.tamuc.edu
* @version 1.0
* @since   12-01-2015
*/

//declare a package
package com.order.mgmt.dto;

import java.util.List;

import com.order.mgmt.entities.ProductBean;
import com.order.mgmt.utils.IOrderManagement;
import com.order.mgmt.utils.OrderManagementHelper;

public class ProductDTO extends OrderManagementDTO {

	//Product headers that can be presented to the user
	private static final String[] headers;
	
	//initialize the Product related headers
	static {
		headers = new String[] { IOrderManagement.PRODUCT_ID, IOrderManagement.PRODUCT_NAME, IOrderManagement.SUPPLIER, 
				IOrderManagement.PRODUCT_TYPE, IOrderManagement.UNIT_PRICE, IOrderManagement.AVAILABLE_QTY, IOrderManagement.STATUS};
	}
	
	//public constructor
	public ProductDTO() {
		super(headers, 0);
	}

	//return the associated entity code that proves
	//itself as Product entity
	@Override
	public int getEntityCode() {
		return IOrderManagement.PRODUCT;
	}

	//convert the product information into table format
	//that is presented to the user on GUI
	@Override
	public String[][] toTable(List<Object> list) {
		String[][] products = new String[list.size()][headers.length];
		synchronized (this) {
			int rowIndex = 0;
			for (Object object : list) {
				ProductBean product = (ProductBean) object;
				products[rowIndex][0] = product.getProductID();
				products[rowIndex][1] = product.getName();
				products[rowIndex][2] = product.getSupplier();
				products[rowIndex][3] = product.getProductType();
				products[rowIndex][4] = product.getUnitPrice() + "";
				products[rowIndex][5] = product.getAvailableQuantity() + "";
				products[rowIndex][6] = product.getStatus();
				rowIndex++;
			}
		}
		return products;
	}
}
