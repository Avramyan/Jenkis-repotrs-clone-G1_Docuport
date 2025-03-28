package io.docuport_g1.step_definitions;

import io.cucumber.java.en.Then;
import io.docuport_g1.pages.CommonPage;
import io.docuport_g1.pages.LeftNavigatePage;

import io.docuport_g1.pages.ReceivedDocsPage;
import io.docuport_g1.runner.CukesRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AdvisorStepDefs extends CommonPage {

    @Then("user clicks {string} button on {string} page")
    public void user_clicks_button_on_page(String button, String page) {
        advisorClicksButton(button, page);



    }
}

