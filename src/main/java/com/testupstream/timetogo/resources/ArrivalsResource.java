package com.testupstream.timetogo.resources;

import com.google.inject.Inject;
import com.testupstream.timetogo.proxy.ArrivalsHttpProxy;
import com.testupstream.timetogo.proxy.ArrivalsProxy;
import com.testupstream.timetogo.uris.Uris;
import com.testupstream.timetogo.views.ArrivalsView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.testupstream.timetogo.uris.Uris.ARRIVALS_PATH;

@Path(ARRIVALS_PATH)
public class ArrivalsResource {

    private final ArrivalsProxy arrivalsProxy;

    @Inject
    public ArrivalsResource(ArrivalsProxy arrivalsProxy) {
        this.arrivalsProxy = arrivalsProxy;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response get() {
        return Response.ok(new ArrivalsView(arrivalsProxy.getLocalArrivals())).build();
    }

}
