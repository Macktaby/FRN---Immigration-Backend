package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.models.User;
import com.mysql.jdbc.Statement;

public class UserBean {

	private Connection conn;

	public UserBean() {
		conn = DBConnection.getActiveConnection();
	}

	public User addUser(String userName, String password, String nickName, String email, String website, String phone,
			boolean isAdmin) {

		try {
			String sql = "Insert into user (`user_name`, `password`, `nicename`, `email`, `url`, "
					+ "`phone`, `register_time`, `activation_key`, `status`, `is_admin`)"
					+ "VALUES  ( ? , ? , ? , ? , ? , ?  ,? , ? , ? , ? )";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			Date date = new Date();
			Timestamp currentTime = new Timestamp(date.getTime());

			stmt.setString(1, userName);
			stmt.setString(2, password);
			stmt.setString(3, nickName);
			stmt.setString(4, email);
			stmt.setString(5, website);
			stmt.setString(6, phone);
			stmt.setTimestamp(7, currentTime);
			stmt.setString(8, "");
			stmt.setString(9, "");
			stmt.setBoolean(10, isAdmin);
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				User user = new User();

				user.setUserID(rs.getInt(1));
				user.setUserName(userName);
				user.setPassword(password);
				user.setNickName(nickName);
				user.setEmail(email);
				user.setWebsite(website);
				user.setPhone(phone);
				user.setRegisterTime(currentTime);
				user.setActivationKey("");
				user.setUserStatus("");
				user.setAdmin(isAdmin);

				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public User getUser(String email, String pass) {

		try {
			String sql = "Select * from user where `email` = ? and `password` = ?";
			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				User user = new User();

				user.setUserID(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setNickName(rs.getString("nicename"));
				user.setEmail(rs.getString("email"));
				user.setWebsite(rs.getString("url"));
				user.setPhone(rs.getString("phone"));
				user.setRegisterTime(rs.getTimestamp("register_time"));
				user.setActivationKey(rs.getString("activation_key"));
				user.setUserStatus(rs.getString("status"));
				user.setAdmin(rs.getBoolean("is_admin"));

				return user;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}