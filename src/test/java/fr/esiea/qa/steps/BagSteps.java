package fr.esiea.qa.steps;

import fr.esiea.qa.hooks.TestContext;
import fr.esiea.qa.pages.BagPage;
import fr.esiea.qa.pages.HomePage;
import fr.esiea.qa.pages.StorePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class BagSteps {
    
    private final TestContext context;
    private HomePage homePage;
    private BagPage bagPage;
    private StorePage storePage;
    
    public BagSteps(TestContext context) {
        this.context = context;
    }
    
    @When("I open the shopping bag")
    public void iOpenTheShoppingBag() {
        homePage = new HomePage(context.getPage());
        bagPage = homePage.openBag();
    }
    
    @Given("I am on the shopping bag page")
    public void iAmOnTheShoppingBagPage() {
        bagPage = new BagPage(context.getPage());
        bagPage.open();
    }
    
    @Then("I should see the bag page")
    public void iShouldSeeTheBagPage() {
        assertThat(bagPage.isBagPageLoaded()).isTrue();
    }
    
    @Then("the bag should be empty")
    public void theBagShouldBeEmpty() {
        assertThat(bagPage.isEmpty()).isTrue();
    }
    
    @Then("the bag should contain {int} item(s)")
    public void theBagShouldContainItems(int expectedCount) {
        assertThat(bagPage.getItemCount()).isEqualTo(expectedCount);
    }
    
    @When("I remove the first item from the bag")
    public void iRemoveTheFirstItemFromTheBag() {
        bagPage.removeFirstItem();
    }
    
    @When("I click continue shopping")
    public void iClickContinueShopping() {
        storePage = bagPage.continueShopping();
    }
    
    @Then("I should be redirected to the Store")
    public void iShouldBeRedirectedToTheStore() {
        assertThat(storePage.isStorePageLoaded()).isTrue();
    }
    
    @Then("the bag should contain {string}")
    public void theBagShouldContain(String productName) {
        assertThat(bagPage.containsProduct(productName)).isTrue();
    }
    
    @Then("the checkout button should be visible")
    public void theCheckoutButtonShouldBeVisible() {
        assertThat(bagPage.isCheckoutEnabled()).isTrue();
    }
}
