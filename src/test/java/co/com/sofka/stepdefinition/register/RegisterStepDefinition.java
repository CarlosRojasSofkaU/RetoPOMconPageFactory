package co.com.sofka.stepdefinition.register;

import co.com.sofka.model.register.RegisterModel;
import co.com.sofka.page.register.RegisterPage;
import co.com.sofka.runner.register.RegisterTestCucumber;
import co.com.sofka.stepdefinition.setup.WebUI;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static co.com.sofka.util.Seconds.TEN_SECONDS;

public class RegisterStepDefinition extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(RegisterTestCucumber.class);

    private Random rand = new Random();
    private RegisterModel registerModel;
    private RegisterPage registerPage;

    @Dado("que el cliente se encuentra en la pagina web de registrarse")
    public void queElClienteSeEncuentraEnLaPaginaWebDeRegistrarse() {
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
    @Cuando("el cliente ingresa los campos obligatorios y confirma la accion")
    public void elClienteIngresaLosCamposObligatoriosYConfirmaLaAccion() {
        try{
            registerPage = new RegisterPage(driver, registerModel, TEN_SECONDS.getValue());

            registerPage.fillRegisterFormObligatoryFields(true);

        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }
    @Cuando("el cliente ingresa los campos obligatorios con una contrasena y confirmacion diferentes y confirma la accion")
    public void elClienteIngresaLosCamposObligatoriosConUnaContrasenaYConfirmacionDiferentesYConfirmaLaAccion() {
        try{
            registerPage = new RegisterPage(driver, registerModel, TEN_SECONDS.getValue());

            registerPage.fillRegisterFormObligatoryFields(false);

        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }
    @Entonces("el sistema debera mostrar en pantalla un mensaje de bienvenida con el nombre de usuario del cliente y otro con su nombre completo.")
    public void elSistemaDeberaMostrarEnPantallaUnMensajeDeBienvenidaConElNombreDeUsuarioDelClienteYOtroConSuNombreCompleto() {
        Assertions.assertEquals(forSubmittedForm(), registerPage.isRegistrationDone());
        quiteDriver();
    }
    @Entonces("el sistema debera mostrar en pantalla un mensaje de error indicando que la contrasena y la confirmacion de la contrasena son diferentes.")
    public void elSistemaDeberaMostrarEnPantallaUnMensajeDeErrorIndicandoQueLaContrasenaYLaConfirmacionDeLaContrasenaSonDiferentes() {
        Assertions.assertEquals("Passwords did not match.", registerPage.RegistrationIsNotDonePasswordsMissMatch());
    }

    private List<String> forSubmittedForm(){
        List<String> submitedFormResult = new ArrayList<>();
        submitedFormResult.add("Welcome " + registerModel.getFirstName() + " " + registerModel.getLastName());
        submitedFormResult.add("Welcome " + registerModel.getUsername());
        return submitedFormResult;
    }
}
