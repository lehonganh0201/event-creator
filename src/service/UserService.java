package service;

import domain.*;
import domain.EventEnum.EventStatus;
import domain.EventEnum.Role;
import gui.LoginFrame;
import repository.UserRepositoryImpl;

import java.util.List;

public class UserService {
    private final UserRepositoryImpl userRepository;

    public static UserService getInstance(){
        return new UserService();
    }

    public UserService() {
        userRepository = new UserRepositoryImpl();
    }

    public UserRepositoryImpl getUserRepository() {
        return userRepository;
    }
    
    public boolean registerUser(User user) {
        return userRepository.registerUser(user);
    }
    
    public boolean loginUser(String email,String password) {
        boolean result = userRepository.loginUser(email,password);
        if(result == false){
            System.out.println("Cannot access account");
            return false;
        }
        else {
            return true;
        }
    }

    public void updatePersonalInformation(User user) {
        if(user.getFirstName().trim().isEmpty() || user.getLastName().trim().isEmpty() || user.getEmail().trim().isEmpty() || user.getPassword().trim().isEmpty()){
            System.out.println("Cannot updated your information");
            return;
        }
        else{
            LoginFrame.user.setFirstName(user.getFirstName());
            LoginFrame.user.setLastName(user.getLastName());
            LoginFrame.user.setPassword(user.getPassword());
            LoginFrame.user.setEmail(user.getEmail());
            LoginFrame.user.setBio(user.getBio());
            userRepository.updatePersonalInformation(LoginFrame.user);
        }
    }

    public Integer getUserId(String email,String password){
        return userRepository.getUserIdByDetails(email,password);
    }

    
    public boolean registerForEvent(User user, Event event,boolean check) {
        return userRepository.registerForEvent(user,event,check);
    }

    public Event getEventById(Integer id){
        return userRepository.getEventById(id);
    }

    
    public List<Event> viewRegisteredEvents(User user) {
        return userRepository.viewRegisteredEvents(user);
    }

    
    public Event viewEventDetails(User user, Event event) {
        return userRepository.viewEventDetails(user,event);
    }

    public User getUserById(Integer id){
        return userRepository.getUserById(id);
    }

    
    public boolean cancelEventRegistration(User user, Event event) {
        return userRepository.cancelEventRegistration(user,event);
    }

    public boolean createEvent(Event event){
        return userRepository.createEvent(event);
    }

    
    public boolean joinGroup(User user, Group group) {
        return userRepository.joinGroup(user, group);
    }

    public Group getGroup(){
        return userRepository.getGroupLast();
    }

    
    public boolean createGroup(User user, Group group) {
        return userRepository.createGroup(user, group);
    }

    public void addEventToGroup(Event event,Group group){
        userRepository.addEventToGroup(event.getEventId(),group.getGroupId());
    }

    public void addUserToGroup(User user,Group group){
        userRepository.addUserToGroup(user.getUserId(),group.getGroupId());
    }

    
    public boolean registerAndUseRole(User user, Group group, Role role) {
        return userRepository.registerAndUseRole(user, group, role);
    }

    
    public List<EventPost> viewEventPosts(Event event) {
        return userRepository.viewEventPosts(event);
    }

    
    public void createEventPost(User user, Event event, String content) {
        userRepository.createEventPost(user, event, content);
    }

    
    public EventTicket viewEventTicketInformation(Event event) {
        return userRepository.viewEventTicketInformation(event);
    }

    
    public EventTicket reserveAndConfirmTicket(User user, Event event) {
        return userRepository.reserveAndConfirmTicket(user, event);
    }

    
    public EventStatus viewEventStatus(Event event) {
        return userRepository.viewEventStatus(event);
    }

    
    public void viewEventStatistics(User user) {
        userRepository.viewEventStatistics(user);
    }

    
    public List<EventInvitation> viewEventInvitations(User user) {
        return userRepository.viewEventInvitations(user);
    }

    
    public void respondToEventInvitation(User user, EventInvitation invitation, boolean accept) {
        userRepository.respondToEventInvitation(user, invitation, accept);
    }

    
    public void sendEventInvitation(User user, Event event, List<User> invitedUsers) {
        userRepository.sendEventInvitation(user, event, invitedUsers);
    }

    public boolean managerAdminAccess(User user){
        return userRepository.acceptAdmin(user.getUserId());
    }

    public List<User> getUserList(){
        return userRepository.showListUser();
    }

    public boolean deleteUser(User user){
        if(managerAdminAccess(user)){
            System.out.println("Cannot delete admin account");
            return false;
        }
        else {
            userRepository.deleteUserFromGroup(user.getUserId());
            userRepository.deleteUserEventsByUserId(user.getUserId());
            userRepository.deleteEventsByUserId(user.getUserId());
            userRepository.deletePostsByUserId(user.getUserId());
            userRepository.deleteUserRolesByUserId(user.getUserId());
            return userRepository.deleteUser(user.getUserId());
        }
    }
    
    public boolean updateRole(User user, Group group, Role role) {
        return userRepository.updateRole(user, group, role);
    }

    public List<Group> getListGroup(Event event){
        return userRepository.getListGroup(event.getEventId());
    }

    public List<User> getListByEmail(String e){
        return userRepository.getListUserByEmail(e);
    }

    public int countUser(int month,int year){
        return userRepository.countUserRegisterForMonth(month,year);
    }

    public Group getGroupById(int id) {
        return userRepository.getGroupById(id);
    }
}
