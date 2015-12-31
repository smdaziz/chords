/**
* This interface provides underlying
* component under display. May be actual
* Customer or Product form etc.
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

public interface IPresenter {

	//this method implementation returns
	//the underlying instance associated
	//with the module to present the user panel/form
	public abstract IViewer getUnderlyingPresenter();

}
