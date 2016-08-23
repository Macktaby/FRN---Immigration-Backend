package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Brand;
import com.mysql.jdbc.Statement;

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

	public Brand addBrand(Brand brand) {
		try {
			String sql = "INSERT INTO `brand` (`name`, `desc`, `image`) VALUES (?,?,?);";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, brand.getName());
			stmt.setString(2, brand.getDescription());
			stmt.setString(3, brand.getImage());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				brand.setBrandID(rs.getInt(1));

				return brand;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public String updateBrand(Brand brand) {
		try {
			String sql = "UPDATE `brand` SET `name`=?,`desc`=?,`image`=? WHERE `brand_id`=?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, brand.getName());
			stmt.setString(2, brand.getDescription());
			stmt.setString(3, brand.getImage());
			stmt.setInt(4, brand.getBrandID());

			stmt.executeUpdate();
			return "true";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "false";
	}

	public String deleteBrand(int brandID) {
		try {
			String sql = "DELETE FROM `brand` WHERE `brand_id` = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, brandID);
			stmt.executeUpdate();

			return "true";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "false";
	}

}
