package domain;
/*
 * @author HongAnh
 * @created 07 / 02 / 2024 - 5:03 PM
 * @project IntelliJ IDEA
 * @social Github: https://github.com/lehonganh0201
 */
import java.sql.Date;

public class EventTicket {
    private int eventTicketId;
    private Event event;
    private User user;
    private boolean isPaid;
    private Date paidDate;

    private static int n = 1;

    public EventTicket(Event event, User user, boolean isPaid, Date paidDate) {
        this.event = event;
        this.user = user;
        this.isPaid = isPaid;
        this.paidDate = paidDate;
        this.eventTicketId = n;
        ++n;
    }

    public EventTicket(int eventTicketId, Event event, User user, boolean isPaid, Date paidDate) {
        this.eventTicketId = eventTicketId;
        this.event = event;
        this.user = user;
        this.isPaid = isPaid;
        this.paidDate = paidDate;
    }
}
