package domain.EventEnum;

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
