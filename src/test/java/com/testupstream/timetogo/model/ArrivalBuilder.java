package com.testupstream.timetogo.model;

public class ArrivalBuilder {

    public static ArrivalBuilder anArrival() {
        return new ArrivalBuilder();
    }

    public Arrival build() {
        return new Arrival(123456L, "55", "Pritchards Rd", "Oxford Circus");
    }

}
