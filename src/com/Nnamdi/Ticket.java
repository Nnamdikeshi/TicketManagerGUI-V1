package com.Nnamdi;

import java.util.*;
import java.text.SimpleDateFormat;

public class Ticket extends TicketManager{
    // These store our needed variables
    protected String description;
    protected Date dateReported;
    protected int priority;
    protected String reporter;
    protected static int staticTicketIDCounter = 1;
    protected int ticketID;

    //constructor
    public Ticket(int priority, String reporter, String description) {
        this.priority = priority;
        this.reporter = reporter;
        this.description = description;
        this.dateReported = new Date();
        this.ticketID = staticTicketIDCounter++;
    }

    public int getTicketID() {
        return ticketID;
    }
    public String getDescription() {
        return description;
    }
    public String getReporter() {
        return reporter;
    }
    public int getPriority() {
        return priority;
    }
    public Date getDateReported() {
        return dateReported;
    }

    public String toString() {
        //formats date and time and returns as string
        String datePattern = "HH:mm 'on' MM/dd/yy";
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
        String dateReportedString = format.format(dateReported);

        return ("ID: " + this.ticketID + " Problem: " + this.description + " Priority: " + this.priority + " Reported by: "
                + this.reporter + " Reported on: " + dateReportedString);
    }
}