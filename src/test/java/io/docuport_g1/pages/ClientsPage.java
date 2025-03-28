package io.docuport_g1.pages;

import io.docuport_g1.utilities.BrowserUtils;
import io.docuport_g1.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class ClientsPage {
    public ClientsPage() {
        PageFactory.initElements(Driver.getDriver(), this);

    }

    @FindBy(xpath = "//span[contains(text(),'Create')]/ancestor::button[@type='button']")
    public WebElement createNewClientDropdown;
    @FindBy(xpath = "//span[contains(text(),'Personal') and preceding-sibling::i]")
    public WebElement personal;
    @FindBy(xpath = "//span[.='Business']")
    public WebElement business;
    @FindBy(xpath = "//label[contains(text(),'new user')]")
    public WebElement newUserCheckbox;
    @FindBy(xpath = "//span[contains(text(),'Save')]/..")
    public WebElement saveButton;

    //ALL CLIENTS INPUTS
    @FindBy(xpath = "//label[.='Company name']/following-sibling::input")
    public WebElement companyName;
    @FindBy(xpath = "//label[.='First name']/following-sibling::input")
    public WebElement firstName;
    @FindBy(xpath = "//label[.='Last name']/following-sibling::input")
    public WebElement lastName;
    @FindBy(xpath = "//label[.='Email address']/following-sibling::input")
    public WebElement email;
    @FindBy(xpath = "//label[.='Advisor']/following-sibling::input[@type='text']")
    public WebElement advisorDropdown;
    @FindBy(xpath = "//div[contains(text(),'advisor advisor')]")
    public WebElement advisor;
    @FindBy(xpath = "//label[.='Phone number']/following-sibling::input")
    public WebElement phoneNumber;
    @FindBy(xpath = "//label[.='Password']/following-sibling::input")
    public WebElement password;
    @FindBy(xpath = "//label[.='Confirm password']/following-sibling::input")
    public WebElement confirmPassword;
    //ERROR MASSAGES
    @FindBy(xpath = "//label[.='Last name']/following::div[contains(text(),'This field is required')]")
    public WebElement lastNameRequired;
    @FindBy(xpath = "//label[.='Last name']/preceding::div[contains(text(),'This field is required')]")
    public WebElement firstNameRequired;
    //new Client search part
    @FindBy(xpath = "//span[contains(text(),'Cancel')]/..")
    public WebElement cancelButton;
    @FindBy(xpath = "//span[contains(text(),'Search')]/../..")
    public WebElement searchButton;
    @FindBy(xpath = "//label[contains(text(),'Email')]/following-sibling::input")
    public WebElement emailInputInSearchClient;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submitSearchButton;
    @FindBy(xpath = "//p[contains(text(),'no results')]")
    public WebElement searchResults;


    //Creating New client input information method
    public void newClientInputs(Map<String, String> inputs, String clientType) {
//Map of all fields and corresponding inputs for them
        Map<String, WebElement> fieldsAndInputElems = new HashMap<>();
        fieldsAndInputElems.put("Company name", companyName);
        fieldsAndInputElems.put("First name", firstName);
        fieldsAndInputElems.put("Last name", lastName);
        fieldsAndInputElems.put("Email", email);
        fieldsAndInputElems.put("Advisor", advisorDropdown);
        fieldsAndInputElems.put("Phone number", phoneNumber);
        fieldsAndInputElems.put("Password", password);
        fieldsAndInputElems.put("Confirm Password", confirmPassword);

        //loop through needed field to input needed values
        for (Map.Entry<String, String> entry : inputs.entrySet()) {
            String fieldName = entry.getKey();
            String fieldValue = entry.getValue();

            if (fieldsAndInputElems.containsKey(fieldName)) {
                // EXCLUDE COMPANY NAME FIELD FOR PERSONAL CLIENT
                if (clientType.equalsIgnoreCase("personal") &&
                        fieldName.equalsIgnoreCase("company name")
                ) {
                    continue;
                }
                WebElement field = fieldsAndInputElems.get(fieldName);

                if (fieldName.equals("Advisor")) {
                    field.click();
                    BrowserUtils.waitFor(10);
                    WebElement advisorOption = Driver.getDriver().findElement(By.xpath("//div[contains(text(),'" + fieldValue + "')]"));
                    BrowserUtils.waitForVisibility(advisorOption,10).click();
                } else {
                    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

                    wait.until(ExpectedConditions.visibilityOf(field)).clear();

                    //field.clear();
                    field.sendKeys(fieldValue);
                }
            } else {
                throw new IllegalArgumentException("Invalid field name: " + fieldName);

            }
        }
    }

    //SEARCH METHOD FOR NEW CLIENT
    public boolean isNewClientCreated(String email) {
        emailInputInSearchClient.sendKeys(email);
        submitSearchButton.click();
        return BrowserUtils.waitForVisibility(searchResults,10).isDisplayed();
    }
}