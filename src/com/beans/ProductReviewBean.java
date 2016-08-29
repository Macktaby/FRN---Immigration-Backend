package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.ProductReview;
import com.mysql.jdbc.Statement;

public class ProductReviewBean {

	private Connection conn;

	public ProductReviewBean() {
		conn = DBConnection.getActiveConnection();
	}

	private ProductReview parseProductReview(ResultSet rs) throws SQLException {

		ProductReview pr = new ProductReview();

		pr.setProductID(rs.getInt("product_id"));
		pr.setUserID(rs.getInt("user_id"));
		pr.setReview(rs.getString("review"));
		pr.setRating(rs.getInt("rating"));

		return pr;
	}

	public ProductReview addReview(ProductReview pr) {

		try {
			String sql = "INSERT INTO `product_reviews` "
					+ "(`product_id`, `user_id`, `review`, `rating`) VALUES (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, pr.getProductID());
			stmt.setInt(2, pr.getUserID());
			stmt.setString(3, pr.getReview());
			stmt.setInt(4, pr.getRating());

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<ProductReview> getReviews(int id) {

		try {
			String sql = "SELECT * FROM `product_reviews` WHERE `product_id` = ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);

			ArrayList<ProductReview> reviews = new ArrayList<ProductReview>();
			ResultSet rs = stmt.executeQuery();

			while (rs.next())
				reviews.add(parseProductReview(rs));

			return reviews;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
