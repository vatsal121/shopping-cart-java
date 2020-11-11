package com.isi.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isi.entity.User;
import com.isi.manager.LoginManager;
import com.isi.manager.SessionManager;
import com.isi.utils.PasswordUtils;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String pageToRedirect = "LoginServlet";
		int counter = 0;
		if (userName != null && !userName.isEmpty() && password != null && !password.isEmpty()) {
			if (LoginManager.checkUserExists(userName)) {
				User user = LoginManager.getUser(userName);
				if (user != null) {
					boolean success = PasswordUtils.verifyUserPassword(password, user.getPasswordHash(),
							user.getPasswordSalt());

					if (success) {
						if (!SessionManager.checkSessionExistsOrNot(request, "User")) {
							SessionManager.addValueToSession(request, "User", user);
						}
						pageToRedirect = "HomeServlet";
						if (user.getRole().equalsIgnoreCase("admin")) {
							pageToRedirect = "AdminServlet";
						}

					} else {
						counter++;
					}
				} else {
					counter++;
				}
			} else {
				counter++;
			}
		}
		if (counter > 0) {
			request.setAttribute("userError", true);
		}
		request.getRequestDispatcher(pageToRedirect).forward(request, response);

	}

}
