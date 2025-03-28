package io.docuport_g1.pages;

import io.docuport_g1.utilities.DB_Utility;
import io.docuport_g1.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LeadsPage {
    public LeadsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    private static ResultSet rs;

    @FindBy(xpath = "//tbody/tr")
    public List<WebElement> rows;


    public int getResultCount() {
        return Driver.getDriver().findElements(By.xpath("//tbody/tr")).size();
    }

        public List<Map<String, String>> getAllLeadsInformation(String fullName, String emailAddress, String phoneNumber) {
            List<Map<String, String>> expectedList = new ArrayList<>();
            for (int i = 1; i <= getResultCount(); i++) {
                Map<String, String> expectedMap = new LinkedHashMap<>();
                expectedMap.put(fullName, Driver.getDriver().findElement(By.xpath("(//tbody/tr)[" + i + "]//td[2]")).getText());
                expectedMap.put(emailAddress, Driver.getDriver().findElement(By.xpath("(//tbody/tr)[" + i + "]//td[3]")).getText());
                expectedMap.put(phoneNumber, Driver.getDriver().findElement(By.xpath("(//tbody/tr)[" + i + "]//td[4]")).getText());
                expectedList.add(expectedMap);
            }

            return expectedList;
        }
    }


