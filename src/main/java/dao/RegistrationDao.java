package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import beans.Register;
import dao.DBConnection;
import services.RegistrationService;

public class RegistrationDao implements RegistrationService<Register> {

	@Override
	public void newUser(Register register) {
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			
			String sqlInsert = "INSERT INTO registration (id, username, password, email, isVerified) VALUES (?, ?, ?, ?, false)";
			PreparedStatement statement = connection.prepareStatement(sqlInsert);
			
			statement.setString(1, register.getUserID().toString());
			statement.setString(2, register.getUsername());
			statement.setString(3, register.getPassword());
			statement.setString(4, register.getEmail());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		

	@Override
	public boolean userCheck(String username) {
		boolean exists = false;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			
			String sqlCheckUser = "SELECT * FROM registration WHERE username = ?";
			PreparedStatement statement = connection.prepareStatement(sqlCheckUser);
			statement.setString(1, username);
			
			ResultSet set = statement.executeQuery();
			
			if (set.next()) {
				exists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}

	@Override 
	public boolean emailCheck(String email) {
		boolean exists = false;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			
			String sqlCheckUser = "SELECT * FROM registration WHERE email = ?";
			PreparedStatement statement = connection.prepareStatement(sqlCheckUser);
			statement.setString(1, email);
			
			ResultSet set = statement.executeQuery();
			
			if (set.next()) {
				exists = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exists;
	}
}
