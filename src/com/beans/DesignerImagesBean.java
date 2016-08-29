package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class DesignerImagesBean {

	private Connection conn;

	public DesignerImagesBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<String> getDesignerImages(int designerID) {

		try {
			String sql = "SELECT * FROM designer_images WHERE designer_id = ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, designerID);
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

	public String addDesignerImages(int designerID, List<String> images) {

		for (String image : images) {
			try {
				String sql = "INSERT INTO `designer_images`(`designer_id`, `image`) VALUES (?, ?)";

				PreparedStatement stmt;
				stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				stmt.setInt(1, designerID);
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
