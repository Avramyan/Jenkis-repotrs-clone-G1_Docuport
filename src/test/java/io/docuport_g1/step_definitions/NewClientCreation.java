package io.docuport_g1.step_definitions;

import io.cucumber.java.en.*;
import io.docuport_g1.pages.ClientsPage;
import io.docuport_g1.pages.LeftNavigatePage;
import io.docuport_g1.utilities.BrowserUtils;
import io.docuport_g1.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

import static org.junit.Assert.*;


public class NewClientCreation {
    private static final Logger logger = LogManager.getLogger(NewClientCreation.class);
    LeftNavigatePage leftNavigatePage = new LeftNavigatePage();
    ClientsPage clientsPage = new ClientsPage();

    @When("user clicks Clients on left navigation Page")
    public void user_clicks_on() {
        leftNavigatePage.clients.click();

    }

    @Then("user should see Clients page")
    public void user_should_see_clients_page() {
        String expected = "Clients";
        String actual = Driver.getDriver().findElement(By.tagName("h1")).getText();
        assertEquals("Expected does NOT match actual", expected, actual);
        logger.info("User is on Client Page");
    }


    @When("user clicks Create new client dropdown and choose {string}")
    public void user_clicks_dropdown_and_choose_type(String clientType) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(clientsPage.createNewClientDropdown)).click();

        if (clientType.equalsIgnoreCase("Business")) {
           BrowserUtils.waitForVisibility(clientsPage.business,10).click();
            logger.info("Clicked Create New Client and Choose Business");

        } else if (clientType.equalsIgnoreCase("Personal")) {
            BrowserUtils.waitForVisibility(clientsPage.personal,10).click();
            logger.info("Clicked Create New Client and Choose Personal");

        } else {
            throw new IllegalArgumentException("Invalid client type: " + clientType);
        }
    }

    @When("create new client window popped up user clicks Create new user checkbox")
    public void create_new_client_window_popped_up_user_clicks_checkbox() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", clientsPage.newUserCheckbox);
        Thread.sleep(300); // tiny pause to let layout settle
        clientsPage.newUserCheckbox.click();
    }

    @Then("user fills out all required fields except first and last name for {string}")
    public void user_fills_out_all_required_fields_except_first_and_last_name(String clientType, Map <String, String> fieldsAndValues) {
        BrowserUtils.waitFor(5);
        clientsPage.newClientInputs(fieldsAndValues, clientType);
        BrowserUtils.waitFor(5);
        logger.info("New client creation field filled");
    }

    @Then("user clicks save")
    public void user_clicks_save() {
        clientsPage.saveButton.click();
        logger.info("Clicked save button");
    }

    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        assertTrue(clientsPage.firstNameRequired.isDisplayed());

        assertTrue(clientsPage.lastNameRequired.isDisplayed());
        logger.info("An error messages are displayed");
    }

    @Then("validate that user was not created")
    public void validate_that_user_was_not_created(String email) {
        clientsPage.cancelButton.click();
        clientsPage.searchButton.click();
        if (clientsPage.isNewClientCreated(email)) {
            logger.info("New client NOT created");

        }


    }
}
