package domain;
/*
 * @author HongAnh
 * @created 07 / 02 / 2024 - 5:03 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */
import domain.EventEnum.Role;

public class UserRole {
    private Integer userRoleID;
    private User user;
    private Role role;

    private static int n = 1;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
        this.userRoleID = n;
        ++n;
    }

    public UserRole(Integer userRoleID, User user, Role role) {
        this.userRoleID = userRoleID;
        this.user = user;
        this.role = role;
    }
}
