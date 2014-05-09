package com.testupstream.timetogo.views;

import com.testupstream.timetogo.model.Arrival;
import com.testupstream.timetogo.views.viewmodels.ArrivalGroup;
import com.testupstream.timetogo.views.viewmodels.Eta;
import org.junit.Test;

import java.util.List;

import static com.testupstream.timetogo.model.ArrivalBuilder.anArrival;
import static com.testupstream.timetogo.views.viewmodels.ArrivalGroupBuilder.anArrivalGroup;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrivalsViewTest {

    @Test
    public void getArrivals_shouldGroupArrivalsByDestinationStopAndRoute() throws Exception {

        String destinationOne = "Aardvark St.";
        String destinationTwo = "Elephant Way";
        String stopOne = "Barracuda Ln";
        String stopTwo = "Platypus St";
        String routeOne = "55";
        String routeTwo = "243";

        ArrivalGroup expectedArrivalGroupOne = anArrivalGroup()
                .withDestination(destinationOne)
                .withStop(stopOne)
                .withRoute(routeOne)
                .build();

        ArrivalGroup expectedArrivalGroupTwo = anArrivalGroup()
                .withDestination(destinationTwo)
                .withStop(stopTwo)
                .withRoute(routeTwo)
                .build();

        Arrival arrivalOne = anArrival().withStopName(stopOne).withDestination(destinationOne).withRoute(routeOne).build();
        Arrival arrivalTwo = anArrival().withStopName(stopOne).withDestination(destinationOne).withRoute(routeOne).build();
        Arrival arrivalThree = anArrival().withStopName(stopOne).withDestination(destinationOne).withRoute(routeOne).build();
        Arrival arrivalFour = anArrival().withStopName(stopTwo).withDestination(destinationTwo).withRoute(routeTwo).build();
        Arrival arrivalFive = anArrival().withStopName(stopTwo).withDestination(destinationTwo).withRoute(routeTwo).build();

        List<Arrival> arrivalsList = asList(
                arrivalOne,
                arrivalTwo,
                arrivalThree,
                arrivalFour,
                arrivalFive
        );

        ArrivalsView arrivalsView = new ArrivalsView(arrivalsList);

        assertThat(arrivalsView.getEtas(expectedArrivalGroupOne).size(), is(3));
        assertThat(arrivalsView.getEtas(expectedArrivalGroupOne).contains(new Eta(arrivalOne.getEta())), is(true));
        assertThat(arrivalsView.getEtas(expectedArrivalGroupOne).contains(new Eta(arrivalTwo.getEta())), is(true));
        assertThat(arrivalsView.getEtas(expectedArrivalGroupOne).contains(new Eta(arrivalThree.getEta())), is(true));
        assertThat(arrivalsView.getEtas(expectedArrivalGroupTwo).size(), is(2));
        assertThat(arrivalsView.getEtas(expectedArrivalGroupTwo).contains(new Eta(arrivalFour.getEta())), is(true));
        assertThat(arrivalsView.getEtas(expectedArrivalGroupTwo).contains(new Eta(arrivalFive.getEta())), is(true));

    }
}
