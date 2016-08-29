package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.House;
import com.mysql.jdbc.Statement;

public class HouseBean {

	private Connection conn;

	public HouseBean() {
		conn = DBConnection.getActiveConnection();
	}

	private House parseHouse(ResultSet rs) throws SQLException {

		House house = new House();

		house.setHouseID(rs.getInt("house_id"));
		house.setName(rs.getString("name"));
		house.setDescription(rs.getString("desc"));
		house.setImage(rs.getString("image"));

		return house;
	}

	public ArrayList<House> getHouses() {
		try {
			String sql = "SELECT * FROM houses";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ArrayList<House> houses = new ArrayList<House>();

			while (rs.next())
				houses.add(parseHouse(rs));

			return houses;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<House> searchHouses(String name) {
		try {
			String sql = "SELECT * FROM houses WHERE name LIKE ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + name + "%");
			ResultSet rs = stmt.executeQuery();

			ArrayList<House> houses = new ArrayList<House>();

			while (rs.next())
				houses.add(parseHouse(rs));

			return houses;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public House addHouse(House house) {
		try {
			String sql = "INSERT INTO `houses`(`name`, `desc`, `image`) VALUES (?,?,?)";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, house.getName());
			stmt.setString(2, house.getDescription());
			stmt.setString(3, house.getImage());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				house.setHouseID(rs.getInt(1));

				return house;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String updateHouse(House house) {
		try {
			String sql = "UPDATE `houses` SET `name`=?,`desc`=?,`image`=? WHERE `house_id`=?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, house.getName());
			stmt.setString(2, house.getDescription());
			stmt.setString(3, house.getImage());
			stmt.setInt(4, house.getHouseID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String deleteHouse(int id) {
		try {
			String sql = "DELETE FROM `houses` WHERE `house_id` = ?";

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
