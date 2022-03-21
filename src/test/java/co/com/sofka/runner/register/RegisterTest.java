package co.com.sofka.runner.register;

import co.com.sofka.model.register.RegisterModel;
import co.com.sofka.page.register.RegisterPage;
import co.com.sofka.stepdefinition.setup.WebUI;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static co.com.sofka.util.Seconds.TEN_SECONDS;
import static io.cucumber.messages.internal.com.google.common.base.StandardSystemProperty.USER_DIR;

public class RegisterTest extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(RegisterTest.class);

    private Random rand = new Random();
    private RegisterModel registerModel;

    @BeforeEach
    public void setUp(){
        try{
            setUpLog4j2();
            setUpWebDriver();
            generalStUp(2);

            registerModel = new RegisterModel();
            registerModel.setFirstName("Iván");
            registerModel.setLastName("Rojas");
            registerModel.setAddress("Corales");
            registerModel.setCity("Pereira");
            registerModel.setState("Colombia");
            registerModel.setZipCode("660001");
            //registerModel.setPhone("3113305865");
            registerModel.setSsn("123456789");
            registerModel.setUsername("Calyman" + String.valueOf(rand.nextInt(1000)));
            registerModel.setPassword("12345");
            registerModel.setConfirmationPassword(registerModel.getPassword());


        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }

    @Test
    public void contactUsFormMandatoryFields(){
        try{
            RegisterPage registerPage = new RegisterPage(driver, registerModel, TEN_SECONDS.getValue());

            registerPage.fillRegisterFormObligatoryFields(true);

            Assertions.assertEquals(forSubmittedForm(), registerPage.isRegistrationDone());

        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }

    @Test
    public void contactUsFormMandatoryFieldsWithoutMatchingPasswords(){
        try{
            RegisterPage registerPage = new RegisterPage(driver, registerModel, TEN_SECONDS.getValue());

            registerPage.fillRegisterFormObligatoryFields(false);

            Assertions.assertEquals("Passwords did not match.", registerPage.RegistrationIsNotDonePasswordsMissMatch());

        } catch (Exception exception){
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
        submitedFormResult.add("Welcome " + registerModel.getFirstName() + " " + registerModel.getLastName());
        submitedFormResult.add("Welcome " + registerModel.getUsername());
        return submitedFormResult;
    }
}
