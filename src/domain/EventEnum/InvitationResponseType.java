package domain.EventEnum;
/*
 * @author HongAnh
 * @created 07 / 02 / 2024 - 5:03 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */
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
