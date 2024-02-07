package domain.EventEnum;

public enum Role {
    Admin(1),
    Host(2),
    Guest(3)
    ;


    private final int roleId;

    Role(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
