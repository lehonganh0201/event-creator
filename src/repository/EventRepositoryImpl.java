package repository;

import JDBCManager.JDBCConnection;
import dao.EventDao;
import domain.*;
import domain.EventEnum.EventStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventRepositoryImpl implements EventRepository{
    private final EventDao eventDao;

    public EventRepositoryImpl() {
        this.eventDao =new EventDao();
    }

    public Event getEventById(int id){
        return eventDao.getEventById(id);
    }

    public List<Event> showListEvent(){
        return eventDao.viewAvailableEvents();
    }


    @Override
    public void updateEventInformation(String name, String description, String time, String location) {

    }

    @Override
    public Event getEventByInfo(Event event) {
        return eventDao.getEventByInfo(event);
    }

    @Override
    public List<Event> getEventByCreationId(int id) {
        List<Event> list = new ArrayList<>();

        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT * FROM event WHERE userId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Event event = extractEventFromResultSet(resultSet);
                        list.add(event);
                    }
                }
            }
            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<EventPost> getEventPosts() {
        return null;
    }

    @Override
    public void addPost(EventPost post) {

    }

    @Override
    public void deletePost(EventPost post) {

    }

    @Override
    public EventTicket getTicketInformation() {
        return null;
    }

    @Override
    public EventTicket reserveAndConfirmTicket(User user) {
        return null;
    }

    @Override
    public List<EventInvitation> getEventInvitations() {
        return null;
    }

    @Override
    public void respondToInvitation(User user, EventInvitation invitation) {

    }

    @Override
    public void sendInvitations(List<User> invitedUsers) {

    }

    @Override
    public List<Group> getRelatedGroups() {
        return null;
    }

    @Override
    public void addRelatedGroup(Group group) {

    }

    @Override
    public void removeRelatedGroup(Group group) {

    }

    @Override
    public List<UserRole> getUserRoles() {
        return null;
    }

    @Override
    public void updateUserRole(User user, int roleId) {
        try (Connection connection = JDBCConnection.getConnection()) {
            String updateUserRoleSql = "UPDATE USER_ROLE SET roleId = ? WHERE userId = ?";
            try (PreparedStatement updateUserRoleStatement = connection.prepareStatement(updateUserRoleSql)) {
                updateUserRoleStatement.setInt(1, roleId);
                updateUserRoleStatement.setInt(2, user.getUserId());

                int rowsAffected = updateUserRoleStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User role updated successfully in USER_ROLE table.");
                } else {
                    System.out.println("No user role found for the given ID in USER_ROLE table.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertUserRole(int userId, int roleId) {
        try (Connection connection = JDBCConnection.getConnection()) {
            String insertUserRoleSql = "INSERT INTO user_role (userId, roleId) VALUES (?, ?)";
            try (PreparedStatement insertUserRoleStatement = connection.prepareStatement(insertUserRoleSql)) {
                insertUserRoleStatement.setInt(1, userId);
                insertUserRoleStatement.setInt(2, roleId);

                int rowsAffected = insertUserRoleStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User role inserted successfully into USER_ROLE table.");
                } else {
                    System.out.println("Failed to insert user role into USER_ROLE table.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getUserFromEvent(Event event){
        List<User> list = new ArrayList<>();
        try(Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT u.* FROM Users u " +
                    "JOIN USER_EVENT ue ON u.userId = ue.userId " +
                    "WHERE ue.eventId = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,event.getEventId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                User user = extractUserFromResultSet(resultSet);
                list.add(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("userId"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        return user;
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
