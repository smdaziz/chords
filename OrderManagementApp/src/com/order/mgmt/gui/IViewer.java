/**
* This interface provides standard access methods
* to GUI component initialization, release
* binding and unbinding objects.
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

public interface IViewer {

	//form/panel initializing activities go into
	//this method before the panel corresponding
	//to each module is loaded
	public abstract void init();

	//this method implementation binds
	//the passed in object to appropriate fields
	//on the panel/form
	public abstract boolean weaveObject(Object o);

	//this method implementation unbinds
	//the appropriate fields from the panel/form
	public abstract Object unWeaveObject();

	//form/panel releasing activities go into
	//this method before the panel corresponding
	//to each module is unloaded
	public abstract void release();

}
