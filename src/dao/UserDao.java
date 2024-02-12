package dao;

import JDBCManager.JDBCConnection;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public boolean isEmailExists(String email) {
        try (Connection connection = JDBCConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE email = ?")) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
            JDBCConnection.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public User getUserById(int userId) {
        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT * FROM USERS WHERE UserId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String firstName = resultSet.getString("FirstName");
                        String lastName = resultSet.getString("LastName");
                        String email = resultSet.getString("Email");
                        String bio = resultSet.getString("Bio");
                        boolean isActive = resultSet.getBoolean("IsActive");
                        JDBCConnection.closeConnection(connection);
                        return new User(userId, firstName, lastName, email, bio, isActive, null, null, null, null, null);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
