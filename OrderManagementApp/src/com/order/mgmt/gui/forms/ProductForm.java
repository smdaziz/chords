/**
* This class acts as a form to
* capture user input that is part
* of a Product entity.
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

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.order.mgmt.entities.ProductBean;
import com.order.mgmt.gui.panels.EditorPanel;
import com.order.mgmt.utils.IOrderManagement;
import com.order.mgmt.utils.OrderManagementHelper;

public class ProductForm extends EditorPanel {

	//declare and instantiate the layout associated with
	//the fields displayed on Product form
	private final GridBagLayout gridBagLayout = new GridBagLayout();

	//declare the Product related labels and fields
	private JTextField productID = new JTextField();
	private JLabel productIDLabel = new JLabel(IOrderManagement.PRODUCT_ID);
	private JTextField productName = new JTextField();
	private JLabel productNameLabel = new JLabel(IOrderManagement.PRODUCT_NAME);
	private JTextField supplier = new JTextField();
	private JLabel supplierLabel = new JLabel(IOrderManagement.SUPPLIER);
	private JTextField productType = new JTextField();
	private JLabel productTypeLabel = new JLabel(IOrderManagement.PRODUCT_TYPE);
	private JTextField unitPrice = new JTextField();
	private JLabel unitPriceLabel = new JLabel(IOrderManagement.UNIT_PRICE);
	private JTextField availableQuantity = new JTextField();
	private JLabel availableQuantityLabel = new JLabel(IOrderManagement.AVAILABLE_QTY);
	private JTextField status = new JTextField();
	private JLabel statusLabel = new JLabel(IOrderManagement.STATUS);
	//common insets format across all the fields attached
	//to form fields on gridbaglayout
	private Insets productFormInsets = new Insets(5, 5, 5, 5);

	public ProductForm() {
		//set the layout for this panel
		setLayout(gridBagLayout);
		//define the field widths where ever appropriate
		productID.setColumns(10);
		productName.setColumns(10);
		supplier.setColumns(10);
		productType.setColumns(10);
		unitPrice.setColumns(10);
		availableQuantity.setColumns(10);
		status.setColumns(10);

		//boot the settings associated with each of the
		//fields on Product form
		bootIdSettings();
		bootNameSettings();
		bootSupplierSettings();
		bootProductTypeSettings();
		bootUnitPriceSettings();
		bootAvailableQtySettings();
		bootStatusSettings();
	}

	private void bootIdSettings() {
		//define constraint for product id label
		GridBagConstraints idGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		idGridSettings.gridx = 0;
		idGridSettings.gridy = 0;
		idGridSettings.insets = productFormInsets;
		//add the label to panel
		add(productIDLabel, idGridSettings);

		//define constraint for product id input field
		//define the x & y location where input field is to be placed
		idGridSettings.gridx = 1;
		idGridSettings.gridy = 0;
		//add the input field to panel
		add(productID, idGridSettings);
	}

	private void bootNameSettings() {
		//define constraint for product name label
		GridBagConstraints nameGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		nameGridSettings.gridx = 2;
		nameGridSettings.gridy = 0;
		nameGridSettings.insets = productFormInsets;
		//add the label to panel
		add(productNameLabel, nameGridSettings);

		//define constraint for product name input field
		//define the x & y location where input field is to be placed
		nameGridSettings.gridx = 3;
		nameGridSettings.gridy = 0;
		//add the input field to panel
		add(productName, nameGridSettings);
	}

	private void bootSupplierSettings() {
		//define constraint for product supplier label
		GridBagConstraints supplierGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		supplierGridSettings.gridx = 0;
		supplierGridSettings.gridy = 1;
		supplierGridSettings.insets = productFormInsets;
		//add the label to panel
		add(supplierLabel, supplierGridSettings);

		//define constraint for product supplier input field
		//define the x & y location where input field is to be placed
		supplierGridSettings.gridx = 1;
		supplierGridSettings.gridy = 1;
		//add the input field to panel
		add(supplier, supplierGridSettings);
	}

	private void bootProductTypeSettings() {
		//define constraint for product type label
		GridBagConstraints productTypeGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		productTypeGridSettings.gridx = 2;
		productTypeGridSettings.gridy = 1;
		productTypeGridSettings.insets = productFormInsets;
		//add the label to panel
		add(productTypeLabel, productTypeGridSettings);

		//define constraint for product type input field
		//define the x & y location where input field is to be placed
		productTypeGridSettings.gridx = 3;
		productTypeGridSettings.gridy = 1;
		//add the input field to panel
		add(productType, productTypeGridSettings);
	}

	private void bootUnitPriceSettings() {
		//define constraint for product unit price label
		GridBagConstraints unitPriceGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		unitPriceGridSettings.gridx = 0;
		unitPriceGridSettings.gridy = 2;
		unitPriceGridSettings.insets = productFormInsets;
		//add the label to panel
		add(unitPriceLabel, unitPriceGridSettings);

		//define constraint for product unit price input field
		//define the x & y location where input field is to be placed
		unitPriceGridSettings.gridx = 1;
		unitPriceGridSettings.gridy = 2;
		//add the input field to panel
		add(unitPrice, unitPriceGridSettings);
	}

	private void bootAvailableQtySettings() {
		//define constraint for product available quantity label
		GridBagConstraints availableQtyGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		availableQtyGridSettings.gridx = 2;
		availableQtyGridSettings.gridy = 2;
		availableQtyGridSettings.insets = productFormInsets;
		//add the label to panel
		add(availableQuantityLabel, availableQtyGridSettings);

		//define constraint for product available quantity input field
		//define the x & y location where input field is to be placed
		availableQtyGridSettings.gridx = 3;
		availableQtyGridSettings.gridy = 2;
		//add the input field to panel
		add(availableQuantity, availableQtyGridSettings);
	}

	private void bootStatusSettings() {
		//define constraint for product status label
		GridBagConstraints statusGridSettings = new GridBagConstraints();
		//define the x & y location where label is to be placed
		statusGridSettings.gridx = 0;
		statusGridSettings.gridy = 3;
		statusGridSettings.insets = productFormInsets;
		//add the label to panel
		add(statusLabel, statusGridSettings);

		//define constraint for product available status input field
		//define the x & y location where input field is to be placed
		statusGridSettings.gridx = 1;
		statusGridSettings.gridy = 3;
		//add the input field to panel
		add(status, statusGridSettings);
	}

	//extract the fields from the passed in object
	//and bind to GUI
	public boolean weaveObject(Object o) {
		ProductBean product = (ProductBean) o;
		productID.setText(product.getProductID());
		productName.setText(product.getName());
		supplier.setText(product.getSupplier());
		productType.setText(product.getProductType());
		unitPrice.setText(product.getUnitPrice() + "");
		availableQuantity.setText(product.getAvailableQuantity() + "");
		status.setText(product.getStatus());
		return false;
	}

	//construct the object by extracting the values
	//from form fields and encapsulating into an object
	public Object unWeaveObject() {
		ProductBean product = new ProductBean();
		product.setProductID(productID.getText());
		product.setName(productName.getText());
		product.setSupplier(supplier.getText());
		product.setProductType(productType.getText());
		product.setUnitPrice(OrderManagementHelper.extractValue(
				(unitPrice.getText())).doubleValue());
		product.setAvailableQuantity(Integer.parseInt(availableQuantity.getText()));
		product.setStatus(status.getText());
		return product;
	}

	//return the underlying entity code
	//proving that it is Product
	public int getEntityCode() {
		return IOrderManagement.PRODUCT;
	}

	//obtain the selected product id from
	//the products listed
	@Override
	public String getSelection() {
		return productID.getText();
	}

	//re initialize the form fields for 
	//adding a new product
	public void release() {
		productID.setText("");
		productName.setText("");
		supplier.setText("");
		productType.setText("");
		unitPrice.setText("");
		availableQuantity.setText("");
		status.setText("");
	}

	//any initialization activity in future, goes here
	public void init() {

	}
}
