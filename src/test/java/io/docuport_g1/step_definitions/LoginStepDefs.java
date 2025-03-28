package io.docuport_g1.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

import static io.docuport_g1.step_definitions.Hook.driver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.docuport_g1.pages.HomePage;
import io.docuport_g1.pages.LoginPage;
import io.docuport_g1.utilities.BrowserUtils;
import io.docuport_g1.utilities.ConfigurationReader;
import io.docuport_g1.utilities.DocuportConstants;
import io.docuport_g1.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;


import static junit.framework.TestCase.assertTrue;


public class LoginStepDefs {
    private static final Logger LOG = LogManager.getLogger();
    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Given("user is on Docuport login page")
    public void user_is_on_docuport_login_page() {
        Driver.getDriver().get(ConfigurationReader.getProperties("docuportUiUrl"));
        BrowserUtils.waitForClickable(loginPage.loginButton, DocuportConstants.EXTRA_LARGE);
        Assert.assertTrue("Login button is NOT displayed", loginPage.loginButton.isDisplayed());
        LOG.info("user is on docuport login page");
    }

    @When("user enters username for client")
    public void user_enters_username_for_client() {
        loginPage.usernameInput.sendKeys(DocuportConstants.USERNAME_CLIENT);
        LOG.info("user enters username");

    }
    @When("user enters password for client")
    public void user_enters_password_for_client() {
        loginPage.passwordInput.sendKeys(DocuportConstants.PASSWORD);
        LOG.info("user enters password");
    }
    @When("user click login button")
    public void user_click_login_button() {
        loginPage.loginButton.click();
        LOG.info("user clicks login button");
    }

    @Then("user should be able to see the home for client")
    public void user_should_be_able_to_see_the_home_for_client() {
        Assert.assertTrue(BrowserUtils.waitForVisibility(loginPage.homeIcon,10).isDisplayed());
        LOG.info("Home page is successfully displayed");

    }
    @Then("user should successfully logout")
    public void user_should_successfully_logout() {

        BrowserUtils.waitForStaleElement(loginPage.bgDropdown);
        loginPage.bgDropdown.click();
        BrowserUtils.justWait(10);
        loginPage.logOutButton.click();
        BrowserUtils.justWait(10);
        LOG.info("Log out page is successfully displayed");
    }

    @When("user enters username for employee")
    public void user_enters_username_for_employee() {
        loginPage.usernameInput.sendKeys(DocuportConstants.USERNAME_EMPLOYEE);
        LOG.info("user enters username");
    }
    @When("user enters password for employee")
    public void user_enters_password_for_employee() {
        loginPage.passwordInput.sendKeys(DocuportConstants.PASSWORD);
        LOG.info("user enters password");
    }

    @Then("user should be able to see the home for employee")
    public void user_should_be_able_to_see_the_home_for_employee() {
        Assert.assertTrue(BrowserUtils.waitForVisibility(loginPage.form1099,10).isDisplayed());
        LOG.info("Home page is successfully displayed");

    }

    @When("user enters username for advisor")
    public void user_enters_username_for_advisor() {
        loginPage.usernameInput.sendKeys(DocuportConstants.USERNAME_ADVISOR);
        LOG.info("user enters username");
    }
    @When("user enters password for advisor")
    public void user_enters_password_for_advisor() {
        loginPage.passwordInput.sendKeys(DocuportConstants.PASSWORD);
        LOG.info("user enters password");

    }

    @Then("user should be able to see the home for advisor")
    public void user_should_be_able_to_see_the_home_for_advisor() {
        Assert.assertTrue(BrowserUtils.waitForVisibility(loginPage.homeIcon,10).isDisplayed());
        LOG.info("Home page is successfully displayed");

    }

    @When("user enters username for supervisor")
    public void user_enters_username_for_supervisor() {
        loginPage.usernameInput.sendKeys(DocuportConstants.USERNAME_SUPERVISOR);
        LOG.info("user enters username");
    }
    @When("user enters password for supervisor")
    public void user_enters_password_for_supervisor() {
        loginPage.passwordInput.sendKeys(DocuportConstants.PASSWORD);
        LOG.info("user enters password");

    }

