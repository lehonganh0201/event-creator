package service;

import domain.*;
import domain.EventEnum.EventStatus;
import repository.EventRepository;
import repository.EventRepositoryImpl;

import java.util.List;

public class EventService {

    private final EventRepositoryImpl eventRepository;

    public EventService() {
        eventRepository = new EventRepositoryImpl();
    }

    public List<Event> showListEvent(){
        return eventRepository.showListEvent();
    }
    
    public void viewEventDetails() {
        
    }

    
    public void updateEventInformation(String name, String description, String time, String location) {

    }

    
    public List<User> getRegisteredUsers() {
        return null;
    }

    
    public void addUserToEvent(User user) {

    }

    
    public User viewUserDetails(User user) {
        return null;
    }

    
    public void removeUserFromEvent(User user) {

    }

    
    public List<EventPost> getEventPosts() {
        return null;
    }

    
    public void addPost(EventPost post) {

    }

    
    public void deletePost(EventPost post) {

    }

    
    public EventStatus getEventStatus() {
        return null;
    }

    
    public void updateEventStatus(EventStatus status) {

    }

    
    public EventTicket getTicketInformation() {
        return null;
    }

    
    public EventTicket reserveAndConfirmTicket(User user) {
        return null;
    }

    
    public List<EventInvitation> getEventInvitations() {
        return null;
    }

    
    public void respondToInvitation(User user, EventInvitation invitation) {

    }

    
    public void sendInvitations(List<User> invitedUsers) {

    }

    
    public List<Group> getRelatedGroups() {
        return null;
    }

    
    public void addRelatedGroup(Group group) {

    }

    
    public void removeRelatedGroup(Group group) {

    }

    
    public List<UserRole> getUserRoles() {
        return null;
    }

    
    public void updateUserRole(User user, UserRole role) {

    }

    public void insertUserRole(User user,int roleId){
        eventRepository.insertUserRole(user.getUserId(), roleId);
    }

    public Event getEventById(int eventId) {
        return eventRepository.getEventById(eventId);
    }

    public Event getEventByInfo(Event event){
        return eventRepository.getEventByInfo(event);
    }

    public List<Event> getEventByUserId(int id){
        return eventRepository.getEventByCreationId(id);
    }

    public List<User> getUserByEvent(Event event){
        return eventRepository.getUserFromEvent(event);
    }
}
