package fr.esiea.qa.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    
    private final TestContext context;
    
    public Hooks(TestContext context) {
        this.context = context;
    }
    
    @Before
    public void setUp(Scenario scenario) {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        context.initBrowser(headless);

    }
    
    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
                byte[] screenshot = context.takeScreenshot();
                scenario.attach(screenshot, "image/png", "failure-screenshot");
            } catch (Exception e) {
                System.err.println("Failed to take screenshot: " + e.getMessage());
            }
        }
        
        context.closeBrowser();
    }
}
