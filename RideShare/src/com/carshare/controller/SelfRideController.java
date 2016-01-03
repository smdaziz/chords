package com.carshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carshare.beans.Ride;
import com.carshare.dao.RideDAO;

public class SelfRideController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Fetch Ride info and persist to DB
		/*Enumeration parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String parameterName = (String) parameterNames.nextElement();
			String[] parameterValues = request.getParameterValues(parameterName);
			System.out.println("<b>" + parameterName + "</b>" + "<br>");
			for(String parameterValue : parameterValues){
				System.out.println(parameterValue + "<br>");
			}
		}*/
		HttpSession session = request.getSession(false);
		if (null != session) {
			Ride ride = new Ride();
			ride.setUserName((String)(session.getAttribute("userName")));
			ride.setSource(request.getParameter("from"));
			ride.setDest(request.getParameter("to"));
			ride.setHh(Integer.parseInt(request.getParameter("hh")));
			ride.setMm(Integer.parseInt(request.getParameter("mm")));
			ride.setPassengers(Integer.parseInt(request.getParameter("passengers")));
			ride.setSrcLat(request.getParameter("fromLat"));
			ride.setSrcLng(request.getParameter("fromLng"));
			ride.setDestLat(request.getParameter("toLat"));
			ride.setDestLng(request.getParameter("toLng"));
			
			RideDAO rideDAO = new RideDAO();
			rideDAO.insertRide(ride);
			request.setAttribute("message", "Your ride confirmed from "+ride.getSource()+" to "+ride.getDest()+" at "+ride.getHh()+":"+ride.getMm()+" hours");
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/ride.jsp");
			requestDispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "Please Login");
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
