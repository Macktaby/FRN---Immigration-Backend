package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Catalog;
import com.mysql.jdbc.Statement;

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

	public Catalog addCatalog(Catalog catalog) {

		try {
			String sql = "INSERT INTO `catalog` (`name`, `desc`, `date`, `pdf`) VALUES (?,?,?,?);";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, catalog.getName());
			stmt.setString(2, catalog.getDescription());
			stmt.setTimestamp(3, catalog.getDate());
			stmt.setString(4, catalog.getPdfLink());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				catalog.setCatalogID(rs.getInt(1));
				return catalog;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
