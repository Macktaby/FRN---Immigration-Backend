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
		product.setCategoryName(rs.getString("category_name"));
		product.setShowRoomID(rs.getInt("showroom_id"));
		product.setShowRoomName(rs.getString("showroom_name"));
		product.setBrandID(rs.getInt("brand_id"));
		product.setBrandName(rs.getString("brand_name"));

		return product;
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

	public ArrayList<Product> getAllProducts() {
		try {
			String sql = "SELECT * FROM product";

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

	public ArrayList<Product> getFilteredProducts(int brandID, int categoryID, int showRoomID) {
		try {
			int count = 0;
			boolean selection[] = { false, false, false };
			String sql = buildFilterSQL(brandID, categoryID, showRoomID, selection);

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			if (selection[0])
				stmt.setInt(++count, brandID);
			if (selection[1])
				stmt.setInt(++count, categoryID);
			if (selection[2])
				stmt.setInt(++count, showRoomID);

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

	private String buildFilterSQL(int brandID, int categoryID, int showRoomID, boolean[] selection)
			throws SQLException {

		String sql = "SELECT * FROM product";
		boolean flag = true;

		if (brandID != 0) {
			if (flag)
				sql += " WHERE ";
			else
				sql += " AND ";

			flag = false;

			sql += "brand_id = ?";
			selection[0] = true;
		}

		if (categoryID != 0) {
			if (flag)
				sql += " WHERE ";
			else
				sql += " AND ";

			flag = false;

			sql += "category_id = ?";
			selection[1] = true;
		}

		if (showRoomID != 0) {
			if (flag)
				sql += " WHERE ";
			else
				sql += " AND ";

			flag = false;
			sql += "showroom_id = ?";
			selection[2] = true;
		}

		return sql;
	}

}
