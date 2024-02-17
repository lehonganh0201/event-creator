package domain;
/*
 * @author HongAnh
 * @created 07 / 02 / 2024 - 5:03 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */
import java.util.ArrayList;
import java.util.List;


public class Group {
    private int groupId;
    private String name;
    private String description;
    private List<UserGroup> userGroups = new ArrayList<>();
    private List<AllowedEventGroup> groupEvents = new ArrayList<>();

    private static int n = 1;

    public Group(int groupId, String name, String description, List<UserGroup> userGroups, List<AllowedEventGroup> groupEvents) {
        this.groupId = groupId;
        this.name = name;
        this.description = description;
        this.userGroups = userGroups;
        this.groupEvents = groupEvents;
    }

    public Group(String name, String description, List<UserGroup> userGroups, List<AllowedEventGroup> groupEvents) {
        this.name = name;
        this.description = description;
        this.userGroups = userGroups;
        this.groupEvents = groupEvents;
        this.groupId = n;
        ++n;
    }

    public Group() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
}
