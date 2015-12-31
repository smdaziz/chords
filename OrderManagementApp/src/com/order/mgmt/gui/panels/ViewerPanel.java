/**
* This class is a panel which displays
* standard view area, like save, close, delete.
* This also encapsulates the logic to go back and
* forth across various modules.
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

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.order.mgmt.dto.OrderManagementDTO;
import com.order.mgmt.gui.IPresenter;
import com.order.mgmt.gui.IViewChanger;
import com.order.mgmt.gui.IViewer;
import com.order.mgmt.gui.forms.FormEditor;
import com.order.mgmt.listeners.CloseAction;
import com.order.mgmt.utils.IOrderManagement;
import com.order.mgmt.utils.OrderManagementHelper;

public class ViewerPanel extends JPanel implements IPresenter {

	//declare the image url for close icon
	private URL imageURL = FormEditor.class.getResource(IOrderManagement.THREE);
	//declare and assign the toolbar for order management options
	private JToolBar orderManagementToolBar = new JToolBar();
	//declare all other required components such as
	//table for displaying orders/products/customers
	//and close button etc..
	private OrderManagementDTO orderManagementModel;
	private JTable ordersTable;
	private IViewChanger iViewChanger;
	private JButton closeButton = new JButton();
	
	public ViewerPanel(IViewChanger viewChanger, OrderManagementDTO orderMgmtModel) {
		this.iViewChanger = viewChanger;
		this.orderManagementModel = orderMgmtModel;
		//set border layout for this panel
		setLayout(new BorderLayout());

		//set the tooltip for close button
		closeButton.setToolTipText(IOrderManagement._CLOSE);
		//attach the action command for close button
		closeButton.setActionCommand(IOrderManagement.CLOSE);
		//associate the close action to this button such that
		//it takes the user to the home screen on closing current panel
		closeButton.addActionListener(new CloseAction(this));

		//just in case there is no image found, set the text of close button
		if (null == imageURL) {
			closeButton.setText(IOrderManagement._CLOSE);
		} else {
			//if image is found, set it as icon on close button
			ImageIcon defaultIcon = new ImageIcon(imageURL, IOrderManagement._CLOSE);
			closeButton.setIcon(defaultIcon);
		}

		//add this close button to order management toolbar
		orderManagementToolBar.add(closeButton);
		//add this toolbar to underlying panel
		add(orderManagementToolBar, BorderLayout.PAGE_START);

		//create a new table, to display records in read only format
		ordersTable = new JTable(orderMgmtModel);
		//set the height so that if fills the current panel size
		ordersTable.setFillsViewportHeight(true);
		ordersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//add the listener to the table
		ordersTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					private int selectedRow;
					public void valueChanged(ListSelectionEvent e) {
						if (!e.getValueIsAdjusting())
							selectedRow = ordersTable.getSelectedRow();
							if(selectedRow <= 0)
								return;
							else
								selectedEntry(ordersTable.getValueAt(selectedRow, 0).toString());
					}
				});
		
		//embedd this table into a scroll pane so that if the rows exceed
		//the panel height, user can easily scroll up and down to view records
		JScrollPane scrollPane = new JScrollPane(ordersTable);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//add this scroll pane to current panel
		add(scrollPane, BorderLayout.CENTER);
	}

	//this method adds selected entry to the presentation view
	//behind the scenes, all the fetched objects are weaved onto the table
	private void selectedEntry(String id) {
		//find the entity name
		String entityName = underlyingEntity(orderManagementModel.getEntityCode());
		//get corresponding view
		IPresenter correspondingPresenter = iViewChanger.getViewCorrespondingTo(entityName);
		//fetch associated entity data from database
		Object entity = OrderManagementHelper.fetchEntity(id, orderManagementModel.getEntityCode());
		//change the view to include the selected entity data in editable form
		iViewChanger.changeViewTo(entityName);
		//weave the object and bind the values to all the fields on respective form
		correspondingPresenter.getUnderlyingPresenter().weaveObject(entity);
	}

	//return the underlying module entity code
	//for which the view is to be presented
	private String underlyingEntity(int type) {
		if (type == IOrderManagement.PRODUCT)
			return IOrderManagement.PRODUCT_FORM;
		if (type == IOrderManagement.CUSTOMER)
			return IOrderManagement.CUSTOMER_FORM;
		return IOrderManagement.SALES_ORDER_FORM;
	}

	//return the underlying module presentation view
	public IViewer getUnderlyingPresenter() {
		return orderManagementModel;
	}

	//this method redirects user to home screen
	public void firstScreen() {
		iViewChanger.changeViewTo(IOrderManagement.SALES_ORDER_PANEL);
	}

}
