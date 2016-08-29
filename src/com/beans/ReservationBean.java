package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.Reservation;
import com.mysql.jdbc.Statement;

public class ReservationBean {

	private Connection conn;

	public ReservationBean() {
		conn = DBConnection.getActiveConnection();
	}

	public Reservation addReservation(Reservation reservation) {
		try {
			String sql = "INSERT INTO `reservation`"
					+ "(`user_id`, `product_id`, `quantity`, `time`) VALUES ( ? , ? , ? , ? )";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, reservation.getUserID());
			stmt.setInt(2, reservation.getProductID());
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

	public String cancelReservation(int reservationID, int quantity) {

		if (quantity < 0)
			return "Reservation Not Found";

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
		}

		return "false";
	}

}
