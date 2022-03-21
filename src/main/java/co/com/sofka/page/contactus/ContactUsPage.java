package co.com.sofka.page.contactus;

import co.com.sofka.model.contactus.ContactUsModel;
import co.com.sofka.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import static io.cucumber.messages.internal.com.google.common.base.StandardSystemProperty.USER_DIR;

public class ContactUsPage extends CommonActionOnPages {
    private static final Logger LOGGER = Logger.getLogger(ContactUsPage.class);
    private ContactUsModel contactUsModel;
    private static final String MODEL_NULL_MESSAGE = "El modelo del formulario es nulo.";

    //For input test cases.
    @CacheLookup
    @FindBy(id = "name")
    private WebElement name;

    @CacheLookup
    @FindBy(id = "email")
    private WebElement email;

    @CacheLookup
    @FindBy(id = "phone")
    private WebElement phone;

    @CacheLookup
    @FindBy(id = "message")
    private WebElement message;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"contactForm\"]/table/tbody/tr[5]/td[2]/input")
    private WebElement submit;

    //For Assertions test case.
    @CacheLookup
    @FindBy(xpath = "/html/body/div[1]/div[3]/div[2]/p[1]")
    private WebElement assertionName;

    @CacheLookup
    @FindBy(id = "message.errors")
    private WebElement assertionMessageError;

    //constructor
    public ContactUsPage(WebDriver driver, ContactUsModel contactUsModel) {
        super(driver);
        pageFactoryInitElement(driver, this);
        this.contactUsModel = contactUsModel;
    }

    public ContactUsPage(WebDriver driver, ContactUsModel contactUsModel, int seconds) {
        super(driver, seconds, false);
        pageFactoryInitElement(driver, this);
        this.contactUsModel = contactUsModel;
    }

    //Funcionalidades del Page.
    public void fillContactUsForm() throws InterruptedException {
        scrollOn(name);
        clearOn(name);
        typeOn(name, contactUsModel.getName());

        scrollOn(email);
        clearOn(email);
        typeOn(email, contactUsModel.getEmail());

        scrollOn(phone);
        clearOn(phone);
        typeOn(phone, contactUsModel.getPhone());

        scrollOn(message);
        clearOn(message);
        typeOn(message, contactUsModel.getMessage());

        doSubmit(submit);

    }

    public void fillContactUsFormWithoutMessage() throws InterruptedException {
        scrollOn(name);
        clearOn(name);
        typeOn(name, contactUsModel.getName());

        scrollOn(email);
        clearOn(email);
        typeOn(email, contactUsModel.getEmail());

        scrollOn(phone);
        clearOn(phone);
        typeOn(phone, contactUsModel.getPhone());

        scrollOn(message);
        clearOn(message);

        doSubmit(submit);

    }

    public String isRegistrationDone(){
        String submitedFormResul = getText(assertionName).trim();
        return submitedFormResul;
    }

    public String registrationIsNotDoneMessageError(){
        String submitedFormResul = getText(assertionMessageError).trim();
        return submitedFormResul;
    }
}
