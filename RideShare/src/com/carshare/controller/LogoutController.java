package com.carshare.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);

		if (null != session) {
			session.invalidate();
			request.setAttribute("message", "You have logged out successfully..!!");
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			requestDispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "Please Login");
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
			requestDispatcher.forward(request, response);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
