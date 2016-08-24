package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.PromotionWishlist;
import com.mysql.jdbc.Statement;

public class PromotionWishlistBean {

	private Connection conn;

	public PromotionWishlistBean() {
		conn = DBConnection.getActiveConnection();
	}

	public String addWishlistPromotion(PromotionWishlist promotion) {

		ArrayList<Integer> userIDs = new FavoriteProductBean().getFavoriteUsers(promotion.getProductID());

		try {
			String sql = "INSERT INTO `promotion_wishlist`"
					+ " (`discount`,`start_time`,`end_time`,`product_id`) VALUES (?,?,?,?)";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, promotion.getDiscount());
			stmt.setTimestamp(2, promotion.getStartTime());
			stmt.setTimestamp(3, promotion.getEndTime());
			stmt.setInt(4, promotion.getProductID());

			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				promotion.setPromotionID(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "false";
		}

		// INSERT PROMOTION FOR USERS
		for (int userID : userIDs) {
			try {
				String sql = "INSERT INTO `promotion_wishlist`"
						+ " (`discount`,`start_time`,`end_time`,`product_id`) VALUES (?,?,?,?)";

				PreparedStatement stmt;
				stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				stmt.setInt(1, promotion.getDiscount());
				stmt.setTimestamp(2, promotion.getStartTime());
				stmt.setTimestamp(3, promotion.getEndTime());
				stmt.setInt(4, promotion.getProductID());
				stmt.setInt(5, userID);

				stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					promotion.setPromotionID(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return "false";
			}

		}

		return "true";
	}

	public String deleteWishlistPromotion(int promotionID) {

		try {
			String sql = "DELETE FROM `promotion_wishlist` WHERE `promotion_id` = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, promotionID);
			stmt.executeUpdate();

			return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

}
