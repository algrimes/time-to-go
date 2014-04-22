package com.testupstream.timetogo.model;

import com.testupstream.timetogo.time.DateTimeFactory;
import org.joda.time.DateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class ArrivalTest {

    @Test
    public void getEta_shouldDisplayFriendlyEtaBasedOnCurrentTime() throws Exception {
        DateTimeFactory.freezeTime(DateTime.now());

        long eta = DateTimeFactory.now().plusMinutes(7).getMillis();

        Arrival arrival = new Arrival(eta, "55", "Pritchard's Road", "Oxford Circus");

        assertThat(arrival.getEta(), is("7 mins"));
    }
}
