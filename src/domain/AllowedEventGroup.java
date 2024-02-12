package domain;
/*
 * @author HongAnh
 * @created 07 / 02 / 2024 - 5:03 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */
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
