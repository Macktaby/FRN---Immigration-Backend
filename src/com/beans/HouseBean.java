package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.House;
import com.models.User;
import com.mysql.jdbc.Statement;

public class HouseBean {

	private Connection conn;

	public HouseBean() {
		conn = DBConnection.getActiveConnection();
	}

	public ArrayList<House> getHouses() {
		// TODO Auto-generated method stub
		return null;
	}

}
