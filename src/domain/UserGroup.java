package domain;

public class UserGroup {
    private Integer userGroupID;
    private User user;
    private Group group;

    private static int n = 1;

    public UserGroup(User user, Group group) {
        this.user = user;
        this.group = group;
        this.userGroupID = n;
        ++n;
    }

    public UserGroup(Integer userGroupID, User user, Group group) {
        this.userGroupID = userGroupID;
        this.user = user;
        this.group = group;
    }
}
