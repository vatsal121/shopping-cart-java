package com.isi.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.isi.entity.Cart;
import com.isi.entity.Product;
import com.isi.service.DBConnector;

public class CartManager {
	private static String getAllCartQuery = "select  * from cart c ";
	// private static String whereClause = "where p.Id=?";
	private static String insertToCart = "INSERT INTO cart(UserId, ProductId, DiscountPercentage, IsCustomerCheckedOut, QtyOrdered, FinalProductPrice, DateCreated) "
			+ "VALUES(?,?,?,?,?,?,?)";
	private static String updateCart = "update cart set QtyOrdered=?, FinalProductPrice=?, DateModified=now() where Id=?";

	private static String checkOutCart = "update cart set IsCustomerCheckedOut=true, DateModified=now() where UserId=?";
	private static String deleteCart = "delete from cart where Id=?";

	public static ArrayList<Cart> getAllCartProducts(String whereClause) {

		ArrayList<Cart> cartList = new ArrayList<Cart>();

		Connection con;
		try {
			con = DBConnector.ConnectToDatabase(true);

			String newQuery = getAllCartQuery;
			if (!whereClause.isEmpty()) {
				newQuery += " " + whereClause;
			}
			ResultSet rs = DBConnector.ExecuteQuery(con, newQuery);
			while (rs.next()) {
				Product p = ProductManager.getProductByID(rs.getInt("ProductId"));
				Cart cart = new Cart(rs.getInt("Id"), rs.getInt("UserId"), rs.getInt("ProductId"),
						rs.getInt("QtyOrdered"), rs.getFloat("DiscountPercentage"), rs.getFloat("FinalProductPrice"),
						rs.getBoolean("IsCustomerCheckedOut"), rs.getDate("DateCreated"), rs.getDate("DateModified"),
						p);
				cartList.add(cart);
			}

			DBConnector.CloseConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartList;
	}

	public static Cart getCartByID(int ID) {
		String whereClause = " where c.Id=" + ID + " and IsCustomerCheckedOut=false";
		if (ID == 0) {
			whereClause = "";
		}
		Cart _Cart = null;
		ArrayList<Cart> cList = getAllCartProducts(whereClause);
		for (Cart cart : cList) {
			if (cart.getId() == ID) {
				_Cart = cart;
				break;
			}
		}
		return _Cart;
	}

	public static Cart getCartByProductID(int productID, int userId) {
		String whereClause = " where c.ProductId=" + productID + " and c.UserId=" + userId
				+ " and IsCustomerCheckedOut=false";
		if (productID == 0) {
			whereClause = "";
		}
		Cart _Cart = null;
		ArrayList<Cart> cList = getAllCartProducts(whereClause);
		for (Cart cart : cList) {
			if (cart.getProductId() == productID) {
				_Cart = cart;
				break;
			}
		}
		return _Cart;
	}

	public static void AddToCart(Cart c) {
		if (c != null) {
			Connection con;
			try {
				con = DBConnector.ConnectToDatabase(true);

				PreparedStatement pstPreparedStatement = con.prepareStatement(insertToCart);
				pstPreparedStatement.setInt(1, c.getUserId());
				pstPreparedStatement.setInt(2, c.getProductId());
				pstPreparedStatement.setFloat(3, c.getDiscountPercentage());
				pstPreparedStatement.setBoolean(4, c.isIsCustomerCheckedOut());
				pstPreparedStatement.setInt(5, c.getQtyOrdered());
				pstPreparedStatement.setFloat(6, c.getFinalProductPrice());
				pstPreparedStatement.setDate(7, c.getDateCreated());

				pstPreparedStatement.execute();

				DBConnector.CloseConnection();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void UpdateCart(Cart c) {
		if (c != null) {
			Connection con;
			try {
				con = DBConnector.ConnectToDatabase(true);

				float finalPrice = c.get_Product().getPrice();
				finalPrice -= c.get_Product().getPrice() * c.getDiscountPercentage();
				finalPrice = finalPrice * c.getQtyOrdered();
				PreparedStatement pstPreparedStatement = con.prepareStatement(updateCart);
				pstPreparedStatement.setInt(1, c.getQtyOrdered());
				pstPreparedStatement.setFloat(2, finalPrice);
				pstPreparedStatement.setInt(3, c.getId());

				pstPreparedStatement.execute();

				DBConnector.CloseConnection();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void DeleteCart(int id) {
		Connection con;
		try {
			con = DBConnector.ConnectToDatabase(true);
			PreparedStatement pstPreparedStatement = con.prepareStatement(deleteCart);
			pstPreparedStatement.setInt(1, id);
			pstPreparedStatement.execute();
			DBConnector.CloseConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean checkIfProductExistsInCart(int productId, int userId) {

		Cart c = getCartByProductID(productId, userId);
		if (c != null) {
			// if (c.getUserId() == userId) {
			return true;
			// }
		}
		return false;
	}

	public static void CheckOut(int userId) {
		Connection con;
		try {
			con = DBConnector.ConnectToDatabase(true);
			PreparedStatement pstPreparedStatement = con.prepareStatement(checkOutCart);
			pstPreparedStatement.setInt(1, userId);
			pstPreparedStatement.execute();
			DBConnector.CloseConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
