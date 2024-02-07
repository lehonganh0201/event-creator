package repository;

import JDBCManager.JDBCConnection;
import dao.UserDao;
import domain.*;
import domain.EventEnum.EventStatus;
import domain.EventEnum.InvitationResponseType;
import domain.EventEnum.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    public UserRepositoryImpl() {
        userDao = new UserDao();
    }

    @Override
    public boolean registerUser(User user) {
        if (!userDao.isEmailExists(user.getEmail())) {
            try (Connection connection = JDBCConnection.getConnection()) {
                String sql = "INSERT INTO USERS(firstName, lastName, email, password) VALUES (?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setString(1, user.getFirstName());
                    preparedStatement.setString(2, user.getLastName());
                    preparedStatement.setString(3, user.getEmail());
                    preparedStatement.setString(4, user.getPassword());

                    int rowsAffected = preparedStatement.executeUpdate();

                    return rowsAffected > 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        } else {
            System.out.println("Da co tai khoan dang ky bang email nay");
        }
        return false;
    }

    @Override
    public boolean loginUser(String email,String password) {
        if (userDao.isEmailExists(email)) {
            try (Connection connection = JDBCConnection.getConnection()) {
                String sql = "SELECT * FROM USERS WHERE password = ? AND email = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, password);
                    preparedStatement.setString(2, email);
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            // User found, create a User object and return it
                            return true;
                        } else {
                            return false;
                        }
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return false; // User not found
        }
        return false;
    }

    @Override
    public int getUserIdByDetails(String email, String password) {
        Connection connection = JDBCConnection.getConnection();
        try {
            String sql = "SELECT userId FROM USERS WHERE email = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("userId");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public User getUserById(Integer id) {
        User user = null;

        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT * FROM USERS WHERE userId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        user = new User();
                        user.setUserId(resultSet.getInt("userId"));
                        user.setFirstName(resultSet.getString("firstName"));
                        user.setLastName(resultSet.getString("lastName"));
                        user.setEmail(resultSet.getString("email"));
                        user.setBio(resultSet.getString("bio"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void updatePersonalInformation(User user) {
        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "UPDATE USERS SET firstName=?, lastName=?, email=?, bio=?, password = ? WHERE userId=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, user.getFirstName());
                preparedStatement.setString(2, user.getLastName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getBio());
                preparedStatement.setString(5,user.getPassword());
                preparedStatement.setInt(6, user.getUserId());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User information updated successfully.");
                } else {
                    System.out.println("No user found for the given ID.");
                }
            }
            JDBCConnection.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean registerForEvent(User user, Event event) {
        try (Connection connection = JDBCConnection.getConnection()) {
            if (isUserRegisteredForEvent(connection, user.getUserId(), event.getEventId())) {
                System.out.println("User is already registered for the event.");
                return false;
            }

            String sql = "INSERT INTO USER_EVENT(UserId, EventId) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, user.getUserId());
                preparedStatement.setInt(2, event.getEventId());

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("User successfully registered for the event.");
                    return true;
                } else {
                    System.out.println("Registration failed. Please try again.");
                }
            }
            JDBCConnection.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createEvent(Event event) {
        try (Connection connection = JDBCConnection.getConnection()){

            String sql = "INSERT INTO event (name, creationDate, registrationEndDate, startDateTime, endDateTime, place, capacity, price, userID) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, event.getName());

            Calendar currentCalendar = Calendar.getInstance();
            Date currentDate = new Date(currentCalendar.getTimeInMillis());
            preparedStatement.setDate(2, new java.sql.Date(currentDate.getTime()));
            preparedStatement.setDate(3, new java.sql.Date(event.getRegistrationEndDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(event.getStartDateTime().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(event.getEndDateTime().getTime()));
            preparedStatement.setString(6, event.getPlace());
            preparedStatement.setInt(7, event.getCapacity());
            preparedStatement.setDouble(8, event.getPrice());
            preparedStatement.setInt(9, event.getCreationUserId());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Event> viewRegisteredEvents(User user) {
        List<Event> registeredEvents = new ArrayList<>();

        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT e.* FROM EVENT e " +
                    "JOIN USER_EVENT ue ON e.EventId = ue.EventId " +
                    "WHERE ue.UserId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, user.getUserId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Event event = extractEventFromResultSet(resultSet);
                        registeredEvents.add(event);
                    }
                }
            }
            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registeredEvents;
    }


    @Override
    public Event viewEventDetails(User user, Event event) {
        Event detailedEvent = null;

        try (Connection connection = JDBCConnection.getConnection()) {
            if (isUserRegisteredForEvent(connection, user.getUserId(), event.getEventId())) {
                String sql = "SELECT * FROM EVENT WHERE EventId = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, event.getEventId());

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            detailedEvent = extractEventFromResultSet(resultSet);
                        }
                    }
                }
            } else {
                System.out.println("User is not registered for the specified event.");
            }

            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detailedEvent;
    }

    public Event getEventById(Integer eventId) {
        Event detailedEvent = null;

        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT * FROM EVENT WHERE EventId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, eventId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        detailedEvent = extractEventFromResultSet(resultSet);
                    }
                }
            }
            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detailedEvent;
    }

    @Override
    public boolean cancelEventRegistration(User user, Event event) {
        try (Connection connection = JDBCConnection.getConnection()) {
            if (isUserRegisteredForEvent(connection, user.getUserId(), event.getEventId())) {
                String sql = "DELETE FROM USER_EVENT WHERE UserId = ? AND EventId = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, user.getUserId());
                    preparedStatement.setInt(2, event.getEventId());

                    int rowsAffected = preparedStatement.executeUpdate();
                    JDBCConnection.closeConnection(connection);
                    return rowsAffected > 0;
                }
            } else {
                System.out.println("User is not registered for the specified event.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean joinGroup(User user, Group group) {
        try (Connection connection = JDBCConnection.getConnection()) {
            if (!isUserInGroup(connection, user.getUserId(), group.getGroupId())) {
                String sql = "INSERT INTO USER_GROUP(UserId, GroupId) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, user.getUserId());
                    preparedStatement.setInt(2, group.getGroupId());
                    int rowsAffected = preparedStatement.executeUpdate();
                    JDBCConnection.closeConnection(connection);
                    return rowsAffected > 0;
                }
            } else {
                System.out.println("User is already a member of the specified group.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean createGroup(User user, Group group) {
        try (Connection connection = JDBCConnection.getConnection()) {
            if (!isGroupNameUnique(connection, group.getName())) {
                System.out.println("Group name is already taken. Choose a different name.");
                return false;
            }

            String createGroupSql = "INSERT INTO EVENT_GROUP(Name, Description) VALUES (?, ?)";
            try (PreparedStatement createGroupStatement = connection.prepareStatement(createGroupSql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                createGroupStatement.setString(1, group.getName());
                createGroupStatement.setString(2, group.getDescription());

                int rowsAffected = createGroupStatement.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet generatedKeys = createGroupStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int groupId = generatedKeys.getInt(1);

                            String addUserToGroupSql = "INSERT INTO USER_GROUP(UserId, GroupId) VALUES (?, ?)";
                            try (PreparedStatement addUserToGroupStatement = connection.prepareStatement(addUserToGroupSql)) {
                                addUserToGroupStatement.setInt(1, user.getUserId());
                                addUserToGroupStatement.setInt(2, groupId);

                                int addUserToGroupRowsAffected = addUserToGroupStatement.executeUpdate();
                                return addUserToGroupRowsAffected > 0;
                            }
                        }
                    }
                }
            }
            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean registerAndUseRole(User user, Group group, Role role) {
        try (Connection connection = JDBCConnection.getConnection()) {
            if (!isUserInGroup(connection, user.getUserId(), group.getGroupId())) {
                System.out.println("User is not a member of the specified group. Please join the group first.");
                return false;
            }

            if (isUserHasRoleInGroup(connection, user.getUserId(), group.getGroupId(), role.getRoleId())) {
                System.out.println("User already has the specified role in the group.");
                return false;
            }

            String sql = "INSERT INTO USER_ROLE(UserId, RoleId) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, user.getUserId());
                preparedStatement.setInt(2, role.getRoleId());

                int rowsAffected = preparedStatement.executeUpdate();
                JDBCConnection.closeConnection(connection);
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<EventPost> viewEventPosts(Event event) {
        List<EventPost> eventPosts = new ArrayList<>();

        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT * FROM EVENT_POST WHERE EventId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, event.getEventId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int eventPostId = resultSet.getInt("EventPostId");
                        String text = resultSet.getString("Text");
                        Date creationDate = resultSet.getDate("CreationDate");
                        int userId = resultSet.getInt("UserId");

                        User user = userDao.getUserById(userId);

                        EventPost eventPost = new EventPost(eventPostId, event, text, creationDate, user);
                        eventPosts.add(eventPost);
                    }
                }
            }
            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventPosts;
    }

    @Override
    public void createEventPost(User user, Event event, String content) {
        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "INSERT INTO EVENT_POST(EventId, Text, CreationDate, UserId) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, event.getEventId());
                preparedStatement.setString(2, content);
                preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                preparedStatement.setInt(4, user.getUserId());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Event post created successfully.");
                } else {
                    System.out.println("Failed to create event post.");
                }
            }
            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EventTicket viewEventTicketInformation(Event event) {
        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT * FROM EVENT_TICKET WHERE EventId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, event.getEventId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int eventTicketId = resultSet.getInt("EventTicketId");
                        boolean isPaid = resultSet.getBoolean("IsPaid");
                        Date paidDate = resultSet.getDate("PaidDate");
                        int userId = resultSet.getInt("UserId");

                        return new EventTicket(eventTicketId, event, userDao.getUserById(userId), isPaid, paidDate);
                    }
                }
            }
            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public EventTicket reserveAndConfirmTicket(User user, Event event) {
        try (Connection connection = JDBCConnection.getConnection()) {
            // Check if the user already has a reserved ticket
            if (isUserHasReservedTicket(connection, user.getUserId(), event.getEventId())) {
                System.out.println("User already has a reserved ticket for the event.");
                return null;
            }
            // Insert a new reserved ticket for the user and event
            int eventTicketId = insertReservedTicket(connection, user.getUserId(), event.getEventId());

            // Retrieve the details of the newly reserved ticket
            JDBCConnection.closeConnection(connection);

            return getEventTicketById(connection, eventTicketId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public EventStatus viewEventStatus(Event event) {
        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT StatusId FROM EVENT_STATUS WHERE EventId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, event.getEventId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int statusId = resultSet.getInt("StatusId");
                        return mapStatusIdToEnum(statusId);
                    }
                }
            }
            JDBCConnection.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void viewEventStatistics(User user) {
        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT COUNT(*) FROM EVENT_TICKET WHERE UserId = ? AND IsPaid = true";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, user.getUserId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int attendedEventsCount = resultSet.getInt(1);
                        System.out.println("User " + user.getUserId() + " has attended " + attendedEventsCount + " events.");
                    }
                }
            }
            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EventInvitation> viewEventInvitations(User user) {
        List<EventInvitation> invitations = new ArrayList<>();

        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "SELECT * FROM EVENT_INVITATION WHERE UserId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, user.getUserId());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Event event = getEventById(connection, resultSet.getInt("EventId"));
                        Date sentDate = resultSet.getDate("SentDate");
                        InvitationResponseType responseType = InvitationResponseType.fromValue(resultSet.getInt("ResponseType"));
                        String testResponse = resultSet.getString("TestResponse");
                        Date responseDate = resultSet.getDate("ResponseDate");

                        EventInvitation invitation = new EventInvitation(event, user, sentDate, responseType, testResponse, responseDate);
                        invitations.add(invitation);
                    }
                }
            }
            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invitations;
    }

    @Override
    public void respondToEventInvitation(User user, EventInvitation invitation, boolean accept) {
        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "UPDATE EVENT_INVITATION SET ResponseType = ?, TestResponse = ?, ResponseDate = ? WHERE UserId = ? AND EventId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                if (accept) {
                    preparedStatement.setInt(1, InvitationResponseType.Accept.getValue());
                    preparedStatement.setString(2, "Accept");
                } else {
                    preparedStatement.setInt(1, InvitationResponseType.Reject.getValue());
                    preparedStatement.setString(2, "Reject");
                }

                preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
                preparedStatement.setInt(4, user.getUserId());
                preparedStatement.setInt(5, invitation.getEvent().getEventId());

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Event invitation response updated successfully.");
                } else {
                    System.out.println("Failed to update event invitation response.");
                }
            }
            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEventInvitation(User sender, Event event, List<User> invitedUsers) {
        try (Connection connection = JDBCConnection.getConnection()) {
            String sql = "INSERT INTO EVENT_INVITATION (EventId, UserId, SentDate, ResponseType, TestResponse, ResponseDate) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                for (User invitedUser : invitedUsers) {
                    preparedStatement.setInt(1, event.getEventId());
                    preparedStatement.setInt(2, invitedUser.getUserId());
                    preparedStatement.setDate(3, new java.sql.Date(System.currentTimeMillis()));
                    preparedStatement.setInt(4, InvitationResponseType.Maybe.getValue());
                    preparedStatement.setString(5, "");
                    preparedStatement.setDate(6, null);

                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("Event invitation sent to user with ID: " + invitedUser.getUserId());
                    } else {
                        System.out.println("Failed to send event invitation to user with ID: " + invitedUser.getUserId());
                    }
                }
            }
            JDBCConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateRole(User user, Group group, Role role) {
        try (Connection connection = JDBCConnection.getConnection()) {
            if (!isUserInGroup(connection, user.getUserId(), group.getGroupId())) {
                System.out.println("User is not in the specified group.");
                return false;
            }

            String sql = "UPDATE USER_ROLE SET RoleId = ? WHERE UserId = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, role.getRoleId());
                preparedStatement.setInt(2, user.getUserId());

                int rowsUpdated = preparedStatement.executeUpdate();
                JDBCConnection.closeConnection(connection);

                if (rowsUpdated > 0) {
                    System.out.println("User role updated successfully.");
                    return true;
                } else {
                    System.out.println("Failed to update user role.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("Id"));
        user.setFirstName(resultSet.getString("firstName"));
        user.setLastName(resultSet.getString("lastName"));
        user.setEmail(resultSet.getString("email"));
        user.setBio(resultSet.getString("bio"));

        return user;
    }

    private boolean isUserRegisteredForEvent(Connection connection, int userId, int eventId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM USER_EVENT WHERE UserId = ? AND EventId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, eventId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    private boolean isUserInGroup(Connection connection, int userId, int groupId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM USER_GROUP WHERE UserId = ? AND GroupId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, groupId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }

        return false;
    }

    private boolean isGroupNameUnique(Connection connection, String groupName) throws SQLException {
        String sql = "SELECT COUNT(*) FROM GROUP WHERE Name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, groupName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
        }

        return false;
    }

    private boolean isUserHasRoleInGroup(Connection connection, int userId, int groupId, int roleId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM USER_ROLE WHERE UserId = ? AND GroupId = ? AND RoleId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, groupId);
            preparedStatement.setInt(3, roleId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }

        return false;
    }

    private boolean isUserHasReservedTicket(Connection connection, int userId, int eventId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM EVENT_TICKET WHERE UserId = ? AND EventId = ? AND IsPaid = false";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, eventId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    private int insertReservedTicket(Connection connection, int userId, int eventId) throws SQLException {
        String sql = "INSERT INTO EVENT_TICKET(EventId, UserId, IsPaid) VALUES (?, ?, false)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, eventId);
            preparedStatement.setInt(2, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1); // Return the generated EventTicketId
                    }
                }
            }
        }
        return -1; // Return -1 if the ticket insertion fails
    }

    private EventTicket getEventTicketById(Connection connection, int eventTicketId) throws SQLException {
        String sql = "SELECT * FROM EVENT_TICKET WHERE EventTicketId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, eventTicketId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    boolean isPaid = resultSet.getBoolean("IsPaid");
                    Date paidDate = resultSet.getDate("PaidDate");

                    return new EventTicket(eventTicketId, null, null, isPaid, paidDate);
                }
            }
        }
        return null;
    }

    private EventStatus mapStatusIdToEnum(int statusId) {
        for (EventStatus status : EventStatus.values()) {
            if (status.getValue() == statusId) {
                return status;
            }
        }
        return null;
    }

    private Event getEventById(Connection connection, int eventId) throws SQLException {
        String sql = "SELECT * FROM EVENT WHERE EventId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, eventId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve event details and create an Event object
                    int id = resultSet.getInt("EventId");
                    String name = resultSet.getString("Name");
                    String description = resultSet.getString("Description");
                    Date creationDate = resultSet.getDate("CreationDate");
                    Date registrationEndDate = resultSet.getDate("RegistrationEndDate");
                    Date startDateTime = resultSet.getDate("StartDateTime");
                    Date endDateTime = resultSet.getDate("EndDateTime");
                    String place = resultSet.getString("Place");
                    boolean isPublic = resultSet.getBoolean("IsPublic");
                    int capacity = resultSet.getInt("Capacity");
                    double price = resultSet.getDouble("Price");

                    return new Event(id, name, description, creationDate, registrationEndDate, startDateTime, endDateTime, place, isPublic, capacity, price, null, null, null, null, null);
                }
            }
        }

        return null;
    }

}
