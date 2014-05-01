package com.testupstream.timetogo.model;

public class ArrivalBuilder {


    private String destination = "Oxford Circus";

    public static ArrivalBuilder anArrival() {
        return new ArrivalBuilder();
    }

    public Arrival build() {
        return new Arrival(123456L, "55", "Pritchards Rd", destination);
    }

    public ArrivalBuilder withDestination(String destination) {
        this.destination = destination;
        return this;
    }
}
