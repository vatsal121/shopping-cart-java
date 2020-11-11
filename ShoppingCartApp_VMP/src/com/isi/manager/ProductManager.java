package com.isi.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.isi.entity.Category;
import com.isi.entity.Product;
import com.isi.service.DBConnector;

public class ProductManager {

	private static String getAllProductsQuery = "SELECT p.Id as ProductId" + " ,ProductName" + ",CategoryId" + ",Price"
			+ ",p.Description" + ",Quantity" + ",ProductImagePath" + ",p.DateCreated" + ",p.DateModified"
			+ ",C.Id as CategoryCategoryId" + ",C.CategoryName" + ",C.DateCreated as CategoryDateCreated"
			+ ",C.DateModified as CategoryDateModified" + " FROM products p "
			+ "LEFT JOIN Category C on C.Id=p.CategoryId ";
	// private static String whereClause = "where p.Id=?";

	public static ArrayList<Product> getAllProducts(String whereClause) {

		ArrayList<Product> productList = new ArrayList<Product>();

		Connection con;
		try {
			con = DBConnector.ConnectToDatabase(true);

			String newQuery = getAllProductsQuery;
			if (!whereClause.isEmpty()) {
				newQuery += " " + whereClause;
			}
			ResultSet rs = DBConnector.ExecuteQuery(con, newQuery);
			while (rs.next()) {
				Category c = new Category(rs.getInt("CategoryCategoryId"), rs.getNString("CategoryName"),
						rs.getDate("CategoryDateCreated"), rs.getDate("CategoryDateModified"));

				Product p = new Product(rs.getInt("ProductId"), rs.getInt("CategoryId"), rs.getInt("Quantity"),
						rs.getString("ProductName"), rs.getString("Description"), rs.getString("ProductImagePath"),
						rs.getFloat("Price"), rs.getDate("DateCreated"), rs.getDate("DateModified"), c);

				productList.add(p);
			}

			DBConnector.CloseConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}

	public static Product getProductByID(int ID) {
		String whereClause = " where p.Id=" + ID;
		if (ID == 0) {
			whereClause = "";
		}
		Product prod = null;
		ArrayList<Product> pList = getAllProducts(whereClause);
		for (Product product : pList) {
			if (product.getId() == ID) {
				prod = product;
				break;
			}
		}
		return prod;
	}

}
