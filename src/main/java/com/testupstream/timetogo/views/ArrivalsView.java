package com.testupstream.timetogo.views;

import com.testupstream.timetogo.model.Arrival;
import io.dropwizard.views.View;

import java.util.List;

public class ArrivalsView extends View {

    private final List<Arrival> arrivals;

    public ArrivalsView(List<Arrival> arrivals) {
        super("arrivals.ftl");
        this.arrivals = arrivals;
    }

    public List<Arrival> getArrivals() {
        return arrivals;
    }
}
