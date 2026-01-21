package fr.esiea.qa.steps;

import fr.esiea.qa.hooks.TestContext;
import fr.esiea.qa.pages.HomePage;
import fr.esiea.qa.pages.ProductCategoryPage;
import fr.esiea.qa.pages.StorePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigationSteps {
    
    private final TestContext context;
    private HomePage homePage;
    private StorePage storePage;
    private ProductCategoryPage categoryPage;
    
    public NavigationSteps(TestContext context) {
        this.context = context;
    }
    
    @Given("I am on the Apple home page")
    public void iAmOnTheAppleHomePage() {
        homePage = new HomePage(context.getPage());
        homePage.open();
    }
    
    @Then("the navigation bar should be visible")
    public void theNavigationBarShouldBeVisible() {
        assertThat(homePage.isNavBarVisible()).isTrue();
    }
    
    @Then("the page title should contain {string}")
    public void thePageTitleShouldContain(String expectedText) {
        assertThat(homePage.getTitle().toLowerCase()).contains(expectedText.toLowerCase());
    }
    
    @When("I click on the Store link in navigation")
    public void iClickOnTheStoreLinkInNavigation() {
        storePage = homePage.goToStore();
    }
    
    @Then("I should be on the Store page")
    public void iShouldBeOnTheStorePage() {
        assertThat(storePage.isStorePageLoaded()).isTrue();
    }
    
    @Then("the Store page should display product categories")
    public void theStorePageShouldDisplayProductCategories() {
        assertThat(storePage.isStorePageLoaded()).isTrue();
    }
    
    @When("I click on the iPhone link in navigation")
    public void iClickOnTheIPhoneLinkInNavigation() {
        categoryPage = homePage.goToIPhone();
    }
    
    @Then("I should be on the iPhone page")
    public void iShouldBeOnTheIPhonePage() {
        assertThat(categoryPage.isCategoryPageLoaded()).isTrue();
    }
    
    @Then("the page should display iPhone product information")
    public void thePageShouldDisplayIPhoneProductInformation() {
        assertThat(categoryPage.getTitle().toLowerCase()).contains("iphone");
    }
    
    @When("I click on the Mac link in navigation")
    public void iClickOnTheMacLinkInNavigation() {
        categoryPage = homePage.goToMac();
    }
    
    @Then("I should be on the Mac page")
    public void iShouldBeOnTheMacPage() {
        assertThat(categoryPage.isCategoryPageLoaded()).isTrue();
    }
    
    @Then("the page should display Mac product information")
    public void thePageShouldDisplayMacProductInformation() {
        assertThat(categoryPage.getTitle().toLowerCase()).contains("mac");
    }
    
    @When("I click on the iPad link in navigation")
    public void iClickOnTheIPadLinkInNavigation() {
        categoryPage = homePage.goToIPad();
    }
    
    @Then("I should be on the iPad page")
    public void iShouldBeOnTheIPadPage() {
        assertThat(categoryPage.isCategoryPageLoaded()).isTrue();
    }
    
    @Then("the page should display iPad product information")
    public void thePageShouldDisplayIPadProductInformation() {
        assertThat(categoryPage.getTitle().toLowerCase()).contains("ipad");
    }
    
    @When("I click on the Watch link in navigation")
    public void iClickOnTheWatchLinkInNavigation() {
        categoryPage = homePage.goToWatch();
    }
    
    @Then("I should be on the Watch page")
    public void iShouldBeOnTheWatchPage() {
        assertThat(categoryPage.isCategoryPageLoaded()).isTrue();
    }
    
    @Then("the page should display Watch product information")
    public void thePageShouldDisplayWatchProductInformation() {
        assertThat(categoryPage.getTitle().toLowerCase()).contains("watch");
    }
    
    @When("I click on the AirPods link in navigation")
    public void iClickOnTheAirPodsLinkInNavigation() {
        categoryPage = homePage.goToAirPods();
    }
    
    @Then("I should be on the AirPods page")
    public void iShouldBeOnTheAirPodsPage() {
        assertThat(categoryPage.isCategoryPageLoaded()).isTrue();
    }
    
    @Then("the page should display AirPods product information")
    public void thePageShouldDisplayAirPodsProductInformation() {
        assertThat(categoryPage.getTitle().toLowerCase()).contains("airpods");
    }
    
    @When("I click on the {string} link in navigation")
    public void iClickOnTheLinkInNavigation(String category) {
        switch (category.toLowerCase()) {
            case "iphone" -> categoryPage = homePage.goToIPhone();
            case "mac" -> categoryPage = homePage.goToMac();
            case "ipad" -> categoryPage = homePage.goToIPad();
            case "watch" -> categoryPage = homePage.goToWatch();
            case "airpods" -> categoryPage = homePage.goToAirPods();
            case "store" -> storePage = homePage.goToStore();
            default -> throw new IllegalArgumentException("Unknown category: " + category);
        }
    }
    
    @Then("I should be on the {string} page")
    public void iShouldBeOnThePage(String category) {
        if (category.equalsIgnoreCase("store")) {
            assertThat(storePage.isStorePageLoaded()).isTrue();
        } else {
            assertThat(categoryPage.isCategoryPageLoaded()).isTrue();
        }
    }
}
