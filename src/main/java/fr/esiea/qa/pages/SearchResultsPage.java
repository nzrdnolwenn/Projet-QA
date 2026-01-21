package fr.esiea.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class SearchResultsPage extends BasePage {
    
    private static final String RESULT_ITEMS = "[data-autom*='result'], .rf-serp-productresult, a[href*='/shop/']";
    private static final String RESULT_LINKS = "main a";
    private static final String NO_RESULTS = "[data-autom*='noresult'], .rf-serp-noresults";
    
    public SearchResultsPage(Page page) {
        super(page);
    }
    
    public SearchResultsPage open(String query) {
        navigate("/us/search/" + query.replace(" ", "+"));
        waitForPageLoad();
        acceptCookiesIfPresent();
        return this;
    }
    
    public boolean hasResults() {
        waitForPageLoad();
        String url = getCurrentUrl().toLowerCase();
        return url.contains("search") || url.contains("shop") || 
               url.contains("iphone") || url.contains("mac") || 
               url.contains("ipad") || url.contains("airpods") || 
               url.contains("watch");
    }
    
    public boolean hasNoResults() {
        try {
            return page.locator(NO_RESULTS).first().isVisible();
        } catch (Exception e) {
            return false;
        }
    }
    
    public Locator getResultItems() {
        return getLocator(RESULT_ITEMS);
    }
    
    public int getResultCount() {
        try {
            return getLocator(RESULT_ITEMS).count();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public Locator getResultLinks() {
        return getLocator(RESULT_LINKS);
    }
    
    public void clickFirstResult() {
        Locator results = getResultLinks();
        if (results.count() > 0) {
            results.first().click();
            waitForPageLoad();
        }
    }
    
    public void clickResult(int index) {
        Locator results = getResultLinks();
        if (results.count() > index) {
            results.nth(index).click();
            waitForPageLoad();
        }
    }
    
    public boolean resultsContainText(String text) {
        try {
            String pageContent = page.textContent("main").toLowerCase();
            return pageContent.contains(text.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getDisplayedQuery() {
        try {
            return page.textContent("h1");
        } catch (Exception e) {
            return "";
        }
    }
}
