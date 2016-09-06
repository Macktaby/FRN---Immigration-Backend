package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class ProductImagesBean {

	private Connection conn;

	public ProductImagesBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<String> getProductImages(int productID) {

		try {
			String sql = "SELECT * FROM product_images WHERE product_id = ?";

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

	public String addProductImages(int productID, List<String> images) {

		for (String image : images) {
			try {
				String sql = "INSERT INTO `product_images`(`product_id`, `image`) VALUES (?, ?)";

				PreparedStatement stmt;
				stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				stmt.setInt(1, productID);
				stmt.setString(2, image);

				stmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				return "false";
			}
		}

		return "true";
	}

}
