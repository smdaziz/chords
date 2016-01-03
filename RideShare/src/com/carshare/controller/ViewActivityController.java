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
import com.carshare.dao.ShareRideDAO;

public class ViewActivityController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Fetch available Rides info and associate with user session
		HttpSession session = request.getSession(false);
		if(null != session) {
			RideInfoDAO rideInfoDAO = new RideInfoDAO();
			List<RideInfo> availableRides = rideInfoDAO.getAvailableRidesForUser((String)session.getAttribute("userName"));
			if(null != availableRides) {
				session.setAttribute("availableRides", availableRides);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/viewActivity.jsp");
				requestDispatcher.forward(request, response);
			} else {
				ShareRideDAO shareRideDAO = new ShareRideDAO();
				String rideDetails = shareRideDAO.getShareRideDetails((String)session.getAttribute("userName"));
				if(rideDetails != null) {
					request.setAttribute("message", "<b>You have shared ride with</b><br>"+rideDetails);
				} else {
					request.setAttribute("message", "You din't perform any activity today!! Please choose to ride.");
				}
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
