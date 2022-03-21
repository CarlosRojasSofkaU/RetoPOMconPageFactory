package co.com.sofka.runner.register;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        features = {"src/test/resources/features/register/register.feature"},
        glue = "co.com.sofka.stepdefinition.register",
        tags = "@Regresion"
)
public class RegisterTestCucumber {
}
