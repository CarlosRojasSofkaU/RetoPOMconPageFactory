package co.com.sofka.runner.contactus;

import co.com.sofka.model.contactus.ContactUsModel;
import co.com.sofka.page.contactus.ContactUsPage;
import co.com.sofka.stepdefinition.setup.WebUI;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static co.com.sofka.util.Seconds.TEN_SECONDS;
import static io.cucumber.messages.internal.com.google.common.base.StandardSystemProperty.USER_DIR;

public class ContactUsTest extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(ContactUsTest.class);

    private ContactUsModel contactUsModel;

    @BeforeEach
    public void setUp(){
        try{
            setUpLog4j2();
            setUpWebDriver();
            generalStUp(1);

            contactUsModel = new ContactUsModel();
            contactUsModel.setName("Iván");
            contactUsModel.setEmail("a@gmail.com");
            contactUsModel.setPhone("3113305865");
            contactUsModel.setMessage("Holamundo");


        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }

    @Test
    public void contactUsFormMandatoryFields(){
        try{
            ContactUsPage contactUsPage = new ContactUsPage(driver, contactUsModel, TEN_SECONDS.getValue());
            //ContactUsPage contactUsPage = new ContactUsPage(driver, contactUsModel);

            contactUsPage.fillContactUsForm();

            Assertions.assertEquals(forSubmittedForm(), contactUsPage.isRegistrationDone());

        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }

    @Test
    public void contactUsFormWithoutMessage(){
        try{
            ContactUsPage contactUsPage = new ContactUsPage(driver, contactUsModel, TEN_SECONDS.getValue());

            contactUsPage.fillContactUsFormWithoutMessage();

            Assertions.assertEquals("Message is required.", contactUsPage.registrationIsNotDoneMessageError());

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

    private String forSubmittedForm(){
        String submitedFormResult = "Thank you " + contactUsModel.getName();
        return submitedFormResult;
    }
}
