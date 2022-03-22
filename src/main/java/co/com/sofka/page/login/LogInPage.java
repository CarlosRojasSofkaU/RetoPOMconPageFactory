package co.com.sofka.page.login;

import co.com.sofka.model.login.LogInModel;
import co.com.sofka.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static io.cucumber.messages.internal.com.google.common.base.StandardSystemProperty.USER_DIR;

public class LogInPage extends CommonActionOnPages {
    private static final Logger LOGGER = Logger.getLogger(LogInPage.class);
    private LogInModel logInModel;
    private static final String MODEL_NULL_MESSAGE = "El modelo del formulario es nulo.";

    //For input test cases.
    @CacheLookup
    @FindBy(xpath = "//*[@id=\"loginPanel\"]/form/div[1]/input")
    private WebElement userName;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"loginPanel\"]/form/div[2]/input")
    private WebElement password;

    @CacheLookup
    @FindBy(xpath = "//*[@id=\"loginPanel\"]/form/div[3]/input")
    private WebElement submit;

    //For Assertions test case.
    @CacheLookup
    @FindBy(xpath = "/html/body/div[1]/div[3]/div[2]/div/div/h1")
    private WebElement assertionAccountsOverview;

    @CacheLookup
    @FindBy(xpath = "/html/body/div[1]/div[3]/div[1]/ul/li[8]/a")
    private WebElement assertionLogOut;

    @CacheLookup
    @FindBy(xpath = "/html/body/div[1]/div[3]/div[2]/p")
    private WebElement assertionLogInError;

    //constructor
    public LogInPage(WebDriver driver, LogInModel logInModel) {
        super(driver);
        pageFactoryInitElement(driver, this);
        this.logInModel = logInModel;
    }

    public LogInPage(WebDriver driver, LogInModel logInModel, int seconds) {
        super(driver, seconds, false);
        pageFactoryInitElement(driver, this);
        this.logInModel = logInModel;
    }

    //Funcionalidades del Page.
    public void fillLogInFormObligatoryFields(boolean correctPassword) throws InterruptedException {
        scrollOn(userName);
        clearOn(userName);
        typeOn(userName, logInModel.getUserName());

        scrollOn(password);
        clearOn(password);
        if (correctPassword){
            typeOn(password, logInModel.getPassword());
        }
        else{
            typeOn(password, logInModel.getPassword() + "Hola");
        }

        doSubmit(submit);

    }

    public List<String> isRegistrationDone(){
        List<String> submitedFormResul = new ArrayList<>();
        submitedFormResul.add(getText(assertionAccountsOverview).trim());
        submitedFormResul.add(getText(assertionLogOut).trim());
        return submitedFormResul;
    }

    public String RegistrationIsNotDoneIncorrectPassword(){
        String submitedFormResul = getText(assertionLogInError).trim();
        return submitedFormResul;
    }
}
