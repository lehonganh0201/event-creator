package repository;

import dao.EventDao;
import domain.*;
import domain.EventEnum.EventStatus;

import java.util.List;

public class EventRepositoryImpl implements EventRepository{
    private final EventDao eventDao;

    public EventRepositoryImpl() {
        this.eventDao =new EventDao();
    }

    public List<Event> showListEvent(){
        return eventDao.viewAvailableEvents();
    }


    @Override
    public void updateEventInformation(String name, String description, String time, String location) {

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
    public void updateUserRole(User user, UserRole role) {

    }
}
