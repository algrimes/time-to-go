package com.testupstream.timetogo.views.viewmodels;

public class ArrivalGroup {

    private String stop;
    private String destination;
    private String route;

    public ArrivalGroup(String stop, String destination, String route) {
        this.stop = stop;
        this.destination = destination;
        this.route = route;
    }

    public String getStop() {
        return stop;
    }

    public String getDestination() {
        return destination;
    }

    public String getRoute() {
        return route;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrivalGroup that = (ArrivalGroup) o;

        if (destination != null ? !destination.equals(that.destination) : that.destination != null) return false;
        if (route != null ? !route.equals(that.route) : that.route != null) return false;
        if (stop != null ? !stop.equals(that.stop) : that.stop != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stop != null ? stop.hashCode() : 0;
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (route != null ? route.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ArrivalGroup{" +
                "stop='" + stop + '\'' +
                ", destination='" + destination + '\'' +
                ", route='" + route + '\'' +
                '}';
    }
}
