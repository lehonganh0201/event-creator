package domain;

import domain.EventEnum.InvitationResponseType;

import java.sql.Date;

public class EventInvitation {
    private Event event;
    private User user;
    private Date sentDate;
    private InvitationResponseType invitationResponseType;
    private String testResponse;
    private Date responseDate;

    public EventInvitation(Event event, User user, Date sentDate, InvitationResponseType invitationResponseType, String testResponse, Date responseDate) {
        this.event = event;
        this.user = user;
        this.sentDate = sentDate;
        this.invitationResponseType = invitationResponseType;
        this.testResponse = testResponse;
        this.responseDate = responseDate;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public InvitationResponseType getInvitationResponseType() {
        return invitationResponseType;
    }

    public void setInvitationResponseType(InvitationResponseType invitationResponseType) {
        this.invitationResponseType = invitationResponseType;
    }

    public String getTestResponse() {
        return testResponse;
    }

    public void setTestResponse(String testResponse) {
        this.testResponse = testResponse;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
}
