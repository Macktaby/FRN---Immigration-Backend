package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Sponsor;

public class SponsorBean {

	private Connection conn;

	public SponsorBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<Sponsor> getSponsors() {

		try {
			String sql = "SELECT * FROM sponsor";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			ArrayList<Sponsor> sponsors = new ArrayList<Sponsor>();

			while (rs.next())
				sponsors.add(parseSponsor(rs));

			return sponsors;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Sponsor parseSponsor(ResultSet rs) throws SQLException {
		Sponsor sponser = new Sponsor();

		sponser.setSponserID(rs.getInt("sponsor_id"));
		sponser.setName(rs.getString("name"));
		sponser.setImage(rs.getString("image"));

		return sponser;
	}

}
