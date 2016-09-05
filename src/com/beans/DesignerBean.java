package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Designer;
import com.mysql.jdbc.Statement;

public class DesignerBean {

	private Connection conn;

	public DesignerBean() {
		conn = DBConnection.getActiveConnection();
	}

	public Designer parseDesigner(ResultSet rs) throws SQLException {

		Designer designer = new Designer();

		designer.setDesignerID(rs.getInt("designer_id"));
		designer.setName(rs.getString("name"));
		designer.setEmail(rs.getString("email"));
		designer.setPhone(rs.getString("phone"));
		designer.setAddress(rs.getString("address"));
		designer.setWebsite(rs.getString("url"));
		designer.setRating(rs.getDouble("rating"));
		designer.setnRatingUsers(rs.getInt("n_ratings"));
		designer.setProfileImage(rs.getString("profile_image"));

		return designer;
	}

	public ArrayList<Designer> getDesigners() {

		try {
			String sql = "SELECT * FROM designer";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Designer> designers = new ArrayList<Designer>();

			while (rs.next())
				designers.add(parseDesigner(rs));

			return designers;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Designer> getFilteredDesigners(String name) {
		try {
			String sql = "SELECT * FROM designer WHERE name LIKE ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + name + "%");
			ResultSet rs = stmt.executeQuery();

			ArrayList<Designer> designers = new ArrayList<Designer>();

			while (rs.next())
				designers.add(parseDesigner(rs));

			return designers;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Designer addDesigner(Designer designer) {
		try {
			String sql = "INSERT INTO `designer`"
					+ "(`name`, `email`, `address`, `url`, `phone`, `rating`, `n_ratings`, `profile_image`) "
					+ "VALUES (?,?,?,?,?,?,?,?);";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, designer.getName());
			stmt.setString(2, designer.getEmail());
			stmt.setString(3, designer.getAddress());
			stmt.setString(4, designer.getWebsite());
			stmt.setString(5, designer.getPhone());
			stmt.setDouble(6, designer.getRating());
			stmt.setInt(7, designer.getnRatingUsers());
			stmt.setString(8, designer.getProfileImage());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				designer.setDesignerID(rs.getInt(1));

				return designer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String updateDesigner(Designer designer) {
		try {
			String sql = "UPDATE `designer` SET `name`=?,`email`=?,`address`=?,`url`=?,`phone`=?,"
					+ ",`profile_image`=? WHERE `designer_id`=?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, designer.getName());
			stmt.setString(2, designer.getEmail());
			stmt.setString(3, designer.getAddress());
			stmt.setString(4, designer.getWebsite());
			stmt.setString(5, designer.getPhone());
			stmt.setString(6, designer.getProfileImage());
			stmt.setInt(7, designer.getDesignerID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String deleteDesigner(int id) {
		try {
			String sql = "DELETE FROM `designer` WHERE `designer_id` = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

}
