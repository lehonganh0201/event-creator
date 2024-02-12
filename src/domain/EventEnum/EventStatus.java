package domain.EventEnum;
/*
 * @author HongAnh
 * @created 07 / 02 / 2024 - 5:03 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */
public enum EventStatus {
    Draft(1),
    OpenToRegistrations(2),
    ClosedToRegistrations(3),
    Ongoing(4),
    Past(5),
    Cancelled(6)
    ;

    private final int value;

    EventStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
