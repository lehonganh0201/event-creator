package dao;

import JDBCManager.JDBCConnection;
import domain.Event;

import java.sql.*;
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

    public Event getEventById(int id) {
        Event event = null;
        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT * FROM event WHERE eventId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int eventId = resultSet.getInt("eventId");
                        String name = resultSet.getString("name");
                        Date creationDate = resultSet.getDate("creationDate");
                        Date registrationEndDate = resultSet.getDate("registrationEndDate");
                        Date startDateTime = resultSet.getDate("startDateTime");
                        Date endDateTime = resultSet.getDate("endDateTime");
                        String place = resultSet.getString("place");
                        int capacity = resultSet.getInt("capacity");
                        double price = resultSet.getDouble("price");
                        int creationUserId = resultSet.getInt("userId");

                        event = new Event(eventId, name, creationDate, registrationEndDate, startDateTime, endDateTime, place, capacity, price, creationUserId);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    public Event getEventByInfo(Event event) {
        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT * FROM event WHERE name = ? AND creationDate = ? AND startDateTime = ? AND endDateTime = ? AND place = ? AND capacity = ? AND price = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, event.getName());
                preparedStatement.setDate(2, new java.sql.Date(event.getCreationDate().getTime()));
                preparedStatement.setDate(3, new java.sql.Date(event.getStartDateTime().getTime()));
                preparedStatement.setDate(4, new java.sql.Date(event.getEndDateTime().getTime()));
                preparedStatement.setString(5, event.getPlace());
                preparedStatement.setInt(6, event.getCapacity());
                preparedStatement.setDouble(7, event.getPrice());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return extractEventFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        event.setCreationUserId(resultSet.getInt("userId"));
        return event;
    }
}
