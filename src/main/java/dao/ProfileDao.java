package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import beans.Profile;
import services.ProfileService;

public class ProfileDao implements ProfileService {

    public Profile getProfileByUserId(int userId) {
        Profile profile = null;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();
            String sqlQuery = "SELECT * FROM profile WHERE userId = ?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                profile = new Profile(
                    resultSet.getInt("userId"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getInt("age"),
                    resultSet.getString("gender"),
                    resultSet.getInt("weight"),
                    resultSet.getInt("height")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile;
    }

    public void saveOrUpdateProfile(Profile profile) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();
            String sqlQuery = "SELECT * FROM profile WHERE userId = ?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setInt(1, profile.getUserId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String sqlUpdate = "UPDATE profile SET firstName = ?, lastName = ?, age = ?, gender = ?, weight = ?, height = ? WHERE userId = ?";
                statement = connection.prepareStatement(sqlUpdate);
                statement.setString(1, profile.getFirstName());
                statement.setString(2, profile.getLastName());
                statement.setInt(3, profile.getAge());
                statement.setString(4, profile.getGender());
                statement.setInt(5, profile.getWeight());
                statement.setInt(6, profile.getHeight());
                statement.setInt(7, profile.getUserId());
            } else {
                String sqlInsert = "INSERT INTO profile (userId, firstName, lastName, age, gender, weight, height) VALUES (?, ?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(sqlInsert);
                statement.setInt(1, profile.getUserId());
                statement.setString(2, profile.getFirstName());
                statement.setString(3, profile.getLastName());
                statement.setInt(4, profile.getAge());
                statement.setString(5, profile.getGender());
                statement.setInt(6, profile.getWeight());
                statement.setInt(7, profile.getHeight());
            }
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
