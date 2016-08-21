package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Category;
import com.mysql.jdbc.Statement;

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

	public Category addCategory(Category category) {

		try {
			String sql = "INSERT INTO `category` (`name`, `desc`) VALUES (?,?);";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, category.getName());
			stmt.setString(2, category.getDescription());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				category.setCategoryID(rs.getInt(1));

				return category;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
