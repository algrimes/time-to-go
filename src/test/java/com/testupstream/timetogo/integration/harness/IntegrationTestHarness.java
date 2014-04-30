package com.testupstream.timetogo.integration.harness;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.util.Modules;
import com.testupstream.timetogo.TimeToGoApp;
import com.testupstream.timetogo.bundles.TimeToGoModule;
import com.testupstream.timetogo.proxy.ArrivalsProxy;
import io.dropwizard.testing.junit.ResourceTestRule;

import static org.mockito.Mockito.mock;

public class IntegrationTestHarness {

    private static IntegrationTestHarness harness;

    private final ArrivalsProxy arrivalsProxy = mock(ArrivalsProxy.class);

    private final Injector injector = Guice.createInjector(Stage.PRODUCTION, Modules.override(new TimeToGoModule()).with(new Module() {
        @Override
        public void configure(Binder binder) {
            binder.bind(ArrivalsProxy.class).toInstance(arrivalsProxy);
        }
    }));

    private final ResourceTestRule jersey = setUpRule();

    private IntegrationTestHarness() {}


    public static IntegrationTestHarness getHarness() {
        if(harness == null) {
            harness = new IntegrationTestHarness();
        }
        return harness;
    }

    public ResourceTestRule getJersey() {
        return jersey;
    }

    public Injector getInjector() {
        return injector;
    }

    public Object clone() throws CloneNotSupportedException {
        super.clone();
        throw new CloneNotSupportedException();
    }

    private ResourceTestRule setUpRule() {
        ResourceTestRule.Builder builder = new ResourceTestRule.Builder()
                .addResource(new TestMessageBodyWriter());
        for (Class resource : new TimeToGoApp().getResources()) {
            builder.addResource(injector.getInstance(resource));
        }
        return builder.build();
    }
}
