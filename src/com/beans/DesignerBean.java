package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Designer;
import com.models.Product;
import com.models.User;
import com.mysql.jdbc.Statement;

public class DesignerBean {

	private Connection conn;

	public DesignerBean() {
		conn = DBConnection.getActiveConnection();
	}

	public Designer parseDesigner(ResultSet rs) {

		return null;
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
			String sql = "SELECT * FROM designer WHERE name = ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,"%"+ name + "%");
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