    @Then("user should be able to see the home for supervisor")
    public void user_should_be_able_to_see_the_home_for_supervisor() {
        Assert.assertTrue(BrowserUtils.waitForVisibility(loginPage.homeIcon,10).isDisplayed());
        LOG.info("Home page is successfully displayed");

    }


    @Given("user the are on the Docuport")
    public void the_user_is_on_the_docuport_page() {
        driver.get("https://www.docuport.com");
        // page it the upload
        Assert.assertTrue(driver.getTitle().contains("Docuport"));
    }

    @When("user typing {string} in {string} on the page {string}")
    public void the_user_inserts_into_the_field_on_the_page(String value, String fieldName, String pageName) {
        WebElement field = driver.findElement(By.name(fieldName)); // Find a field by name
        field.sendKeys(value); // puts info on the line
    }

    @When("user press button the {string} on the page {string}")
    public void the_user_clicks_the_button_on_the_page(String buttonLabel, String pageName) {
        WebElement button = driver.findElement(By.xpath("//button[contains(text(), '" + buttonLabel + "')]"));
        button.click(); // press the button
    }

    @Then("пользователь должен успешно войти в систему")
    public void the_user_should_be_logged_in_successfully() {
        // check loggin user in the system or not
        WebElement homePageElement = driver.findElement(By.id("homePageElementId")); // to change id for actual
        Assert.assertTrue("user entrace in the system", homePageElement.isDisplayed());
    }
}


//package io.docuport_g1.step_definitions;
//
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.junit.Assert;
//
//import static io.docuport_g1.step_definitions.Hook.driver;

//public class LoginStepDefs {
//    @Given("the user is on the Docuport page")
//    public void the_user_is_on_the_docuport_page() {
//        driver.get("https://www.docuport.com");
//        // Write code here that turns the phrase above into concrete actions
//        //throw new io.cucumber.java.PendingException();
//    }
//
//    @When("the user inserts {string} into the {string} field on the {string} page")
//    public void the_user_inserts_into_the_field_on_the_page(String string, String string2, String string3) {
//        WebElement field = driver.findElement(By.name(string2)); // Найдите поле по имени
//        field.sendKeys(string); // Введите текст в поле
//        // Write code here that turns the phrase above into concrete actions
//        //throw new io.cucumber.java.PendingException();
//    }
//
//    @When("the user clicks the {string} button on the {string} page")
//    public void the_user_clicks_the_button_on_the_page(String string, String string2) {
//        WebElement button = driver.findElement(By.xpath("//button[contains(text(), '" + buttonLabel + "')]"));
//        button.click();
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//    @Then("the user should be logged in successfully")
//    public void the_user_should_be_logged_in_successfully() {
//        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
//    }
//
//
//}


//package io.docuport_g1.step_definitions;
//
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.When;
//import io.docuport_g1.pages.HomePage;
//import io.docuport_g1.pages.LoginPage;
//import io.docuport_g1.utilities.*;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import static org.junit.Assert.assertTrue;
//
//public class LoginStepDefs {
//
//    LoginPage loginPage = new LoginPage();
//    HomePage homePage = new HomePage();
//    private static final Logger LOG = LogManager.getLogger();
//
//    @Given("user is on Docuport login page")
//    public void user_is_on_Docuport_login_page() {
//        Driver.getDriver().get(ConfigurationReader.getProperties("docuportBETA"));
//        LOG.info("user is on Docuport login page");
//    }
//
//    @When("user enters username for client")
//    public void user_enters_username_for_client() {
//        BrowserUtils.waitForClickable(LoginPage.LoginButton, DocuportConstants.EXTRA_LARGE);
//        assertTrue("Login button is NOT displayed", LoginPage.LoginButton.isDisplayed());
//        LoginPage.usernameInput.sendKeys(DocuportConstants.USERNAME_CLIENT);
//        LOG.info("user enters username");
//
//    }
//
//
//}
