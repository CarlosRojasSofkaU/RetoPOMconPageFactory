package co.com.sofka.stepdefinition.setup;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static co.com.sofka.util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static com.google.common.base.StandardSystemProperty.USER_DIR;

public class WebUI {

    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String WEBDRIVER_CHROME_DRIVER_PATH = "src/test/resources/driver/windows/chrome/chromedriver.exe";

    private static final String PARABANK_CONTACT_US_URL = "https://parabank.parasoft.com/parabank/contact.htm";
    private static final String PARABANK_REGISTER_URL = "https://parabank.parasoft.com/parabank/register.htm";
    private static final String PARABANK_HOME_URL = "https://parabank.parasoft.com/parabank/index.htm";

    protected WebDriver driver;

    protected void setUpWebDriver(){
        System.setProperty(WEBDRIVER_CHROME_DRIVER, WEBDRIVER_CHROME_DRIVER_PATH);
    }

    protected void generalStUp(int wantedURLCase){
        driver = new ChromeDriver();
        switch (wantedURLCase){
            case 1:
                driver.get(PARABANK_CONTACT_US_URL);
                break;
            case 2:
                driver.get(PARABANK_REGISTER_URL);
                break;
            default:
                driver.get(PARABANK_HOME_URL);
                break;
        }
        driver.manage().window().maximize();
    }

    protected void setUpLog4j2(){
        PropertyConfigurator.configure(USER_DIR.value() + LOG4J_PROPERTIES_FILE_PATH.getValue());
    }

    protected void quiteDriver(){
        driver.quit();
    }
}
