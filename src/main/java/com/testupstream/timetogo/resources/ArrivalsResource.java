package com.testupstream.timetogo.resources;

import com.google.inject.Inject;
import com.testupstream.timetogo.proxy.ArrivalsHttpProxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/arrivals")
public class ArrivalsResource {

    private final ArrivalsHttpProxy arrivalsProxy;

    @Inject
    public ArrivalsResource(ArrivalsHttpProxy arrivalsProxy){
        this.arrivalsProxy = arrivalsProxy;
    }

    @GET
    @Produces("application/json")
    public Response get() {
            return Response.ok(arrivalsProxy.getLocalArrivals()).build();
    }

}
