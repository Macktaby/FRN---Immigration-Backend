package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.Report;
import com.mysql.jdbc.Statement;

public class ReportBean {

	private Connection conn;

	public ReportBean() {
		conn = DBConnection.getActiveConnection();
	}

	public Report addReport(Report report) {

		try {
			String sql = "INSERT INTO `report`"
					+ "(`type`, `details`, `time`, `user_id`, `user_name`, `product_id`, `product_name`, `designer_id`, `designer_name`) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, report.getType());
			stmt.setString(2, report.getDetails());
			stmt.setTimestamp(3, report.getTime());
			stmt.setInt(4, report.getUserID());
			stmt.setString(5, report.getUserName());
			stmt.setInt(6, report.getProductID());
			stmt.setString(7, report.getProductName());
			stmt.setInt(8, report.getDesignerID());
			stmt.setString(9, report.getDesignerName());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				report.setReportID(rs.getInt(1));

				return report;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
