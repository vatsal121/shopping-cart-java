package com.isi.controllers;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isi.entity.User;
import com.isi.manager.LoginManager;
import com.isi.utils.PasswordUtils;

/**
 * Servlet implementation class Register
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("Register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {
			if (LoginManager.checkUserExists(userName)) {
				request.setAttribute("userExists", LoginManager.checkUserExists(userName));
			} else {
				//Calendar calendar = Calendar.getInstance();
				Date createdtDate = new Date(System.currentTimeMillis());// new Date(calendar.getTime().getTime());
				String passwordSalt = PasswordUtils.getSalt(30);
				String passwordHash = PasswordUtils.generateSecurePassword(password, passwordSalt);
				User user = new User(0, userName, passwordSalt, passwordHash, "customer",
						/* new Date(System.currentTimeMillis()) */createdtDate, null);
				LoginManager.AddUser(user);
				request.setAttribute("registeredSuccessfully", true);
			}
		}
		request.getRequestDispatcher("Register.jsp").forward(request, response);
	}

}
