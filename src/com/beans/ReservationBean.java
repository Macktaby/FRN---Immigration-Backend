package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Product;
import com.models.Reservation;
import com.mysql.jdbc.Statement;

public class ReservationBean {

	private Connection conn;

	public ReservationBean() {
		conn = DBConnection.getActiveConnection();
	}

	public Reservation parseReservation(ResultSet rs) throws SQLException {
		Reservation reservation = new Reservation();

		reservation.setReservationID(rs.getInt("reservation.reservation_id"));
		reservation.setUserID(rs.getInt("user.user_id"));
		reservation.setUserName(rs.getString("user.user_name"));
		reservation.setQuantity(rs.getInt("reservation.quantity"));
		reservation.setTime(rs.getTimestamp("reservation.time"));

		reservation.setProduct(parseReservedProduct(rs));

		return reservation;
	}

	private Product parseReservedProduct(ResultSet rs) throws SQLException {

		Product product = new Product();

		product.setProductID(rs.getInt("product.product_id"));
		product.setName(rs.getString("product.name"));
		product.setDescription(rs.getString("product.desc"));
		product.setImage(rs.getString("product.image"));
		product.setQuantity(rs.getInt("reservation.quantity"));
		product.setPrice(rs.getDouble("product.price"));
		product.setRating(rs.getDouble("product.rating"));
		product.setNumRatingUsers(rs.getInt("product.n_ratings"));
		product.setDayProd(rs.getBoolean("product.is_day_prod"));

		product.setCategoryID(rs.getInt("product.category_id"));
		product.setCategoryName(rs.getString("product.category_name"));
		product.setShowRoomID(rs.getInt("product.showroom_id"));
		product.setShowRoomName(rs.getString("product.showroom_name"));
		product.setBrandID(rs.getInt("product.brand_id"));
		product.setBrandName(rs.getString("product.brand_name"));

		return product;
	}

	public Reservation addReservation(Reservation reservation) {
		try {
			String sql = "INSERT INTO `reservation` "
					+ "(`user_id`, `product_id`, `quantity`, `time`) VALUES ( ? , ? , ? , ? )";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, reservation.getUserID());
			stmt.setInt(2, reservation.getProduct().getProductID());
			stmt.setInt(3, reservation.getQuantity());
			stmt.setTimestamp(4, reservation.getTime());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				reservation.setReservationID(rs.getInt(1));

				return reservation;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public int getReservationQuantity(int reservationID) {
		try {
			String sql = "SELECT quantity FROM reservation WHERE reservation_id = ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, reservationID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
				return rs.getInt("quantity");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	public String cancelReservation(int reservationID) {

		try {
			String sql = "DELETE FROM `reservation` WHERE `reservation_id` = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, reservationID);

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}

		return "false";
	}

	public Reservation getReservationDetails(int reservationID) {
		try {
			String sql = "SELECT * FROM reservation, product, user WHERE reservation_id = ? AND"
					+ " product.product_id = reservation.product_id AND user.user_id = reservation.user_id";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, reservationID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
				return parseReservation(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Reservation> getUserReservedProducts(int id) {

		try {
			String sql = "SELECT * FROM product, reservation, user WHERE reservation.user_id = ? "
					+ "AND product.product_id = reservation.product_id AND reservation.user_id = user.user_id";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Reservation> reservations = new ArrayList<Reservation>();

			while (rs.next())
				reservations.add(parseReservation(rs));

			return reservations;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
