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

	private Catalog parseCatalog(ResultSet rs) throws SQLException {

		Catalog catalog = new Catalog();

		catalog.setCatalogID(rs.getInt("catalog_id"));
		catalog.setName(rs.getString("name"));
		catalog.setDescription(rs.getString("desc"));
		catalog.setMonth(rs.getString("month"));
		catalog.setYear(rs.getInt("year"));
		catalog.setPdfLink(rs.getString("pdf"));

		return catalog;
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

	public Catalog addCatalog(Catalog catalog) {

		try {
			String sql = "INSERT INTO `catalog` (`name`, `desc`, `month`, `year`, `pdf`) "
					+ "VALUES ( ? , ? , ? , ? , ? );";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, catalog.getName());
			stmt.setString(2, catalog.getDescription());
			stmt.setString(3, catalog.getMonth());
			stmt.setInt(4, catalog.getYear());
			stmt.setString(5, catalog.getPdfLink());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				catalog.setCatalogID(rs.getInt(1));
				return catalog;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String updateCatalog(Catalog catalog) {
		try {
			String sql = "UPDATE `catalog` SET `name`=?,`desc`=?,`month`=?,`year`=?,`pdf`=? WHERE `catalog_id`=?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, catalog.getName());
			stmt.setString(2, catalog.getDescription());
			stmt.setString(3, catalog.getMonth());
			stmt.setInt(4, catalog.getYear());
			stmt.setString(5, catalog.getPdfLink());
			stmt.setInt(6, catalog.getCatalogID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String deleteCatalog(int catalogID) {
		try {
			String sql = "DELETE FROM `catalog` WHERE `catalog_id` = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, catalogID);

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public ArrayList<Catalog> searchProducts(String name) {
		try {
			String sql = "SELECT * FROM catalog WHERE name LIKE ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + name + "%");
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

}
