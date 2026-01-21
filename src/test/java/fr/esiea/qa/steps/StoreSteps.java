package fr.esiea.qa.steps;

import fr.esiea.qa.hooks.TestContext;
import fr.esiea.qa.pages.ProductCategoryPage;
import fr.esiea.qa.pages.StorePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreSteps {
    
    private final TestContext context;
    private StorePage storePage;
    private ProductCategoryPage categoryPage;
    
    public StoreSteps(TestContext context) {
        this.context = context;
    }
    
    @Given("I am on the Apple Store page")
    public void iAmOnTheAppleStorePage() {
        storePage = new StorePage(context.getPage());
        storePage.open();
    }
    
    @Then("the Store page should be displayed")
    public void theStorePageShouldBeDisplayed() {
        assertThat(storePage.isStorePageLoaded()).isTrue();
    }
    
    @Then("I should see product categories")
    public void iShouldSeeProductCategories() {
        assertThat(storePage.isStorePageLoaded()).isTrue();
    }
    
    @When("I click on Shop Mac")
    public void iClickOnShopMac() {
        categoryPage = storePage.shopMac();
    }
    
    @Then("I should see Mac products")
    public void iShouldSeeMacProducts() {
        assertThat(categoryPage.isCategoryPageLoaded()).isTrue();
    }
    
    @When("I click on Shop iPhone")
    public void iClickOnShopIPhone() {
        categoryPage = storePage.shopIPhone();
    }
    
    @Then("I should see iPhone products")
    public void iShouldSeeIPhoneProducts() {
        assertThat(categoryPage.isCategoryPageLoaded()).isTrue();
    }
    
    @When("I click on Shop iPad")
    public void iClickOnShopIPad() {
        categoryPage = storePage.shopIPad();
    }
    
    @Then("I should see iPad products")
    public void iShouldSeeIPadProducts() {
        assertThat(categoryPage.isCategoryPageLoaded()).isTrue();
    }
    
    @When("I click on Shop Watch")
    public void iClickOnShopWatch() {
        categoryPage = storePage.shopWatch();
    }
    
    @Then("I should see Watch products")
    public void iShouldSeeWatchProducts() {
        assertThat(categoryPage.isCategoryPageLoaded()).isTrue();
    }
    
    @Then("the page should contain {string}")
    public void thePageShouldContain(String expectedText) {
        String pageTitle = categoryPage != null ? categoryPage.getTitle() : storePage.getTitle();
        String currentUrl = categoryPage != null ? categoryPage.getCurrentUrl() : storePage.getCurrentUrl();
        
        assertThat(pageTitle.toLowerCase() + " " + currentUrl.toLowerCase())
                .containsIgnoringCase(expectedText);
    }
}
