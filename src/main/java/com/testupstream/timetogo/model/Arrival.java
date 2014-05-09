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
    private String destination;

    @SuppressWarnings("unused") //Jaxb
    private Arrival() {}

    public Arrival(long eta, String route, String stopName, String destination) {
        this.eta = eta;
        this.route = route;
        this.stopName = stopName;
        this.destination = destination;
    }

    public long getEta() {
        return eta;
    }

    public String getRoute() {
        return route;
    }

    public String getStopName() {
        return stopName;
    }

    public String getDestination() {
        return destination;
    }
}
