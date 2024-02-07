package repository;

import domain.*;
import domain.EventEnum.EventStatus;

import java.util.List;

public interface EventRepository {
    // Quản lý Thông Tin Sự Kiện

    void updateEventInformation(String name, String description, String time, String location);

    // Quản Lý Bài Đăng và Thảo Luận

    List<EventPost> getEventPosts();

    void addPost(EventPost post);

    void deletePost(EventPost post);

    // Quản Lý Đặt Vé

    EventTicket getTicketInformation();

    EventTicket reserveAndConfirmTicket(User user);

    // Quản Lý Mời Tham Gia

    List<EventInvitation> getEventInvitations();

    void respondToInvitation(User user, EventInvitation invitation);

    void sendInvitations(List<User> invitedUsers);

    // Quản Lý Nhóm Liên Quan

    List<Group> getRelatedGroups();

    void addRelatedGroup(Group group);

    void removeRelatedGroup(Group group);

    // Quản Lý Vai Trò và Quyền Hạn

    List<UserRole> getUserRoles();

    void updateUserRole(User user, UserRole role);
}
