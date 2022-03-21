package co.com.sofka.runner.contactus;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        features = {"src/test/resources/features/contactus/contactUs.feature"},
        glue = "co.com.sofka.stepdefinition.contactus",
        tags = "@Regresion"
)
public class ContactUsTestCucumber {
}
