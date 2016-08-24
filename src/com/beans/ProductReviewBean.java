package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.ProductReview;
import com.mysql.jdbc.Statement;

public class ProductReviewBean {

	private Connection conn;

	public ProductReviewBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ProductReview addReview(ProductReview pr) {

		try {
			String sql = "INSERT INTO `product_reviews` "
					+ "(`product_id`, `user_id`, `review`, `rating` time) VALUES (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, pr.getProductID());
			stmt.setInt(2, pr.getUserID());
			stmt.setString(3, pr.getReview());
			stmt.setInt(4, pr.getRating());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				pr.setReviewID(rs.getInt(1));

				return pr;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
