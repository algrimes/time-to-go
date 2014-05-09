package com.testupstream.timetogo.views;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Sets;
import com.testupstream.timetogo.model.Arrival;
import com.testupstream.timetogo.views.viewmodels.ArrivalGroup;
import com.testupstream.timetogo.views.viewmodels.Eta;
import io.dropwizard.views.View;

import java.util.List;
import java.util.Set;

public class ArrivalsView extends View {

    private Set<ArrivalGroup> arrivalGroups;
    private LinkedListMultimap<ArrivalGroup, Eta> etas;

    public ArrivalsView(List<Arrival> arrivals) {
        super("arrivals.ftl");
        groupByDestStopAndRoute(arrivals);
    }

    //It would be a map of groups and etas,
    //but freemarker doesnt allow non-string map keys

    public Set<ArrivalGroup> getArrivalGroups() {
        return arrivalGroups;
    }

    public List<Eta> getEtas(ArrivalGroup arrivalGroup) {
        return etas.get(arrivalGroup);
    }

    private void groupByDestStopAndRoute(List<Arrival> arrivals) {
        this.arrivalGroups = Sets.newHashSet();
        this.etas = LinkedListMultimap.create();
        for (Arrival arrival : arrivals) {
            ArrivalGroup group = new ArrivalGroup(arrival.getStopName(), arrival.getDestination(), arrival.getRoute());
            arrivalGroups.add(group);
            etas.put(group, new Eta(arrival.getEta()));
        }
    }

}
