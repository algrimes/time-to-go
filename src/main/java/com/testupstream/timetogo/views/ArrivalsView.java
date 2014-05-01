package com.testupstream.timetogo.views;

import com.testupstream.timetogo.model.Arrival;
import io.dropwizard.views.View;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrivalsView extends View {

    private final List<Arrival> arrivals;

    public ArrivalsView(List<Arrival> arrivals) {
        super("arrivals.ftl");
        this.arrivals = arrivals;
    }

    public List<Arrival> getArrivals() {
        return destinationSortedList(arrivals);
    }

    private List<Arrival> destinationSortedList(List<Arrival> arrivals) {
        Collections.sort(arrivals, new Comparator<Arrival>() {
            @Override
            public int compare(Arrival arrival1, Arrival arrival2) {
                return arrival1.getDestination().compareTo(arrival2.getDestination());
            }
        });
        return arrivals;
    }
}
