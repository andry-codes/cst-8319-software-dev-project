package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import beans.Register;

public class RegistrationDao {

    public void newUser(Register register) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sqlInsert = "INSERT INTO registration (username, password, email, isVerified) VALUES (?, ?, ?, false)";
            PreparedStatement statement = connection.prepareStatement(sqlInsert);

            statement.setString(1, register.getUsername());
            statement.setString(2, register.getPassword());
            statement.setString(3, register.getEmail());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveVerificationCode(String email, String verificationCode) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sqlInsert = "INSERT INTO verification_tokens (email, token) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sqlInsert);

            statement.setString(1, email);
            statement.setString(2, verificationCode);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getVerificationCode(String email) {
        String token = null;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sqlQuery = "SELECT token FROM verification_tokens WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                token = resultSet.getString("token");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return token;
    }

    public boolean verifyUser(String email, String verificationCode) {
        boolean isValid = false;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sqlCheckToken = "SELECT * FROM verification_tokens WHERE email = ? AND token = ?";
            PreparedStatement statement = connection.prepareStatement(sqlCheckToken);
            statement.setString(1, email);
            statement.setString(2, verificationCode);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                isValid = true;
                markUserAsVerified(email);
                deleteVerificationToken(email, verificationCode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    private void markUserAsVerified(String email) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sqlUpdate = "UPDATE registration SET isVerified = true WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sqlUpdate);
            statement.setString(1, email);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteVerificationToken(String email, String verificationCode) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sqlDelete = "DELETE FROM verification_tokens WHERE email = ? AND token = ?";
            PreparedStatement statement = connection.prepareStatement(sqlDelete);
            statement.setString(1, email);
            statement.setString(2, verificationCode);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    public boolean validateUser(String usernameOrEmail, String password) {
        boolean isValid = false;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sqlValidate = "SELECT * FROM registration WHERE (username = ? OR email = ?) AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sqlValidate);
            statement.setString(1, usernameOrEmail);
            statement.setString(2, usernameOrEmail);
            statement.setString(3, password);

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    public boolean isUserVerified(String usernameOrEmail) {
        boolean isVerified = false;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sqlCheckVerified = "SELECT isVerified FROM registration WHERE username = ? OR email = ?";
            PreparedStatement statement = connection.prepareStatement(sqlCheckVerified);
            statement.setString(1, usernameOrEmail);
            statement.setString(2, usernameOrEmail);

            ResultSet set = statement.executeQuery();

            if (set.next() && set.getBoolean("isVerified")) {
                isVerified = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isVerified;
    }

    public boolean emailExists(String email) {
        boolean exists = false;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sqlCheckEmail = "SELECT * FROM registration WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sqlCheckEmail);
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

    public String getEmailByUsernameOrEmail(String usernameOrEmail) {
        String email = null;
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sqlQuery = "SELECT email FROM registration WHERE username = ? OR email = ?";
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, usernameOrEmail);
            statement.setString(2, usernameOrEmail);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                email = resultSet.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    public void updatePassword(String email, String newPassword) {
        try {
            Connection connection = DBConnection.getConnectionToDatabase();

            String sqlUpdate = "UPDATE registration SET password = ? WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sqlUpdate);
            statement.setString(1, newPassword);
            statement.setString(2, email);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
