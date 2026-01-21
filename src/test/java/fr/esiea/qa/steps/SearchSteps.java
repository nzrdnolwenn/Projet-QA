package fr.esiea.qa.steps;

import fr.esiea.qa.hooks.TestContext;
import fr.esiea.qa.pages.HomePage;
import fr.esiea.qa.pages.SearchResultsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchSteps {
    
    private final TestContext context;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    
    public SearchSteps(TestContext context) {
        this.context = context;
        this.homePage = new HomePage(context.getPage());
    }
    
    @When("I open the search")
    public void iOpenTheSearch() {
        homePage = new HomePage(context.getPage());
        homePage.openSearch();
    }
    
    @When("I search for {string}")
    public void iSearchFor(String query) {
        searchResultsPage = homePage.search(query);
    }
    
    @Then("I should see search results")
    public void iShouldSeeSearchResults() {
        searchResultsPage = new SearchResultsPage(context.getPage());
        searchResultsPage.waitForPageLoad();
        
        String currentUrl = searchResultsPage.getCurrentUrl();
        assertThat(currentUrl.toLowerCase())
                .containsAnyOf("search", "shop", "iphone", "mac", "ipad", "airpods", "watch");
    }
    
    @Then("the results should contain {string}")
    public void theResultsShouldContain(String expectedText) {
        searchResultsPage = new SearchResultsPage(context.getPage());
        
        String currentUrl = searchResultsPage.getCurrentUrl().toLowerCase();
        String pageTitle = searchResultsPage.getTitle().toLowerCase();
        String expected = expectedText.toLowerCase();
        
        assertThat(currentUrl + " " + pageTitle)
                .containsIgnoringCase(expected.replace(" ", ""));
    }
    
    @Then("I should see no results")
    public void iShouldSeeNoResults() {
        assertThat(searchResultsPage.hasNoResults()).isTrue();
    }
    
    @Then("I should see at least {int} results")
    public void iShouldSeeAtLeastResults(int minResults) {
        assertThat(searchResultsPage.getResultCount()).isGreaterThanOrEqualTo(minResults);
    }
    
    @When("I click on the first search result")
    public void iClickOnTheFirstSearchResult() {
        searchResultsPage.clickFirstResult();
    }
}
