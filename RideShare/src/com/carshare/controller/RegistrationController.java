package com.carshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carshare.beans.User;
import com.carshare.dao.UserDAO;

public class RegistrationController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Register new user
		User user = new User();
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setEmail(request.getParameter("email"));
		user.setMobile(request.getParameter("mobile"));
		user.setUserName(request.getParameter("userName"));
		user.setPassword(request.getParameter("password"));

		UserDAO userDAO = new UserDAO();
		boolean registered = userDAO.insertUser(user);

		if(registered) {
			request.setAttribute("message", "Registration Successful..!!!");
			request.setAttribute("color", "green");
		}

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		requestDispatcher.forward(request, response);
	}

}
