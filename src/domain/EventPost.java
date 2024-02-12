package domain;
/*
 * @author HongAnh
 * @created 07 / 02 / 2024 - 5:03 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */
import java.sql.Date;

public class EventPost {
    private int eventPostId;
    private Event event;
    private String text;
    private Date creationDate;
    private User user;

    private static int n = 1;

    public EventPost(Event event, String text, Date creationDate, User user) {
        this.event = event;
        this.text = text;
        this.creationDate = creationDate;
        this.user = user;
        this.eventPostId = n;
        ++n;
    }

    public EventPost(int eventPostId, Event event, String text, Date creationDate, User user) {
        this.eventPostId = eventPostId;
        this.event = event;
        this.text = text;
        this.creationDate = creationDate;
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }
}