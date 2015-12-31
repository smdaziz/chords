/**
* This class defines a drop down entry
* in situations such as JComboBox etc.
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

public class DropDownEntry {
	
	//private key and value
	private String entryKey;
	private String entryValue;

	//public no-arg constructor
	public DropDownEntry() {
	}

	//public constructor which creates
	//the instance with passed in key-value pair
	public DropDownEntry(String entryKey, String entryValue) {
		this.entryKey = entryKey;
		this.entryValue = entryValue;
	}

	//getters and setters to provide access
	//to private fields of this instance
	public String getEntryKey() {
		return entryKey;
	}

	public void setEntryKey(String entryKey) {
		this.entryKey = entryKey;
	}

	public String getEntryValue() {
		return entryValue;
	}

	public void setEntryValue(String entryValue) {
		this.entryValue = entryValue;
	}

	//overriding equality as per our business logic
	//where, two objects are equal if the underlying keys are equal
	@Override
	public boolean equals(Object object) {
		if (null == object)
			return false;
		if (!(object instanceof DropDownEntry))
			return false;
		return ((DropDownEntry) object).getEntryKey().equals(getEntryKey());
	}

	//overriding this method to print
	//the value in <key, value> format
	@Override
	public String toString() {
		return "<" + entryKey + ", " + entryValue + ">";
	}

}
