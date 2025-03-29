package io.docuport_g1.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "html:target/html-reports/cucumber-report.html",
                "json:target/cucumber.json",
                "rerun:target/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = "src/test/resources/features",
        glue = {"io.docuport_g1.step_definitions", "io.docuport_g1.hooks"}, // Add hooks package
        dryRun = false,
        tags = "@AA",
        publish = true,
        monochrome = true
)
public class CukesRunner {
    // This is where your Cucumber tests will run

}
