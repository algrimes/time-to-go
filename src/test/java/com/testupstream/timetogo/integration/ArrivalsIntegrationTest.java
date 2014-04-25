package com.testupstream.timetogo.integration;

import com.testupstream.timetogo.integration.harness.IntegrationTestRunner;
import com.testupstream.timetogo.integration.steps.IntegrationTestStepWrapper;
import com.testupstream.timetogo.model.Arrival;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.testupstream.timetogo.model.ArrivalBuilder.anArrival;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

@RunWith(IntegrationTestRunner.class)
public class ArrivalsIntegrationTest extends IntegrationTestStepWrapper {

    @Test
    public void shouldDisplayArrivals() throws Exception {
        Arrival arrival = anArrival().build();
        given.theFollowingArrivalsAreDue(arrival);
        String arrivalPage = when.arrivalsAreRequested();
        assertThat(arrivalPage, containsString(arrival.getDestination()));
        assertThat(arrivalPage, containsString(arrival.getStopName()));
        assertThat(arrivalPage, containsString(arrival.getRoute()));
    }

}
