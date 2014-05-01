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
    public void getArrivals_shouldDisplayArrivalsAlphabeticallyInDestinationOrder() throws Exception {

        String destinationBeginningWithA = "Aardvark St.";
        String destinationBeginningWithE = "Elephant Way";
        String destinationBeginningWithS = "Salamader Rd.";

        List<Arrival> arrivalsList = asList(
                anArrival().withDestination("Barracuda Ave.").build(),
                anArrival().withDestination(destinationBeginningWithS).build(),
                anArrival().withDestination(destinationBeginningWithA).build(),
                anArrival().withDestination(destinationBeginningWithE).build()
        );

        ArrivalsView arrivalsView = new ArrivalsView(arrivalsList);

        assertThat(arrivalsView.getArrivals().get(0).getDestination(), is(destinationBeginningWithA));
        assertThat(arrivalsView.getArrivals().get(3).getDestination(), is(destinationBeginningWithS));
        assertThat(arrivalsView.getArrivals().get(2).getDestination(), is(destinationBeginningWithE));
    }
}
