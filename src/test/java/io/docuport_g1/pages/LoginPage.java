package io.docuport_g1.pages;

import io.docuport_g1.utilities.BrowserUtils;
import io.docuport_g1.utilities.DocuportConstants;
import io.docuport_g1.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@type='text']")
    public WebElement usernameInput;

    @FindBy(xpath = "//input[@type='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//span[.=' Continue ']")
    public WebElement continueButton;

    @FindBy(xpath = "//div[@role='listbox']//a[1]")
    public WebElement homeIcon;

    @FindBy(xpath = "//a[@href='/1099-form']")
    public WebElement form1099;

    @FindBy(xpath = "//div[@class='d-flex align-center']")
    public WebElement bgDropdown;

    @FindBy(xpath = "//span[contains(text(),'Log out')]")
    public WebElement logOutButton;
    



    public void insertField(String field, String input){
        switch (field.toLowerCase().trim()){
            case "username":
                BrowserUtils.waitForVisibility(usernameInput, 10).sendKeys(input);
                break;
            case "password":
                BrowserUtils.waitForVisibility(passwordInput, 10).sendKeys(input);
                break;
            default: throw new IllegalArgumentException("No such a field: " + field );
        }
    }

    public void clickButton(String button){
        switch (button.toLowerCase().trim()){
            case "login":
                BrowserUtils.waitForClickable(loginButton, 10).click();
                break;
            case "continue":
                try {
                    BrowserUtils.waitForVisibility(continueButton, 10);
                } catch (Exception e) {
                    WebElement element = Driver.getDriver().findElement(By.xpath("//span[.=' Continue ']"));
                    element.click();
                }
                break;

            default: throw new IllegalArgumentException("Not such a button: " + button);
        }
    }

    /**
     *logins to docuport application
     * @param driver, which is initialized in the test base
     * @param role, comes from docuport constants
     * author zck
     */
    public void login(WebDriver driver, String role) throws InterruptedException {
        switch (role.toLowerCase()){
            case "client":
                usernameInput.sendKeys(DocuportConstants.USERNAME_CLIENT);
                passwordInput.sendKeys(DocuportConstants.PASSWORD);
                break;
            case "supervisor":
                usernameInput.sendKeys(DocuportConstants.USERNAME_SUPERVISOR);
                passwordInput.sendKeys(DocuportConstants.PASSWORD);
                break;
            case "advisor":
                usernameInput.sendKeys(DocuportConstants.USERNAME_ADVISOR);
                passwordInput.sendKeys(DocuportConstants.PASSWORD);
                break;
            case "employee":
                usernameInput.sendKeys(DocuportConstants.USERNAME_EMPLOYEE);
                passwordInput.sendKeys(DocuportConstants.PASSWORD);
                break;
            default: throw new InterruptedException("There is not such a role: " + role);
        }

        loginButton.click();

        if(role.toLowerCase().equals("client")){
            Thread.sleep(3000);
            WebElement cont = driver.findElement(By.xpath("//button[@type='submit']"));
            cont.click();
            Thread.sleep(3000);
        }
    }

    public void login2(String username, String password){
        BrowserUtils.waitForClickable(loginButton, 2);
        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        if (BrowserUtils.waitForVisibility(continueButton, 2).isDisplayed()) {
            continueButton.click();
        }
    }


}