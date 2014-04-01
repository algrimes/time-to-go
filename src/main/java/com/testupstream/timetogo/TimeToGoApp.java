package com.testupstream.timetogo;

import com.testupstream.timetogo.bundles.TimeToGoGuiceBundle;
import com.testupstream.timetogo.resources.ArrivalsResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.servlets.assets.AssetServlet;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class TimeToGoApp extends Application<TimeToGoConfig> {

    public static void main(String[] args) throws Exception {
        new TimeToGoApp().run(args);
    }

    @Override
    public void initialize(Bootstrap<TimeToGoConfig> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new TimeToGoGuiceBundle());
        bootstrap.addBundle(new AssetsBundle("/assets"));
    }

    @Override
    public void run(TimeToGoConfig configuration, Environment environment) throws Exception {
        environment.jersey().register(ArrivalsResource.class);
    }
}
