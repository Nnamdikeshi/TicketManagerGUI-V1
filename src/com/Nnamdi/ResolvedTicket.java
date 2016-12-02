package com.Nnamdi;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by nc0614dn on 11/1/2016.
 */
public class ResolvedTicket extends Ticket {

    protected Date dateResolved;
    protected String whyClosed;
    private int staticStringCounter;
    private int ticketID2;

    public ResolvedTicket(int priority, String reporter, String description, Date dateReported, String whyClosed) {
        super(priority, reporter, description);
        this.dateResolved = new Date();
        this.whyClosed = whyClosed;
        staticStringCounter++;
    }

    public int getTicketID2() {
        return ticketID2;
    }

    @Override
    public String toString() {
        //formats date and time and returns string
        String datePattern = "HH:mm 'on' MM/dd/yy";
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
        String dateResolvedString = format.format(dateResolved);

        return ("ID: " + this.ticketID2 + " Problem: " + this.description + " Priority: " + this.priority + " Reported by: "
                + this.reporter + " Reported on: " + this.dateReported + " Date Resolved: " + dateResolvedString + " Reason: " + this.whyClosed);
    }
}
