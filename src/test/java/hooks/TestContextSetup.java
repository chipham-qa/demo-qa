package hooks;

import driver.DriverFactory;
import pages.PageObjectManager;

public class TestContextSetup {

    public PageObjectManager pageObjectManager;
    public TestContextSetup () {
        pageObjectManager = new PageObjectManager(DriverFactory.getDriver());
    }
}
