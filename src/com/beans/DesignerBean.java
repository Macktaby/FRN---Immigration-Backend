package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Designer;

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

}
