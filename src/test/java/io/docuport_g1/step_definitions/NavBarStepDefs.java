package io.docuport_g1.step_definitions;

import io.cucumber.java.en.Then;
import io.docuport_g1.utilities.BrowserUtils;
import io.docuport_g1.utilities.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class NavBarStepDefs {
    private Logger LOG = LogManager.getLogger();

    @Then("validate left navigation items")
    public void validate_left_navigation_items(List<String> items) {
        BrowserUtils.waitFor(5);
        items.forEach(item -> {
            try {
                // Try to find the element containing the text provided in item
                BrowserUtils.waitFor(5);
                WebElement element = Driver.getDriver().findElement(By.xpath("//span[contains(text(),'" + item + "')]"));
                BrowserUtils.waitFor(5);
                Assert.assertNotNull(element);
                LOG.info("{} exists", item);
            } catch (Exception e) {
                throw new AssertionError("Unable to find element " + item + " in navigation items");
            }
        });
    }


}