package com.testupstream.timetogo.views.viewmodels;

import com.testupstream.timetogo.model.Arrival;
import com.testupstream.timetogo.time.DateTimeFactory;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;

import java.text.MessageFormat;

public class Eta {

    private long eta;

    public Eta(long eta) {
        this.eta = eta;
    }

    public String getEta() {
        DateTime arrivalTime = new DateTime(this.eta, DateTimeZone.getDefault());
        long duration = new Duration(DateTimeFactory.now().getMillis(), arrivalTime.getMillis()).getStandardMinutes();
        return MessageFormat.format("{0} mins", duration);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Eta eta1 = (Eta) o;

        if (eta != eta1.eta) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (eta ^ (eta >>> 32));
    }
}
