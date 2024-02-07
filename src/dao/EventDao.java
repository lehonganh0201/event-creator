package dao;

import JDBCManager.JDBCConnection;
import domain.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    public List<Event> viewAvailableEvents() {
        List<Event> availableEvents = new ArrayList<>();

        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT * FROM EVENT";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Event event = extractEventFromResultSet(resultSet);
                        availableEvents.add(event);
                    }
                }
            }
            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return availableEvents;
    }

    private Event extractEventFromResultSet(ResultSet resultSet) throws SQLException {
        Event event = new Event();
        event.setEventId(resultSet.getInt("eventId"));
        event.setName(resultSet.getString("name"));
        event.setDescription(resultSet.getString("description"));
        event.setCreationDate(resultSet.getDate("creationDate"));
        event.setRegistrationEndDate(resultSet.getDate("registrationEndDate"));
        event.setStartDateTime(resultSet.getDate("startDateTime"));
        event.setEndDateTime(resultSet.getDate("endDateTime"));
        event.setPlace(resultSet.getString("place"));
        event.setCapacity(resultSet.getInt("capacity"));
        event.setPrice(resultSet.getDouble("price"));
        return event;
    }
}
