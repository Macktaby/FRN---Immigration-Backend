package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.PromotionLocation;
import com.mysql.jdbc.Statement;

public class PromotionLocationBean {

	private Connection conn;

	public PromotionLocationBean() {
		conn = DBConnection.getActiveConnection();
	}

	public PromotionLocation addLocationPromotion(PromotionLocation promotion) {
		try {
			String sql = "INSERT INTO `promotion_location`"
					+ "(`discount`, `start_time`, `end_time`, `product_id`, `location`) "
					+ "VALUES ( ? , ? , ? , ? , ? )";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, promotion.getDiscount());
			stmt.setTimestamp(2, promotion.getStartTime());
			stmt.setTimestamp(3, promotion.getEndTime());
			stmt.setInt(4, promotion.getProductID());
			stmt.setString(5, promotion.getLocation());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				promotion.setPromotionID(rs.getInt(1));

				return promotion;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String deleteLocationPromotion(int promotionID) {

		try {
			String sql = "DELETE FROM `promotion_location` WHERE `promotion_id` = ?";

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

}
