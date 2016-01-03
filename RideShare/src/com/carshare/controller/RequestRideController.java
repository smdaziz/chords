package com.carshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carshare.beans.ShareRide;
import com.carshare.dao.ShareRideDAO;

public class RequestRideController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Fetch available Rides info and associate with user session
		HttpSession session = request.getSession(false);
		if(null != session) {
			String isRidePossible = request.getParameter("ridePossible");
			if(isRidePossible.equals("false")) {
				request.setAttribute("message", "Sorry!! Ride not possible..");
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/availableRides.jsp");
				requestDispatcher.forward(request, response);
			} else {
				ShareRide shareRide = new ShareRide();
				shareRide.setUserName((String)session.getAttribute("userName"));
				shareRide.setRideBy(request.getParameter("rideBy"));
				
				ShareRideDAO shareRideDAO = new ShareRideDAO();
				boolean success = shareRideDAO.insertShareRide(shareRide);
				if(!success) {
					request.setAttribute("message", "Transaction Failed. Please try again later..!!");
					RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/availableRides.jsp");
					requestDispatcher.forward(request, response);
				} else {
					request.setAttribute("message", "Ride confirmed..!!");
					RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/ride.jsp");
					requestDispatcher.forward(request, response);
				}
			}
		} else {
			request.setAttribute("message", "Please Login");
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
