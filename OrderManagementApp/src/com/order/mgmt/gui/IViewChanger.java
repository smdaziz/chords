/**
* This interface provides standard methods 
* to swith across different panels, go back
* and forth on user actions.
*
* @author  Mohammed Abdul Aziz, Syed
* @cwid	   50143871
* @course  CSCI 531 Project
* @email   msyed6@leomail.tamuc.edu
* @version 1.0
* @since   12-01-2015
*/

//declare a package
package com.order.mgmt.gui;

public interface IViewChanger {
	
	//this method implementation gets the underlying
	//form/panel instance associated with the
	//passed in module name
	public abstract IPresenter getViewCorrespondingTo(String name);

	//this method implementation swaps the screen
	//or panel associated with the current view
	//to that of the passed in module name
	public abstract void changeViewTo(String name);

}
