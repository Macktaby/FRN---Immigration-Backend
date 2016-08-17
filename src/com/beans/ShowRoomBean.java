package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.ShowRoom;

public class ShowRoomBean {

	private Connection conn;

	public ShowRoomBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<ShowRoom> getShowRooms() {

		try {
			String sql = "SELECT * FROM showroom";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ArrayList<ShowRoom> showrooms = new ArrayList<ShowRoom>();

			while (rs.next())
				showrooms.add(parseShowRoom(rs));

			return showrooms;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private ShowRoom parseShowRoom(ResultSet rs) throws SQLException {

		ShowRoom showRoom = new ShowRoom();

		showRoom.setShowRoomID(rs.getInt("showroom_id"));
		showRoom.setName(rs.getString("name"));
		showRoom.setDescription(rs.getString("desc"));
		showRoom.setAddress(rs.getString("address"));
		showRoom.setLocation(rs.getString("location"));
		showRoom.setPhone(rs.getString("phone"));
		showRoom.setImage(rs.getString("image"));

		return showRoom;
	}

}
