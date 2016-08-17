package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Category;

public class CategoryBean {

	private Connection conn;

	public CategoryBean() {
		conn = DBConnection.getActiveConnection();
	}

	public Category parseCategory(ResultSet rs) throws SQLException {

		Category category = new Category();

		category.setCategoryID(rs.getInt("category_id"));
		category.setName(rs.getString("name"));
		category.setDescription(rs.getString("desc"));

		return category;
	}

	public ArrayList<Category> getCategories() {

		try {
			String sql = "SELECT * FROM category";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ArrayList<Category> categories = new ArrayList<Category>();

			while (rs.next())
				categories.add(parseCategory(rs));

			return categories;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
