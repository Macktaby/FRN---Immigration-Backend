package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductImagesBean {

	private Connection conn;

	public ProductImagesBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<String> getProductImages(int productID) {

		try {
			String sql = "SELECT * FROM ProductImages WHERE product_id = ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, productID);
			ResultSet rs = stmt.executeQuery();

			ArrayList<String> images = new ArrayList<String>();

			while (rs.next())
				images.add(rs.getString("image"));

			return images;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
