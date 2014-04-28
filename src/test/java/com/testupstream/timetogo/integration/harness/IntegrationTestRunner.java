package com.testupstream.timetogo.integration.harness;

import org.junit.Ignore;
import org.junit.rules.RunRules;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.util.Arrays;

import static com.testupstream.timetogo.integration.harness.IntegrationTestHarness.getHarness;

public class IntegrationTestRunner extends BlockJUnit4ClassRunner {

    public IntegrationTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
        Description description = describeChild(method);
        if (method.getAnnotation(Ignore.class) != null) {
            notifier.fireTestIgnored(description);
        } else {
            RunRules rules = new RunRules(
                    methodBlock(method),
                    Arrays.<TestRule>asList(getHarness().getJersey()),
                    description);
            runLeaf(rules, description, notifier);
        }
    }

}
