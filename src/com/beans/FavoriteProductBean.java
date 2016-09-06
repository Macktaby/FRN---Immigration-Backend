package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.FavoriteProduct;

public class FavoriteProductBean {

	private Connection conn;

	public FavoriteProductBean() {
		conn = DBConnection.getActiveConnection();
	}

	public String addFavorite(FavoriteProduct fp) {
		try {
			String sql = "INSERT INTO `wishlist`(`user_id`, `product_id`, `time`) VALUES (?,?,?)";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, fp.getUserID());
			stmt.setInt(2, fp.getProductID());
			stmt.setTimestamp(3, fp.getTime());

			stmt.executeUpdate();
			return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String removeFavorite(FavoriteProduct fp) {
		try {
			String sql = "DELETE FROM `wishlist` WHERE `user_id`=? AND `product_id`=?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, fp.getUserID());
			stmt.setInt(2, fp.getProductID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public ArrayList<Integer> getFavoriteUsers(int productID) {
		try {
			String sql = "SELECT `user_id` FROM `wishlist` WHERE `product_id` = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, productID);

			ResultSet rs = stmt.executeQuery();

			ArrayList<Integer> userIDs = new ArrayList<Integer>();

			while (rs.next())
				userIDs.add(rs.getInt(1));

			return userIDs;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
