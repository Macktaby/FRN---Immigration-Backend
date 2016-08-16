package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.models.User;
import com.mysql.jdbc.Statement;

public class House {

	private Connection conn;

	public House() {
		conn = DBConnection.getActiveConnection();
	}

}
