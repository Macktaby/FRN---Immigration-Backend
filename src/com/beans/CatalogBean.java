package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Catalog;

public class CatalogBean {

	private Connection conn;

	public CatalogBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<Catalog> getCatalogs() {

		try {
			String sql = "SELECT * FROM catalog";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Catalog> catalogs = new ArrayList<Catalog>();

			while (rs.next())
				catalogs.add(parseCatalog(rs));

			return catalogs;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Catalog parseCatalog(ResultSet rs) throws SQLException {

		Catalog catalog = new Catalog();

		catalog.setCatalogID(rs.getInt("catalog_id"));
		catalog.setName(rs.getString("name"));
		catalog.setDescription(rs.getString("desc"));
		catalog.setDate(rs.getTimestamp("date"));
		catalog.setPdfLink(rs.getString("pdf"));

		return catalog;
	}

}
