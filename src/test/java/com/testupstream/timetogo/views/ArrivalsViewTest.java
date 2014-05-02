package com.testupstream.timetogo.views;

import com.testupstream.timetogo.model.Arrival;
import org.junit.Test;

import java.util.List;

import static com.testupstream.timetogo.model.ArrivalBuilder.anArrival;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrivalsViewTest {

    @Test
    public void getArrivals_shouldGroupArrivalsByDestination() throws Exception {

        String destinationOne = "Aardvark St.";
        String destinationTwo = "Elephant Way";

        List<Arrival> arrivalsList = asList(
                anArrival().withDestination(destinationTwo).build(),
                anArrival().withDestination(destinationOne).build(),
                anArrival().withDestination(destinationOne).build(),
                anArrival().withDestination(destinationOne).build(),
                anArrival().withDestination(destinationTwo).build()
        );

        ArrivalsView arrivalsView = new ArrivalsView(arrivalsList);

        assertThat(arrivalsView.getArrivals().get(destinationOne).size(), is(3));
        for (Arrival arrival : arrivalsView.getArrivals().get(destinationOne)) {
            assertThat(arrival.getDestination(), is(destinationOne));
        }

        assertThat(arrivalsView.getArrivals().get(destinationTwo).size(), is(2));
        for (Arrival arrival : arrivalsView.getArrivals().get(destinationTwo)) {
            assertThat(arrival.getDestination(), is(destinationTwo));
        }
    }
}
