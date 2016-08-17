package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Brand;

public class BrandBean {

	private Connection conn;

	public BrandBean() {
		conn = DBConnection.getActiveConnection();
	}

	public Brand parseBrand(ResultSet rs) throws SQLException {

		Brand brand = new Brand();

		brand.setBrandID(rs.getInt("brand_id"));
		brand.setName(rs.getString("name"));
		brand.setDescription(rs.getString("desc"));
		brand.setImage(rs.getString("image"));

		return brand;
	}

	public ArrayList<Brand> getBrands() {

		try {
			String sql = "SELECT * FROM brand";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ArrayList<Brand> brands = new ArrayList<Brand>();

			while (rs.next())
				brands.add(parseBrand(rs));

			return brands;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
