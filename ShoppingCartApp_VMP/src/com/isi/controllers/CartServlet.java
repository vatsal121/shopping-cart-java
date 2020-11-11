package com.isi.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isi.entity.Cart;
import com.isi.entity.User;
import com.isi.manager.CartManager;
import com.isi.manager.SessionManager;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
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
		if (request.getParameter("cid") != null && Integer.parseInt(request.getParameter("cid")) > 0) {
			CartManager.DeleteCart(Integer.parseInt(request.getParameter("cid")));
		}

		Object obj = SessionManager.findSpecificSessionValue(request, "User");
		User user = obj != null ? (User) obj : null;

		if (user != null) {
			if (request.getParameter("IsCheckOut") != null
					&& Boolean.parseBoolean(request.getParameter("IsCheckOut")) == true) {
				CartManager.CheckOut(user.getId());
			}
			ArrayList<Cart> cList = CartManager
					.getAllCartProducts(" where UserId=" + user.getId() + " and IsCustomerCheckedOut=false");
			HashMap<Integer, Cart> cartList = new HashMap<Integer, Cart>();
			for (Cart cart : cList) {
				cartList.put(cart.getId(), cart);
			}
			request.setAttribute("cartList", cartList);
		}
		request.getRequestDispatcher("Cart.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
