import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith( Cucumber.class )
@CucumberOptions(features = {"src/test/resources/features"},
        plugin = {"pretty","json:target/cucumber.json"},
        glue = "com.nhscc.stepdefinition",
        tags="@Test")
public class TestRunner {
}