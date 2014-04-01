package com.testupstream.timetogo.proxy;

import com.google.inject.Inject;
import com.sun.jersey.api.client.Client;
import com.testupstream.timetogo.model.Arrival;

import java.util.List;

public class ArrivalsHttpProxy {

    private final Client client;
    private final ArrivalsParser arrivalsParser;

    @Inject
    public ArrivalsHttpProxy(Client client, ArrivalsParser arrivalsParser) {
        this.client = client;
        this.arrivalsParser = arrivalsParser;
    }

    public List<Arrival> getLocalArrivals() {
        String responseBody = client.resource("http://countdown.api.tfl.gov.uk/interfaces/ura/instant_V1?StopCode1=76309,52592,72641&ReturnList=LineName,DestinationText,EstimatedTime,StopPointName").get(String.class);
        return arrivalsParser.parse(responseBody);
    }
}
