package com.testupstream.timetogo.model;

import com.testupstream.timetogo.time.DateTimeFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;

import java.text.MessageFormat;

public class Arrival {

    private long eta;
    private String route;
    private String stopName;

    @SuppressWarnings("unused") //Jaxb
    private Arrival() {}

    public Arrival(long eta, String route, String stopName) {
        this.eta = eta;
        this.route = route;
        this.stopName = stopName;
    }

    public String getEta() {
        DateTime arrivalTime = new DateTime(this.eta, DateTimeZone.getDefault());
        long duration = new Duration(DateTimeFactory.now().getMillis(), arrivalTime.getMillis()).getStandardMinutes();
        return MessageFormat.format("{0} mins", duration);
    }

    public String getRoute() {
        return route;
    }

    public String getStopName() {
        return stopName;
    }
}
