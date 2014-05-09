package com.testupstream.timetogo.views.viewmodels;

import com.testupstream.timetogo.time.DateTimeFactory;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EtaTest {

    @Test
    public void getEta_shouldDisplayFriendlyEtaBasedOnCurrentTime() throws Exception {
        DateTimeFactory.freezeTime(DateTimeFactory.now());

        long arrivalTime = DateTimeFactory.now().plusMinutes(7).getMillis();

        Eta eta = new Eta(arrivalTime);

        assertThat(eta.getEta(), is("7 mins"));
    }
}
