package com.isi.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.isi.entity.Cart;
import com.isi.entity.Product;
import com.isi.entity.User;
import com.isi.manager.CartManager;
import com.isi.manager.ProductManager;
import com.isi.manager.SessionManager;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeServlet() {
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
		if (request.getParameter("logout") != null && Boolean.parseBoolean(request.getParameter("logout")) == true) {
			// request.getRequestDispatcher("LoginServlet").forward(request, response);
			SessionManager.clearAllSessionsAndInvalidate(request);
			response.sendRedirect("LoginServlet");
			return;
		}

		Object userSession = SessionManager.findSpecificSessionValue(request, "User");
		User user = userSession != null ? (User) userSession : null;
		if (request.getParameter("pid") != null && Integer.parseInt(request.getParameter("pid")) > 0) {
			float discountPercentage = 0.16f;
			Product p = ProductManager.getProductByID(Integer.parseInt(request.getParameter("pid")));
			float finalPrice = p.getPrice();
			finalPrice -= p.getPrice() * discountPercentage;
			finalPrice = finalPrice * 1; // default qty 1;
			Cart c = new Cart(0, user.getId(), p.getId(), 1, discountPercentage, finalPrice, false,
					new Date(System.currentTimeMillis()), null, p);

			if (CartManager.checkIfProductExistsInCart(p.getId(), user.getId())) {
				c = CartManager.getCartByProductID(p.getId(), user.getId());
				c.setQtyOrdered(c.getQtyOrdered() + 1);

				CartManager.UpdateCart(c);
			} else {
				CartManager.AddToCart(c);
			}
		}

		ArrayList<Product> productList = ProductManager.getAllProducts("");
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("Home.jsp").forward(request, response);
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
