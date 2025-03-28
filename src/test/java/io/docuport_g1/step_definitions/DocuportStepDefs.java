package io.docuport_g1.step_definitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.docuport_g1.pages.BasePage;
import io.docuport_g1.pages.LoginPage;
import io.docuport_g1.pages.UserPage;
import io.docuport_g1.utilities.BrowserUtils;
import io.docuport_g1.utilities.ConfigurationReader;
import io.docuport_g1.utilities.DB_Utility;
import io.docuport_g1.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class DocuportStepDefs extends BasePage {
    LoginPage loginPage=new LoginPage();
    UserPage userPage = new UserPage();
    String uiUserCount;
    @Given("the {string} on the home page")
    public void the_on_the_home_page(String usertype) throws InterruptedException {
        Driver.getDriver().get(ConfigurationReader.getProperties("docuportUiUrl"));
        loginPage.login(Driver.getDriver(),usertype);

    }
    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        userPage.clickModule(moduleName);
    }
    @When("the user gets total user count")
    public void the_user_gets_total_user_count() {
        BrowserUtils.waitForStaleElement(userPage.searchButton);
        userPage.searchButton.click();
        userPage.clickRadioButton("All");
        BrowserUtils.waitFor(5);
        userPage.searchFilter.click();
        BrowserUtils.waitFor(3);

        uiUserCount =  userPage.pagination.getText().split(" ")[2]; // 1-10 of 1353 -> ["1-10", "of", ""1353]


    }
    @Then("verify user count information match in DB")
    public void verify_user_count_information_match_in_db() {

        String url = ConfigurationReader.getProperties("docuportDbUrl");
        String username = ConfigurationReader.getProperties("docuportDbUsername");
        String password = ConfigurationReader.getProperties("docuportDbPassword");

        DB_Utility.createConnection(url, username, password);


        DB_Utility.runQuery("SELECT COUNT(*) FROM identity.users");
        String dbUserCount = DB_Utility.getCellValue(1, 1);
        //System.out.println("dbUserCount = " + dbUserCount);

        Assert.assertEquals(dbUserCount, uiUserCount);


    }


}