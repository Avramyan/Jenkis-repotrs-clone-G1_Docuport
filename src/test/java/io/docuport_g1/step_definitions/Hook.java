package io.docuport_g1.step_definitions;

import io.cucumber.java.*;
import io.docuport_g1.utilities.BrowserUtils;
import io.docuport_g1.utilities.ConfigurationReader;
import io.docuport_g1.utilities.DB_Utility;
import io.docuport_g1.utilities.Driver;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Configuration;

import java.io.File;
import java.util.Collections;


public class Hook {

    private static final Logger LOG = LogManager.getLogger();
    public static WebDriver driver;



    @Before
    public void setUp(Scenario scenario){
        Driver.getDriver();
        BrowserUtils.myScenario = scenario;
        LOG.info(".......................START AUTOMATION......................Group_1");
    }

    @After
    public void tearDown(Scenario scenario){
        // only takes screenshot when a scenario is failed
        if(scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        LOG.info(".......................END AUTOMATION........................Group_1");
       Driver.closeDriver();
    }

   @AfterStep
    public void takeScreenshot(Scenario scenario){
        final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }

    @Before("@docuportDb")
    public void setUpDB(){
        String url = ConfigurationReader.getProperties("docuportDbUrl");
        String username = ConfigurationReader.getProperties("docuportDbUsername");
        String password = ConfigurationReader.getProperties("docuportDbPassword");

        DB_Utility.createConnection(url, username, password);
    }

    @After("@docuportDb")
    public void closeDBConn (){
        DB_Utility.destroy();
    }

    @BeforeAll
    public static void beforeAll() {
        // Clean any existing report files
        File jsonFile = new File("target/cucumber.json");
        if (jsonFile.exists()) {
            jsonFile.delete();
        }
    }

    @AfterAll
    public static void afterAll() {
        // Verify report was created
        File jsonFile = new File("target/cucumber.json");
        if (!jsonFile.exists()) {
            System.err.println("ERROR: cucumber.json was not generated!");
            try {
                // Create empty file as fallback
                Files.write(jsonFile.toPath(), "[]".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (jsonFile.length() == 0) {
            System.err.println("ERROR: cucumber.json is empty!");
            try {
                // Write empty array as fallback
                Files.write(jsonFile.toPath(), "[]".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

