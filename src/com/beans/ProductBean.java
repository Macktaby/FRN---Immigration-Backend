package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Product;

public class ProductBean {

	private Connection conn;

	public ProductBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<Product> getProductsOfTheDay() {

		try {
			String sql = "SELECT * FROM product WHERE is_day_prod = true";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Product> products = new ArrayList<Product>();

			while (rs.next())
				products.add(parseProduct(rs));

			return products;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Product parseProduct(ResultSet rs) throws SQLException {

		Product product = new Product();

		product.setProductID(rs.getInt("product_id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("desc"));
		product.setImage(rs.getString("image"));
		product.setQuantity(rs.getInt("quantity"));
		product.setPrice(rs.getDouble("price"));
		product.setRating(rs.getDouble("rating"));
		product.setNumRatingUsers(rs.getInt("n_ratings"));
		product.setDayProd(rs.getBoolean("is_day_prod"));

		product.setCategoryID(rs.getInt("category_id"));
		product.setShowRoomID(rs.getInt("showroom_id"));
		product.setBrandID(rs.getInt("brand_id"));

		return product;
	}

}
