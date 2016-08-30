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

	private PromotionLocation parsePromotionLocation(ResultSet rs) throws SQLException {

		PromotionLocation promotion = new PromotionLocation();

		promotion.setPromotionID(rs.getInt("promotion_id"));
		promotion.setProductID(rs.getInt("product_id"));
		promotion.setDiscount(rs.getInt("discount"));
		promotion.setStartTime(rs.getTimestamp("start_time"));
		promotion.setEndTime(rs.getTimestamp("end_time"));
		promotion.setLocation(rs.getString("location"));

		return promotion;
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

	public PromotionLocation getPromotion(int productID, String location) {

		try {
			String sql = "SELECT * FROM promotion_location WHERE product_id=? AND location=?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, productID);
			stmt.setString(2, location);

			ResultSet rs = stmt.executeQuery();

			PromotionLocation promotion = null;

			if (rs.next())
				promotion = parsePromotionLocation(rs);

			return promotion;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
