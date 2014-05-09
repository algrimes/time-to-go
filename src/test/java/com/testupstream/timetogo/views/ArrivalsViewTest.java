package com.testupstream.timetogo.views;

import com.testupstream.timetogo.model.Arrival;
import com.testupstream.timetogo.time.DateTimeFactory;
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

    @Test
    public void getEtas_shouldBeSortedByTime() throws Exception {
        DateTimeFactory.freezeTime(DateTimeFactory.now());


        Arrival arrivalOne = anArrival().withEta(DateTimeFactory.now().plusMinutes(7).getMillis()).build();
        Arrival arrivalTwo = anArrival().withEta(DateTimeFactory.now().plusMinutes(8).getMillis()).build();
        Arrival arrivalThree = anArrival().withEta(DateTimeFactory.now().plusMinutes(11).getMillis()).build();
        Arrival arrivalFour = anArrival().withEta(DateTimeFactory.now().plusMinutes(15).getMillis()).build();
        Arrival arrivalFive = anArrival().withEta(DateTimeFactory.now().plusMinutes(2).getMillis()).build();

        List<Arrival> arrivalsList = asList(
                arrivalOne,
                arrivalTwo,
                arrivalThree,
                arrivalFour,
                arrivalFive
        );

        ArrivalsView arrivalsView = new ArrivalsView(arrivalsList);

        List<Eta> etas = arrivalsView.getEtas(arrivalsView.getArrivalGroups().iterator().next());

        assertThat(etas.get(0).getEta(), is("2 mins"));
        assertThat(etas.get(1).getEta(), is("7 mins"));
        assertThat(etas.get(2).getEta(), is("8 mins"));
        assertThat(etas.get(3).getEta(), is("11 mins"));
        assertThat(etas.get(4).getEta(), is("15 mins"));
    }
}
