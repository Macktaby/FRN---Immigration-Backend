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

	private PromotionWishlist parsePromotionWishlistUser(ResultSet rs) throws SQLException {

		PromotionWishlist promotion = new PromotionWishlist();

		promotion.setPromotionID(rs.getInt("promotion_id"));
		promotion.setProductID(rs.getInt("product_id"));
		promotion.setDiscount(rs.getInt("discount"));
		promotion.setUserID(rs.getInt("user_id"));

		return promotion;

	}

	public PromotionWishlist addWishlistPromotion(PromotionWishlist promotion) {

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
			return null;
		}
		return promotion;
	}

	public String addPromotionUsers(int promotionID, int discount, int productID) {

		ArrayList<Integer> userIDs = new FavoriteProductBean().getFavoriteUsers(productID);

		// INSERT PROMOTION FOR USERS

		for (int userID : userIDs) {
			try {
				String sql = "INSERT INTO `promotion_wishlist_users`"
						+ " (`promotion_id`,`discount`,`user_id`,`product_id`) VALUES (?,?,?,?)";

				PreparedStatement stmt;
				stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				stmt.setInt(1, promotionID);
				stmt.setInt(2, discount);
				stmt.setInt(3, userID);
				stmt.setInt(4, productID);

				stmt.executeUpdate();

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

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public PromotionWishlist getPromotion(int productID, int userID) {
		try {
			String sql = "SELECT * FROM promotion_wishlist_users WHERE product_id=? AND user_id=?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, productID);
			stmt.setInt(2, userID);

			ResultSet rs = stmt.executeQuery();

			PromotionWishlist promotion = null;

			if (rs.next())
				promotion = parsePromotionWishlistUser(rs);

			return promotion;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
