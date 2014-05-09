package com.testupstream.timetogo.views.viewmodels;

public class ArrivalGroupBuilder {


    private String destination = "Oxford Circus";
    private String stop = "Pritchards Rd";
    private String route = "55";

    public static ArrivalGroupBuilder anArrivalGroup() {
        return new ArrivalGroupBuilder();
    }

    public ArrivalGroup build() {
        return new ArrivalGroup(stop, destination, route);
    }

    public ArrivalGroupBuilder withDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public ArrivalGroupBuilder withStop(String stop) {
        this.stop = stop;
        return this;
    }

    public ArrivalGroupBuilder withRoute(String route) {
        this.route = route;
        return this;
    }
}
