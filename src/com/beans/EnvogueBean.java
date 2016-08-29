package com.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.models.Envogue;
import com.mysql.jdbc.Statement;

public class EnvogueBean {

	private Connection conn;

	public EnvogueBean() {
		conn = DBConnection.getActiveConnection();
	}

	private Envogue parseEnvogue(ResultSet rs) throws SQLException {
		Envogue envogue = new Envogue();

		envogue.setEnvogueID(rs.getInt("envogue_id"));
		envogue.setName(rs.getString("name"));
		envogue.setDescription(rs.getString("desc"));
		envogue.setImage(rs.getString("image"));

		return envogue;
	}

	public ArrayList<Envogue> getEnvogues() {
		try {
			String sql = "SELECT * FROM envogue";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			ArrayList<Envogue> envogues = new ArrayList<Envogue>();

			while (rs.next())
				envogues.add(parseEnvogue(rs));

			return envogues;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Envogue> searchEnvogues(String name) {
		try {
			String sql = "SELECT * FROM houses WHERE name LIKE ?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + name + "%");
			ResultSet rs = stmt.executeQuery();

			ArrayList<Envogue> envogues = new ArrayList<Envogue>();

			while (rs.next())
				envogues.add(parseEnvogue(rs));

			return envogues;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Envogue addEnvogue(Envogue envogue) {
		try {
			String sql = "INSERT INTO `envogue`(`name`, `desc`, `image`) VALUES (?,?,?)";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setString(1, envogue.getName());
			stmt.setString(2, envogue.getDescription());
			stmt.setString(3, envogue.getImage());

			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				envogue.setEnvogueID(rs.getInt(1));

				return envogue;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String updateEnvogue(Envogue envogue) {
		try {
			String sql = "UPDATE `envogue` SET `name`=?,`desc`=?,`image`=? WHERE `envogue_id`=?";

			PreparedStatement stmt;
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, envogue.getName());
			stmt.setString(2, envogue.getDescription());
			stmt.setString(3, envogue.getImage());
			stmt.setInt(4, envogue.getEnvogueID());

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

	public String deleteEnvogue(int id) {
		try {
			String sql = "DELETE FROM `envogue` WHERE `envogue_id` = ?";

			PreparedStatement stmt;

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			int nRows = stmt.executeUpdate();
			if (nRows == 1)
				return "true";

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return "false";
	}

}
