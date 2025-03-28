package io.docuport_g1.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"io.docuport_g1.step_definitions", "io.docuport_g1.hooks"}, // Add hooks package
        plugin = {
                "pretty",
                "json:target/cucumber.json",
                "html:target/cucumber-html-report",
                //"timeline:target/cucumber-timeline"
        },
        monochrome = true,
        tags = ""
)
public class CukesRunner {}
