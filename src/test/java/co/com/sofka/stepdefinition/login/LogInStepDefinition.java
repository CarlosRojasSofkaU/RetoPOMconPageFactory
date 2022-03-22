package co.com.sofka.stepdefinition.login;

import co.com.sofka.model.login.LogInModel;
import co.com.sofka.page.login.LogInPage;
import co.com.sofka.runner.login.LogInTestCucumber;
import co.com.sofka.stepdefinition.setup.WebUI;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static co.com.sofka.util.Seconds.TEN_SECONDS;

public class LogInStepDefinition extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(LogInStepDefinition.class);

    private LogInModel logInModel;
    private LogInPage logInPage;

    @Dado("que el cliente se encuentra en el home de la pagina web")
    public void queElClienteSeEncuentraEnElHomeDeLaPaginaWeb() {
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

    @Cuando("el cliente ingresa sus credenciales para loguearse y confirma la accion")
    public void elClienteIngresaSusCredencialesParaLoguearseYConfirmaLaAccion() {
        try {
            logInPage = new LogInPage(driver, logInModel, TEN_SECONDS.getValue());

            logInPage.fillLogInFormObligatoryFields(true);

        } catch (Exception exception) {
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }

    @Cuando("el cliente ingresa sus credenciales pero con contrasena erronea y confirma la accion")
    public void elClienteIngresaSusCredencialesPeroConContrasenaErroneaYConfirmaLaAccion() {
        try {
            logInPage = new LogInPage(driver, logInModel, TEN_SECONDS.getValue());

            logInPage.fillLogInFormObligatoryFields(false);

        } catch (Exception exception) {
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }

    @Entonces("el sistema debera mostrar en pantalla un resumen de la cuenta y varias funcionalidades como la de salir de la cuenta")
    public void elSistemaDeberaMostrarEnPantallaUnResumenDeLaCuentaYVariasFuncionalidadesComoLaDeSalirDeLaCuenta() {
        Assertions.assertEquals(forSubmittedForm(), logInPage.isRegistrationDone());
        quiteDriver();
    }

    @Entonces("el sistema debera mostrar en pantalla un mensaje de error indicando que la contrasena o el usuario no se pudieron validar")
    public void elSistemaDeberaMostrarEnPantallaUnMensajeDeErrorIndicandoQueLaContrasenaOElUsuarioNoSePudieronValidar() {
        Assertions.assertEquals("The username and password could not be verified.", logInPage.RegistrationIsNotDoneIncorrectPassword());
        quiteDriver();
    }

    private List<String> forSubmittedForm(){
        List<String> submitedFormResult = new ArrayList<>();
        submitedFormResult.add("Accounts Overview");
        submitedFormResult.add("Log Out");
        return submitedFormResult;
    }
}
