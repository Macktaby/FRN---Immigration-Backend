package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.DesignerReview;

public class DesignerReviewBean {

	private Connection conn;

	public DesignerReviewBean() {
		conn = DBConnection.getActiveConnection();
	}

	private DesignerReview parseDesignerReview(ResultSet rs) throws SQLException {

		DesignerReview pr = new DesignerReview();

		pr.setDesignerID(rs.getInt("designer_id"));
		pr.setUserID(rs.getInt("user_id"));
		pr.setReview(rs.getString("review"));
		pr.setRating(rs.getInt("rating"));

		return pr;
	}

	public DesignerReview addReview(DesignerReview pr) {

		try {
			String sql = "INSERT INTO `designer_reviews` "
					+ "(`designer_id`, `user_id`, `review`, `rating`) VALUES (?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, pr.getDesignerID());
			stmt.setInt(2, pr.getUserID());
			stmt.setString(3, pr.getReview());
			stmt.setInt(4, pr.getRating());

			stmt.executeUpdate();
			return pr;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<DesignerReview> getReviews(int id) {

		try {
			String sql = "SELECT * FROM `designer_reviews` WHERE `designer_id` = ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);

			ArrayList<DesignerReview> reviews = new ArrayList<DesignerReview>();
			ResultSet rs = stmt.executeQuery();

			while (rs.next())
				reviews.add(parseDesignerReview(rs));

			return reviews;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
