package io.docuport_g1.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonPage {
    ReceivedDocsPage receivedDocsPage = new ReceivedDocsPage();
    LeftNavigatePage leftNavigatePage = new LeftNavigatePage();
    private static final Logger LOG = LogManager.getLogger();

    public void advisorClicksButton(String button, String page) {


        switch (page.toLowerCase().trim()) {
            case "left navigate":
                leftNavigatePage.clickButton(button);
                LOG.info(button + " - was successfully clicked");
                break;
            case "received doc":
                receivedDocsPage.clickButton(button);
                LOG.info(button + " - was successfully clicked");
                break;
            default:
                throw new IllegalArgumentException("not such a page: " + page);


        }
    }
}



