package com.testupstream.timetogo.views;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.testupstream.timetogo.model.Arrival;
import io.dropwizard.views.View;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.collect.Maps.newHashMap;
import static java.util.Arrays.asList;

public class ArrivalsView extends View {

    private final List<Arrival> arrivals;

    public ArrivalsView(List<Arrival> arrivals) {
        super("arrivals.ftl");
        this.arrivals = arrivals;
    }

    public Map<String, List<Arrival>> getArrivals() {
        return getArrivalsGroupedByDest(arrivals);
    }

    private Map<String, List<Arrival>> getArrivalsGroupedByDest(List<Arrival> arrivals) {
        Map<String, List<Arrival>> groupedArrivals = new HashMap<>();
        for (Arrival arrival : arrivals) {
            if (groupedArrivals.get(arrival.getDestination()) != null) {
                groupedArrivals.get(arrival.getDestination()).add(arrival);
            } else {
                groupedArrivals.put(arrival.getDestination(), Lists.<Arrival>newArrayList(arrival));
            }
        }
        return groupedArrivals;
    }

}
