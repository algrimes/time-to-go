package com.testupstream.timetogo.model;

import java.util.Random;

public class ArrivalBuilder {


    private long eta = new Random(2).nextLong();
    private String destination = "Oxford Circus";
    private String stopName = "Pritchards Rd";
    private String route = "55";

    public static ArrivalBuilder anArrival() {
        return new ArrivalBuilder();
    }

    public Arrival build() {
        return new Arrival(eta, route, stopName, destination);
    }

    public ArrivalBuilder withDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public ArrivalBuilder withStopName(String stopName) {
        this.stopName = stopName;
        return this;
    }

    public ArrivalBuilder withRoute(String route) {
        this.route = route;
        return this;
    }

    public ArrivalBuilder withEta(long eta) {
        this.eta = eta;
        return this;
    }
}
