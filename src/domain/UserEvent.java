package domain;

public class UserEvent {
    private int userEventId;
    private User user;
    private Event event;

    private static int n = 1;

    public UserEvent(int userEventId, User user, Event event) {
        this.userEventId = userEventId;
        this.user = user;
        this.event = event;
    }

    public UserEvent(User user, Event event) {
        this.user = user;
        this.event = event;
        this.userEventId = n;
        ++n;
    }

    public User getUser() {
        return user;
    }
}
