package repository;

import domain.*;
import domain.EventEnum.EventStatus;
import domain.EventEnum.Role;

import java.util.List;

public interface UserRepository {
    // Quản lý Tài Khoản Người Dùng

    boolean registerUser(User user);

    boolean loginUser(String email,String password);

    void updatePersonalInformation(User user);

    int getUserIdByDetails(String email, String password);

    User getUserById(Integer id);

    // Tham Gia Sự Kiện

    boolean registerForEvent(User user, Event event,boolean check);

    boolean createEvent(Event event);

    // Quản Lý Sự Kiện đã Tham Gia

    List<Event> viewRegisteredEvents(User user);

    Event viewEventDetails(User user, Event event);

    boolean cancelEventRegistration(User user, Event event);

    // Quản Lý Nhóm và Vai Trò

    boolean joinGroup(User user, Group group);

    boolean createGroup(User user, Group group);

    boolean registerAndUseRole(User user, Group group, Role role);

    // Tương Tác với Bài Đăng Sự Kiện

    List<EventPost> viewEventPosts(Event event);

    void createEventPost(User user, Event event, String content);

    // Đặt Vé Cho Sự Kiện

    EventTicket viewEventTicketInformation(Event event);

    EventTicket reserveAndConfirmTicket(User user, Event event);

    EventStatus viewEventStatus(Event event);

    // Thực Hiện Thống Kê và Báo Cáo

    void viewEventStatistics(User user);

    // Quản Lý Mời Tham Gia Sự Kiện

    List<EventInvitation> viewEventInvitations(User user);

    void respondToEventInvitation(User user, EventInvitation invitation, boolean accept);

    void sendEventInvitation(User user, Event event, List<User> invitedUsers);

    // Quản Lý Vai Trò và Quyền Hạn

    boolean updateRole(User user, Group group, Role role);
}
