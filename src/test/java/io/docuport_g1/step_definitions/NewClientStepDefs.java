package io.docuport_g1.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.docuport_g1.pages.POM;
import io.docuport_g1.utilities.BrowserUtils;
import io.docuport_g1.utilities.ConfigurationReader;
import io.docuport_g1.utilities.DB_Utility;
import io.docuport_g1.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class NewClientStepDefs {

    POM pages = new POM();
    String clientEmail;

    @When("user create a new personal client")
    public void user_create_a_new_personal_client(Map<String, String> clientInfo) {
        clientEmail = clientInfo.get("Email address");

        pages.getDocuportBasePage().clients.click();
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().createNewClient.click();
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().accountTypePersonal.click();
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().firstName.sendKeys(clientInfo.get("First name"), Keys.ENTER);
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().lastName.sendKeys(clientInfo.get("Last name"), Keys.ENTER);
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().createNewUserCheckBox.click();
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().emailAddress.sendKeys(clientInfo.get("Email address"), Keys.ENTER);
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().advisor.click();
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().advisorDropDown.click();
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().phoneNumber.sendKeys(clientInfo.get("Phone number"), Keys.ENTER);
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().password.sendKeys(clientInfo.get("Password"), Keys.ENTER);
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().confirmPassword.sendKeys(clientInfo.get("Password"), Keys.ENTER);
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().save.click();

    }

    @When("validate the new client created")
    public void validate_the_new_client_created() {

        BrowserUtils.waitForStaleElement(pages.getDocuportBasePage().successfulMessage);
        BrowserUtils.waitFor(5);
        pages.getDocuportBasePage().successfulMessage.click();
        Assert.assertTrue("User has not created",pages.getDocuportBasePage().successfulMessage.isEnabled());

    }
    @When("log out as an advisor")
    public void log_out_as_an_advisor() {
        pages.getDocuportBasePage().bgbutton.click();
        pages.getDocuportBasePage().logOut.click();

    }
    @When("log as a new client")
    public void log_as_a_new_client(Map<String,String> credentials) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(15));
//        wait.until(ExpectedConditions.elementToBeClickable( pages.getLoginPage().loginButton));
        Thread.sleep(5000);

        pages.getLoginPage().usernameInput.sendKeys(credentials.get("email"));
        pages.getLoginPage().passwordInput.sendKeys(credentials.get("password"),Keys.ENTER);
        //pages.getLoginPage().loginButton.click();

    }
    @When("user should be able to see the home for new client")
    public void user_should_be_able_to_see_the_home_for_new_client() throws InterruptedException {
        Thread.sleep(5000);

        System.out.println("pages.getLoginPage().homeButton.isDisplayed() = " + pages.getLoginPage().homeIcon.isDisplayed());


    }
    @When("log out as a new client")
    public void log_out_as_a_new_client() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(pages.getDocuportBasePage().bgbutton));
        pages.getDocuportBasePage().bgbutton.click();
        wait.until(ExpectedConditions.elementToBeClickable(pages.getDocuportBasePage().logOut)).click();

    }

    @Then("create Db connection")
    public void create_db_connection() {
        String url = ConfigurationReader.getProperties("docuportDbUrl");
        String username = ConfigurationReader.getProperties("docuportDbUsername");
        String password = ConfigurationReader.getProperties("docuportDbPassword");

        DB_Utility.createConnection(url, username, password);

    }
    @Then("delete newly created {string}")
    public void delete_newly_created(String client) {
        client = this.clientEmail;
        String sql = "Delete from identity.users where email_address='"+ client +"'";
        DB_Utility.runQuery(sql);

    }



}
