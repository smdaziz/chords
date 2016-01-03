package com.carshare.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.carshare.dao.UserDAO;

public class LoginController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO - check if user exists
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		UserDAO userDAO = new UserDAO();
		if(userDAO.userExists(userName, password)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userName", userName);
			System.out.println("forwarding to ride.jsp");
			getServletContext().getRequestDispatcher("/ride.jsp").forward(request, response);
		} else {
			request.setAttribute("message", "Invalid UserName/Password..!!");
			request.setAttribute("color", "red");
			System.out.println("forwarding to home.jsp");
			getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
		}
	}

}
