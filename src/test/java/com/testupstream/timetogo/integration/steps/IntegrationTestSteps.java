package com.testupstream.timetogo.integration.steps;

import com.testupstream.timetogo.integration.harness.IntegrationTestRunner;
import com.testupstream.timetogo.model.Arrival;
import com.testupstream.timetogo.proxy.ArrivalsProxy;
import com.testupstream.timetogo.uris.Uris;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;

public class IntegrationTestSteps {

    private String baseUri = "http://localhost:9998";

    public void theFollowingArrivalsAreDue(Arrival arrival) {
        when(IntegrationTestRunner.getInjector().getInstance(ArrivalsProxy.class).getLocalArrivals()).thenReturn(asList(arrival));
    }

    public String arrivalsAreRequested() {
        return IntegrationTestRunner.getResourceTestHarness().client().resource(baseUri + Uris.ARRIVALS_PATH).get(String.class);
    }

}
