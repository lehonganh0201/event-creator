package domain;


import domain.EventEnum.EventStatus;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private int eventId;
    private String name;
    private String description;
    private Date creationDate;
    private Date registrationEndDate;
    private Date startDateTime;
    private Date endDateTime;
    private String place;
    private boolean isPublic;
    private int capacity;
    private double price;
    private int creationUserId;
    private List<UserEvent> users = new ArrayList<>();
    private List<EventPost> eventPosts = new ArrayList<>();
    private List<EventTicket> eventTickets = new ArrayList<>();
    private List<EventStatus> eventStatuses = new ArrayList<>();
    private List<AllowedEventGroup> groupEvents = new ArrayList<>();

    private static int n = 1;

    public Event(int eventId, String name, String description, Date creationDate, Date registrationEndDate, Date startDateTime, Date endDateTime, String place, boolean isPublic, int capacity, double price, List<UserEvent> users, List<EventPost> eventPosts, List<EventTicket> eventTickets, List<EventStatus> eventStatuses, List<AllowedEventGroup> groupEvents) {
        this.eventId = eventId;
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.registrationEndDate = registrationEndDate;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.place = place;
        this.isPublic = isPublic;
        this.capacity = capacity;
        this.price = price;
        this.users = users;
        this.eventPosts = eventPosts;
        this.eventTickets = eventTickets;
        this.eventStatuses = eventStatuses;
        this.groupEvents = groupEvents;
    }

    public Event(String name, String description, Date creationDate, Date registrationEndDate, Date startDateTime, Date endDateTime, String place, boolean isPublic, int capacity, double price, List<UserEvent> users, List<EventPost> eventPosts, List<EventTicket> eventTickets, List<EventStatus> eventStatuses, List<AllowedEventGroup> groupEvents) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.registrationEndDate = registrationEndDate;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.place = place;
        this.isPublic = isPublic;
        this.capacity = capacity;
        this.price = price;
        this.users = users;
        this.eventPosts = eventPosts;
        this.eventTickets = eventTickets;
        this.eventStatuses = eventStatuses;
        this.groupEvents = groupEvents;
        this.eventId = n;
        ++n;
    }

    public Event() {
    }

    public List<UserEvent> getUsers() {
        return users;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getRegistrationEndDate() {
        return registrationEndDate;
    }

    public void setRegistrationEndDate(Date registrationEndDate) {
        this.registrationEndDate = registrationEndDate;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCreationUserId() {
        return creationUserId;
    }

    public void setCreationUserId(int creationUserId) {
        this.creationUserId = creationUserId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                ", registrationEndDate=" + registrationEndDate +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", place='" + place + '\'' +
                ", isPublic=" + isPublic +
                ", capacity=" + capacity +
                ", price=" + price +
                ", users=" + users +
                ", eventPosts=" + eventPosts +
                ", eventTickets=" + eventTickets +
                ", eventStatuses=" + eventStatuses +
                ", groupEvents=" + groupEvents +
                '}';
    }
}
