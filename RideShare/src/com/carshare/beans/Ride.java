package com.carshare.beans;

import java.util.Date;

public class Ride {

	private String userName;
	private String source;
	private String dest;
	private int hh;
	private int mm;
	private int passengers;
	private String srcLat;
	private String srcLng;
	private String destLat;
	private String destLng;
	private Date rideDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public int getHh() {
		return hh;
	}

	public void setHh(int hh) {
		this.hh = hh;
	}

	public int getMm() {
		return mm;
	}

	public void setMm(int mm) {
		this.mm = mm;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public String getSrcLat() {
		return srcLat;
	}

	public void setSrcLat(String srcLat) {
		this.srcLat = srcLat;
	}

	public String getSrcLng() {
		return srcLng;
	}

	public void setSrcLng(String srcLng) {
		this.srcLng = srcLng;
	}

	public String getDestLat() {
		return destLat;
	}

	public void setDestLat(String destLat) {
		this.destLat = destLat;
	}

	public String getDestLng() {
		return destLng;
	}

	public void setDestLng(String destLng) {
		this.destLng = destLng;
	}

	public Date getRideDate() {
		return rideDate;
	}

	public void setRideDate(Date rideDate) {
		this.rideDate = rideDate;
	}

}
