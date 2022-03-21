package co.com.sofka.page.register;

import co.com.sofka.model.register.RegisterModel;
import co.com.sofka.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static io.cucumber.messages.internal.com.google.common.base.StandardSystemProperty.USER_DIR;

public class RegisterPage extends CommonActionOnPages{
    private static final Logger LOGGER = Logger.getLogger(RegisterPage.class);
    private RegisterModel registerModel;
    private static final String MODEL_NULL_MESSAGE = "El modelo del formulario es nulo.";

    //For input test cases.
    @CacheLookup
    @FindBy(id = "customer.firstName")
    private WebElement firstName;

    @CacheLookup
    @FindBy(id = "customer.lastName")
    private WebElement lastName;

    @CacheLookup
    @FindBy(id = "customer.address.street")
    private WebElement address;

    @CacheLookup
    @FindBy(id = "customer.address.city")
    private WebElement city;

    @CacheLookup
    @FindBy(id = "customer.address.state")
    private WebElement state;

    @CacheLookup
    @FindBy(id = "customer.address.zipCode")
    private WebElement zipCode;

    @CacheLookup
    @FindBy(id = "customer.phoneNumber")
    private WebElement phone;

    @CacheLookup
    @FindBy(id = "customer.ssn")
    private WebElement ssn;

    @CacheLookup
    @FindBy(id = "customer.username")
    private WebElement username;

    @CacheLookup
    @FindBy(id = "customer.password")
    private WebElement password;

    @CacheLookup
    @FindBy(id = "repeatedPassword")
    private WebElement confirmationPassword;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input")
    private WebElement submit;

    //For Assertions test case.
    @CacheLookup
    @FindBy(xpath = "/html/body/div[1]/div[3]/div[2]/h1")
    private WebElement assertionUsername;

    @CacheLookup
    @FindBy(xpath = "/html/body/div[1]/div[3]/div[2]/p")
    private WebElement assertionMessage;

    @CacheLookup
    @FindBy(xpath = "/html/body/div[1]/div[3]/div[1]/p")
    private WebElement assertionCompleteName;

    @CacheLookup
    @FindBy(xpath = "/html/body/div[1]/div[3]/div[1]/h2")
    private WebElement assertionAccountServices;

    @CacheLookup
    @FindBy(id = "repeatedPassword.errors")
    private WebElement assertionPasswordsMissMatch;

    //constructor
    public RegisterPage(WebDriver driver, RegisterModel registerModel) {
        super(driver);
        pageFactoryInitElement(driver, this);
        this.registerModel = registerModel;
    }

    public RegisterPage(WebDriver driver, RegisterModel registerModel, int seconds) {
        super(driver, seconds, false);
        pageFactoryInitElement(driver, this);
        this.registerModel = registerModel;
    }

    //Funcionalidades del Page.
    public void fillRegisterFormObligatoryFields(boolean passwordMatchUp) throws InterruptedException {
        scrollOn(firstName);
        clearOn(firstName);
        typeOn(firstName, registerModel.getFirstName());

        scrollOn(lastName);
        clearOn(lastName);
        typeOn(lastName, registerModel.getLastName());

        scrollOn(address);
        clearOn(address);
        typeOn(address, registerModel.getAddress());

        scrollOn(city);
        clearOn(city);
        typeOn(city, registerModel.getCity());

        scrollOn(state);
        clearOn(state);
        typeOn(state, registerModel.getState());

        scrollOn(zipCode);
        clearOn(zipCode);
        typeOn(zipCode, registerModel.getZipCode());

        scrollOn(phone);
        clearOn(phone);
        //typeOn(phone, registerModel.getPhone());

        scrollOn(ssn);
        clearOn(ssn);
        typeOn(ssn, registerModel.getSsn());

        scrollOn(username);
        clearOn(username);
        typeOn(username, registerModel.getUsername());

        scrollOn(password);
        clearOn(password);
        typeOn(password, registerModel.getPassword());

        scrollOn(confirmationPassword);
        clearOn(confirmationPassword);
        if (passwordMatchUp){
            typeOn(confirmationPassword, registerModel.getConfirmationPassword());
        }
        else {
            typeOn(confirmationPassword, registerModel.getConfirmationPassword() + "Hola");
        }

        doSubmit(submit);

    }

    public List<String> isRegistrationDone(){
        List<String> submitedFormResul = new ArrayList<>();
        submitedFormResul.add(getText(assertionCompleteName).trim());
        submitedFormResul.add(getText(assertionUsername).trim());
        return submitedFormResul;
    }

    public String RegistrationIsNotDonePasswordsMissMatch(){
        String submitedFormResul = getText(assertionPasswordsMissMatch).trim();
        return submitedFormResul;
    }
}
