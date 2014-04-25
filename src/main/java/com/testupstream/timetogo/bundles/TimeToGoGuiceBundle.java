package com.testupstream.timetogo.bundles;

import com.google.common.base.Function;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.util.Modules;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import io.dropwizard.Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import javax.annotation.Nullable;

public class TimeToGoGuiceBundle implements Bundle {

    private GuiceContainer container;

    @Override
    public void initialize(Bootstrap<?> bootstrap) {
        container = new GuiceContainer();

        Guice.createInjector(Stage.PRODUCTION, Modules.override(new JerseyServletModule()).with(
                new Module() {
                    @Override
                    public void configure(Binder binder) {
                        binder.bind(GuiceContainer.class).toInstance(container);
                    }
                }
        ), new TimeToGoModule());
    }

    @Override
    public void run(Environment environment) {
        container.setResourceConfig(environment.jersey().getResourceConfig());
        environment.jersey().replace(new Function<ResourceConfig, ServletContainer>() {
            @Nullable
            @Override
            public ServletContainer apply(ResourceConfig resourceConfig) {
                return container;
            }
        });
        environment.servlets().addFilter("GuiceFilter", new GuiceFilter())
                .addMappingForUrlPatterns(null, false, environment.getApplicationContext().getContextPath() + "*");
    }
}
