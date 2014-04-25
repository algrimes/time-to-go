package com.testupstream.timetogo.bundles;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.sun.jersey.api.client.Client;
import com.testupstream.timetogo.proxy.ArrivalsHttpProxy;
import com.testupstream.timetogo.proxy.ArrivalsProxy;

public class TimeToGoModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(Client.class).in(Scopes.SINGLETON);
        binder.bind(ArrivalsProxy.class).to(ArrivalsHttpProxy.class);
    }

}
