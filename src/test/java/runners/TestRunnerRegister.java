package runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber-reports/register.html",
                "html:target/cucumber-reports/register.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        features = "src/test/resources/features/register",
        glue = {"steps", "hooks"},
        tags= "@register"

)
public class TestRunnerRegister extends AbstractTestNGCucumberTests { }
