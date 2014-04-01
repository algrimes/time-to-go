package com.testupstream.timetogo.model;

import com.testupstream.timetogo.time.DateTimeFactory;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrivalTest {

    @Test
    public void getEta_shouldDisplayFriendlyEtaBasedOnCurrentTime() throws Exception {
        DateTimeFactory.freezeTime(DateTime.now());

        long eta = DateTimeFactory.now().plusMinutes(7).getMillis();

        Arrival arrival = new Arrival(eta, "55", "Pritchard's Road");

        assertThat(arrival.getEta(), is("7 mins"));
    }
}
