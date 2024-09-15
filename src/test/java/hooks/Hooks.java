package hooks;

import driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import utils.LogUtils;

public class Hooks {

    TestContextSetup testContext;

    public Hooks(TestContextSetup context) {
        testContext = context;
    }

    @BeforeAll
    public static void beforeAllScenario() {
        LogUtils.info("Before all");
        // To do something when start the test suite if needed
    }

    @AfterAll
    public static void afterAllScenario() {
        LogUtils.info("After all");
    }

    @Before
    public void beforeScenario() {
        LogUtils.info("Before scenario");

    }

    @After
    public void afterScenario() {
        LogUtils.info("After scenario");
        DriverFactory.closeDriver();
    }
}
