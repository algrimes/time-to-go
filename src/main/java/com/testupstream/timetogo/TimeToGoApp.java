package com.testupstream.timetogo;

import com.testupstream.timetogo.bundles.TimeToGoGuiceBundle;
import com.testupstream.timetogo.resources.ArrivalsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TimeToGoApp extends Application<TimeToGoConfig> {

    public static void main(String[] args) throws Exception {
        new TimeToGoApp().run(args);
    }

    @Override
    public void initialize(Bootstrap<TimeToGoConfig> bootstrap) {
        bootstrap.addBundle(new TimeToGoGuiceBundle());
    }

    @Override
    public void run(TimeToGoConfig configuration, Environment environment) throws Exception {
        environment.jersey().register(ArrivalsResource.class);
    }
}
