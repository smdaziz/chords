/**
* This class maps data from database entity
* orderitem to java entity OrderItem, 
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

import javax.swing.table.DefaultTableModel;

import com.order.mgmt.gui.IViewer;
import com.order.mgmt.utils.OrderManagementHelper;

public abstract class OrderManagementDTO extends DefaultTableModel implements IViewer {

	//public constructor that creates the table with
	//specified rows and columns
	public OrderManagementDTO(String[] columns, int rows) {
		super(columns, rows);
	}

	//this method initializes the table
	//by reading the underlying object and populating
	//the values of each object as each row in table
	public void init() {
		weaveObject(null);
	}

	//this method acts as shutdown hook
	//by cleaning up/deleting data from table
	public void release() {
		setRowCount(0);
	}

	//this method is implemented by each module
	//to return appropriate code that proves itself
	public abstract int getEntityCode();

	//this method is implemented by each module
	//to map the records as rows in the table
	public abstract String[][] toTable(List<Object> list);

	//this method is at the core, which is responsible
	//to fetch all related entities from database
	//and bind each records as table entry/row
	public boolean weaveObject(Object o) {
		release();
		List<Object> rowEntries = OrderManagementHelper.fetchAllEntities(getEntityCode());
		synchronized (rowEntries) {
			String[][] rows = toTable(rowEntries);
			for (String[] row : rows) {
				addRow(row);
			}
		}
		return true;
	}

	//returns the data vector
	public Object unWeaveObject() {
		return getDataVector();
	}

	//this method determines whether a particular
	//table cell is editable or not
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
