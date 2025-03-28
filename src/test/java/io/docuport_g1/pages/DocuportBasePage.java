package io.docuport_g1.pages;

import io.docuport_g1.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocuportBasePage {

    public DocuportBasePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[@class='d-flex align-center justify-end']//button")
    public WebElement bgbutton;

    @FindBy(xpath = "//span[text()='Log out']")
    public WebElement logOut;

    @FindBy(xpath = "//span[text()='Clients']")
    public WebElement clients;

    @FindBy(xpath = "//button[@class='mb-1 mb-sm-0 v-btn v-btn--has-bg theme--light v-size--large success']")
    public WebElement createNewClient;

    @FindBy(xpath = "//span[text()='Personal']")
    public WebElement accountTypePersonal;

    @FindBy(xpath = "//label[.='Create new user']")
    public WebElement createNewUserCheckBox;

    @FindBy(xpath = "//label[text()='First name']//following-sibling::input")
    public WebElement firstName;

    @FindBy(xpath = "//label[text()='Last name']//following-sibling::input")
    public WebElement lastName;

    @FindBy(xpath = "//label[text()='Email address']//following-sibling::input")
    public WebElement emailAddress;

    @FindBy(xpath = "//label[.='Advisor']/following-sibling::input[1]")
    public WebElement advisor;

    @FindBy(xpath = "//div[@class='v-list v-select-list v-sheet theme--light theme--light']//div[2]//preceding-sibling::div")
    public WebElement advisorDropDown;

    @FindBy(xpath = "//label[text()='Phone number']//following-sibling::input")
    public WebElement phoneNumber;

    @FindBy(xpath = "//label[.='Password']//following-sibling::input")
    public WebElement password;

    @FindBy(xpath = "//label[.='Confirm password']//following-sibling::input")
    public WebElement confirmPassword;

    @FindBy(xpath = "//span[.=' Save ']")
    public WebElement save;

    @FindBy (xpath = "//span[contains(text(),'has been created successful')]")
    public WebElement successfulMessage;








































}
