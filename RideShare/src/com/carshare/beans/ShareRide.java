package com.carshare.beans;

import java.util.Date;

public class ShareRide {

	private String userName;
	private String rideBy;
	private Date rideDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRideBy() {
		return rideBy;
	}

	public void setRideBy(String rideBy) {
		this.rideBy = rideBy;
	}

	public Date getRideDate() {
		return rideDate;
	}

	public void setRideDate(Date rideDate) {
		this.rideDate = rideDate;
	}

}
