package domain.EventEnum;

public enum InvitationResponseType {
    Accept(1),
    Reject(2),
    Maybe(3);

    private final int value;

    InvitationResponseType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static InvitationResponseType fromValue(int value) {
        for (InvitationResponseType responseType : values()) {
            if (responseType.value == value) {
                return responseType;
            }
        }
        throw new IllegalArgumentException("Invalid InvitationResponseType value: " + value);
    }
}
