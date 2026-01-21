package fr.esiea.qa.steps;

import fr.esiea.qa.hooks.TestContext;
import fr.esiea.qa.mocks.MockHandler;
import fr.esiea.qa.pages.BagPage;
import fr.esiea.qa.pages.HomePage;
import fr.esiea.qa.pages.ProductCategoryPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MocksSteps {

    private final TestContext context;
    private MockHandler mockHandler;
    private HomePage homePage;
    private ProductCategoryPage categoryPage;
    private BagPage bagPage;
    private final List<String> capturedRequests = new ArrayList<>();

    public MocksSteps(TestContext context) {
        this.context = context;
    }

    @Given("I am on the Apple home page with slow network simulation")
    public void iAmOnTheAppleHomePageWithSlowNetworkSimulation() {
        mockHandler = new MockHandler(context.getPage());
        
        // Simulate 500ms delay on image requests
        mockHandler.mockSlowNetwork("**/*.jpg", 500);
        mockHandler.mockSlowNetwork("**/*.png", 500);
        
        homePage = new HomePage(context.getPage());
        homePage.open();
    }

    @Then("the page should still load successfully")
    public void thePageShouldStillLoadSuccessfully() {
        assertThat(homePage.isNavBarVisible()).isTrue();
        assertThat(homePage.getTitle()).containsIgnoringCase("apple");
    }

    @Given("I am on the Apple home page with network logging enabled")
    public void iAmOnTheAppleHomePageWithNetworkLoggingEnabled() {
        // Intercept all requests and log them
        context.getPage().onRequest(request -> {
            capturedRequests.add(request.url());
        });
        
        homePage = new HomePage(context.getPage());
        homePage.open();
    }

    @When("I navigate to the iPhone page")
    public void iNavigateToTheIPhonePage() {
        categoryPage = homePage.goToIPhone();
    }

    @Then("network requests should be captured")
    public void networkRequestsShouldBeCaptured() {
        assertThat(capturedRequests)
                .isNotEmpty()
                .anyMatch(url -> url.contains("apple.com"));
        
        System.out.println("Captured " + capturedRequests.size() + " network requests");
    }

    @Given("I set up a mock for 500 server error on bag API")
    public void iSetUpAMockFor500ServerErrorOnBagAPI() {
        mockHandler = new MockHandler(context.getPage());
        mockHandler.mock500("**/shop/bag/**");
    }

    @When("I try to access the bag")
    public void iTryToAccessTheBag() {
        bagPage = new BagPage(context.getPage());
        bagPage.open();
    }

    @Then("the page should handle the error gracefully")
    public void thePageShouldHandleTheErrorGracefully() {
        // Page should still load (Apple handles errors gracefully)
        assertThat(bagPage.isBagPageLoaded()).isTrue();
    }
}
