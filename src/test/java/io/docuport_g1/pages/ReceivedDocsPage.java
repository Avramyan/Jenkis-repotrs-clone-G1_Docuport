package io.docuport_g1.pages;

import io.docuport_g1.utilities.BrowserUtils;
import io.docuport_g1.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReceivedDocsPage {


    public ReceivedDocsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//span[contains(text(),'Search')]")
    public WebElement searchButton;

    @FindBy(xpath = "//label[contains(text(),'Document name')]/following-sibling::input")
    public WebElement documentName;

    public void clickButton(String button){
        switch (button.toLowerCase().trim()){
            case "search":
                BrowserUtils.waitForClickable(searchButton, 10).click();
                break;
            default: throw new IllegalArgumentException("Not such a button: " + button);
        }
    }

    public void insertField(String field, String input){
        switch (field.toLowerCase().trim()){
            case "document name":
                BrowserUtils.waitForVisibility(documentName, 10).sendKeys(input);
                break;
            default: throw new IllegalArgumentException("No such a field: " + field );
        }
    }
}