package co.com.sofka.stepdefinition.contactus;

import co.com.sofka.model.contactus.ContactUsModel;
import co.com.sofka.page.contactus.ContactUsPage;
import co.com.sofka.runner.contactus.ContactUsTestCucumber;
import co.com.sofka.stepdefinition.setup.WebUI;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.util.Seconds.TEN_SECONDS;

public class ContactUsStepDefinition extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(ContactUsTestCucumber.class);

    private ContactUsModel contactUsModel;
    private ContactUsPage contactUsPage;

    @Dado("que el cliente se encuentra en la pagina web de contactanos")
    public void queElClienteSeEncuentraEnLaPaginaWebDeContactanos() {
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
    @Cuando("el cliente ingresa los campos obligatorios y confirma la accion")
    public void elClienteIngresaLosCamposObligatoriosYConfirmaLaAccion() {
        try{
            contactUsPage = new ContactUsPage(driver, contactUsModel, TEN_SECONDS.getValue());

            contactUsPage.fillContactUsForm();

        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }
    @Cuando("el cliente ingresa los campos obligatorios menos el de mensaje y confirma la accion")
    public void elClienteIngresaLosCamposObligatoriosMenosElDeMensajeYConfirmaLaAccion() {
        try{
            contactUsPage = new ContactUsPage(driver, contactUsModel, TEN_SECONDS.getValue());

            contactUsPage.fillContactUsFormWithoutMessage();

        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }
    @Entonces("el sistema debera mostrar en pantalla un mensaje de agradecimiento con el nombre del cliente")
    public void elSistemaDeberaMostrarEnPantallaUnMensajeDeAgradecimientoConElNombreDelCliente() {
        Assertions.assertEquals(forSubmittedForm(), contactUsPage.isRegistrationDone());
        quiteDriver();
    }
    @Entonces("el sistema debera mostrar en pantalla un mensaje de error diciendo que el campo mensaje es requerido")
    public void elSistemaDeberaMostrarEnPantallaUnMensajeDeErrorDiciendoQueElCampoMensajeEsRequerido() {
        Assertions.assertEquals("Message is required.", contactUsPage.registrationIsNotDoneMessageError());
        quiteDriver();
    }

    private String forSubmittedForm(){
        String submitedFormResult = "Thank you " + contactUsModel.getName();
        return submitedFormResult;
    }
}
