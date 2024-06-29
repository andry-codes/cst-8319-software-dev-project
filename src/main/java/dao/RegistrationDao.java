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

/* need to look at these more, kind of just took it from my assignment 3 without thinking too much*/
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
	public Register getUser(UUID id) {
		Register user = null;
		try {
			Connection connection = DBConnection.getConnectionToDatabase();
			
			String sqlGetUser = "SELECT id, username, password, email, isVerified FROM registration WHERE id = ?";
			PreparedStatement statement = connection.prepareStatement(sqlGetUser);
			statement.setString(1,  id.toString());
			
			ResultSet set = statement.executeQuery();
			
			if (set.next()) {
				user = new Register(
						UUID.fromString(set.getString("id")),
						set.getString("username"),
						set.getString("password"),
						set.getString("email"),
						set.getBoolean("isVerified")
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}


	@Override
	public boolean deleteUser(UUID id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(Register user) {
		// TODO Auto-generated method stub
		return false;
	}

}
