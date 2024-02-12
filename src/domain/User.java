package domain;
/*
 * @author HongAnh
 * @created 07 / 02 / 2024 - 5:03 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */
import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String bio;
    private boolean isActive;
    private List<EventTicket> eventTickets = new ArrayList<>();
    private List<UserEvent> events = new ArrayList<>();
    private List<EventPost> eventPosts = new ArrayList<>();
    private List<UserRole> userRoles = new ArrayList<>();
    private List<UserGroup> userGroups = new ArrayList<>();

    private static int n=1;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String email, String bio, boolean isActive, List<EventTicket> eventTickets, List<UserEvent> events, List<EventPost> eventPosts, List<UserRole> roles, List<UserGroup> groups) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bio = bio;
        this.isActive = isActive;
        this.eventTickets = eventTickets;
        this.events = events;
        this.eventPosts = eventPosts;
        this.userRoles = roles;
        this.userGroups = groups;
    }

    public User(String firstName, String lastName, String email, String bio, boolean isActive, List<EventTicket> eventTickets, List<UserEvent> events, List<EventPost> eventPosts, List<UserRole> roles, List<UserGroup> groups) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.bio = bio;
        this.isActive = isActive;
        this.eventTickets = eventTickets;
        this.events = events;
        this.eventPosts = eventPosts;
        this.userRoles = roles;
        this.userGroups = groups;
        this.userId= n;
        ++n;
    }

    public List<EventTicket> getEventTickets() {
        return eventTickets;
    }

    public List<UserEvent> getEvents() {
        return events;
    }

    public List<EventPost> getEventPosts() {
        return eventPosts;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        try{
            if(email.trim().isEmpty()){
                throw new Exception("Email khong duoc de trong");
            }
            this.email = email;
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public boolean isActive() {
        return isActive;
    }

    public static int getN() {
        return n;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        try {
            if(firstName.trim().isEmpty()){
                throw new Exception("Ten khong duoc de trong");
            }
            this.firstName = firstName;
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void setLastName(String lastName) {
        try {
            if(lastName.trim().isEmpty()){
                throw new Exception("Ten khong duoc de trong");
            }
            this.lastName = lastName;
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public String getFullName(){
        return firstName +" " + lastName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setEventTickets(List<EventTicket> eventTickets) {
        this.eventTickets = eventTickets;
    }

    public void setEvents(List<UserEvent> events) {
        this.events = events;
    }

    public void setEventPosts(List<EventPost> eventPosts) {
        this.eventPosts = eventPosts;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public static void setN(int n) {
        User.n = n;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try {
            if(password.trim().isEmpty()){
                throw new Exception("Mat khau khong duoc de trong");
            }
            this.password = password;
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }
}