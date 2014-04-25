package com.testupstream.timetogo.integration.steps;

public class IntegrationTestStepWrapper {

    public final IntegrationTestSteps given;
    public final IntegrationTestSteps when;
    public final IntegrationTestSteps then;

    public IntegrationTestStepWrapper() {
        super();
        IntegrationTestSteps steps = new IntegrationTestSteps();
        given = steps;
        when  = steps;
        then  = steps;
    }


}
