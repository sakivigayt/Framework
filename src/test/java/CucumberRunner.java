import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, features = {"src/test/java/com/restassured/features/"},
        glue = {"com.restassured.stepdefs"},
        format = {"pretty", "html:target/site/cucumber-pretty",
                "json:target/reports/cucumber-results.json"},
        tags = {"~@ignore"})
public class CucumberRunner {

}