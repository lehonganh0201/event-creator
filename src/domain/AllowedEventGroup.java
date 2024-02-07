package domain;

public class AllowedEventGroup {
    private Integer groupEventID;
    private Group group;
    private Event event;

    private static int n = 1;

    public AllowedEventGroup(Event event, Group group) {
        this.event = event;
        this.group = group;
        this.groupEventID = n;
        ++n;
    }

    public AllowedEventGroup(Integer groupEventID, Group group, Event event) {
        this.groupEventID = groupEventID;
        this.group = group;
        this.event = event;
    }
}
