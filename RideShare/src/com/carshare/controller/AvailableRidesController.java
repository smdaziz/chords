package com.carshare.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carshare.beans.RideInfo;
import com.carshare.dao.RideInfoDAO;

public class AvailableRidesController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Fetch available Rides info and associate with user session
		HttpSession session = request.getSession(false);
		if(null != session) {
			RideInfoDAO rideInfoDAO = new RideInfoDAO();
			List<RideInfo> availableRides = rideInfoDAO.getAvailableRides();
			if(null != availableRides) {
				session.setAttribute("availableRides", availableRides);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/availableRides.jsp");
				requestDispatcher.forward(request, response);
			} else {
				request.setAttribute("message", "Sorry, No Available Rides this time..!!");
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/ride.jsp");
				requestDispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("message", "Please Login");
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
