package com.testupstream.timetogo.integration.harness;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.util.Modules;
import com.testupstream.timetogo.bundles.TimeToGoModule;
import com.testupstream.timetogo.proxy.ArrivalsProxy;
import com.testupstream.timetogo.resources.ArrivalsResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Ignore;
import org.junit.rules.RunRules;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;

public class IntegrationTestRunner extends BlockJUnit4ClassRunner {

    private static ArrivalsProxy arrivalsProxy = mock(ArrivalsProxy.class);

    public static Injector injector = Guice.createInjector(Stage.PRODUCTION, Modules.override(new TimeToGoModule()).with(new Module() {
        @Override
        public void configure(Binder binder) {
            binder.bind(ArrivalsProxy.class).toInstance(arrivalsProxy);
        }
    }));

    public static final ResourceTestRule resourceTestHarness =
            new ResourceTestRule.Builder()
                    .addResource(injector.getInstance(ArrivalsResource.class))
                    .addResource(new TestMessageBodyWriter())
                    .build();

    public IntegrationTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        Description description = describeChild(method);
        if (method.getAnnotation(Ignore.class) != null) {
            notifier.fireTestIgnored(description);
        } else {
            RunRules rules = new RunRules(methodBlock(method), asList(new TestRule[]{
                    resourceTestHarness}), description);
            runLeaf(rules, description, notifier);
        }
    }
}
