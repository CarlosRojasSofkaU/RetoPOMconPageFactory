package co.com.sofka.runner.login;

import co.com.sofka.model.login.LogInModel;
import co.com.sofka.page.login.LogInPage;
import co.com.sofka.stepdefinition.setup.WebUI;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.util.Seconds.TEN_SECONDS;

public class LogInTest extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(LogInTest.class);

    private LogInModel logInModel;

    @BeforeEach
    public void setUp(){
        try{
            setUpLog4j2();
            setUpWebDriver();
            generalStUp(10);

            logInModel = new LogInModel();
            logInModel.setUserName("Calymano5209"); //tener en cuenta que este usuario debe existir en la plataforma
            logInModel.setPassword("12345");


        } catch(Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }

    @Test
    public void logInFormMandatoryFields(){
        try {
            LogInPage logInPage = new LogInPage(driver, logInModel, TEN_SECONDS.getValue());

            logInPage.fillLogInFormObligatoryFields(true);

            Assertions.assertEquals(forSubmittedForm(), logInPage.isRegistrationDone());

        } catch (Exception exception) {
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }

    @Test
    public void logInFormMandatoryFieldsWithIncorrectPassword(){
        try {
            LogInPage logInPage = new LogInPage(driver, logInModel, TEN_SECONDS.getValue());

            logInPage.fillLogInFormObligatoryFields(false);

            Assertions.assertEquals("The username and password could not be verified.", logInPage.RegistrationIsNotDoneIncorrectPassword());

        } catch (Exception exception) {
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }

    @AfterEach
    public void tearDown(){
        quiteDriver();
    }

    private List<String> forSubmittedForm(){
        List<String> submitedFormResult = new ArrayList<>();
        submitedFormResult.add("Accounts Overview");
        submitedFormResult.add("Log Out");
        return submitedFormResult;
    }
}
