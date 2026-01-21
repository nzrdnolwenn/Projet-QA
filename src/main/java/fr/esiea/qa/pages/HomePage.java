package fr.esiea.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage extends BasePage {
    
    private static final String NAV_BAR = "nav[aria-label='Global']";
    private static final String SEARCH_BUTTON = "#globalnav-menubutton-link-search";
    private static final String HERO_SECTION = "main";
    private static final String COUNTRY_CLOSE_BUTTON = "button[aria-label*='close' i], button[aria-label*='fermer' i]";
    
    public HomePage(Page page) {
        super(page);
    }
    
    public HomePage open() {
        navigateToHome();
        waitForPageLoad();
        closeCountrySelector();
        acceptCookiesIfPresent();
        return this;
    }
    
    // Apple shows a country/region popup that blocks interactions
    private void closeCountrySelector() {
        try {
            page.waitForTimeout(1500);
            Locator closeButton = page.locator(COUNTRY_CLOSE_BUTTON).first();
            if (closeButton.isVisible()) {
                closeButton.click();
                page.waitForTimeout(500);
            }
        } catch (Exception e) {
            // Not present
        }
    }
    
    public boolean isNavBarVisible() {
        try {
            return page.locator(NAV_BAR).first().isVisible();
        } catch (Exception e) {
            return page.locator("#globalnav").isVisible();
        }
    }
    
    public boolean isHeroSectionDisplayed() {
        return isVisible(HERO_SECTION);
    }
    
    public HomePage openSearch() {
        try {
            Locator searchBtn = page.locator(SEARCH_BUTTON);
            if (searchBtn.isVisible()) {
                searchBtn.click();
                page.waitForTimeout(500);
            }
        } catch (Exception e) {
            // Search unavailable
        }
        return this;
    }
    
    // Direct URL navigation is more reliable than clicking nav elements
    public SearchResultsPage search(String query) {
        page.navigate(BASE_URL + "/us/search/" + query.replace(" ", "+"));
        waitForPageLoad();
        return new SearchResultsPage(page);
    }
    
    public StorePage goToStore() {
        page.navigate(BASE_URL + "/us/shop/goto/store");
        waitForPageLoad();
        return new StorePage(page);
    }
    
    public ProductCategoryPage goToMac() {
        page.navigate(BASE_URL + "/mac/");
        waitForPageLoad();
        return new ProductCategoryPage(page, "Mac");
    }
    
    public ProductCategoryPage goToIPad() {
        page.navigate(BASE_URL + "/ipad/");
        waitForPageLoad();
        return new ProductCategoryPage(page, "iPad");
    }
    
    public ProductCategoryPage goToIPhone() {
        page.navigate(BASE_URL + "/iphone/");
        waitForPageLoad();
        return new ProductCategoryPage(page, "iPhone");
    }
    
    public ProductCategoryPage goToWatch() {
        page.navigate(BASE_URL + "/watch/");
        waitForPageLoad();
        return new ProductCategoryPage(page, "Watch");
    }
    
    public ProductCategoryPage goToAirPods() {
        page.navigate(BASE_URL + "/airpods/");
        waitForPageLoad();
        return new ProductCategoryPage(page, "AirPods");
    }
    
    public BagPage openBag() {
        page.navigate(BASE_URL + "/us/shop/goto/bag");
        waitForPageLoad();
        return new BagPage(page);
    }
    
    public Locator getNavItems() {
        return page.locator("nav[aria-label='Global'] li");
    }
    
    public boolean hasNavItem(String itemName) {
        String selector = String.format("nav a[data-analytics-title='%s']", itemName.toLowerCase());
        try {
            return page.locator(selector).first().isVisible();
        } catch (Exception e) {
            return false;
        }
    }
}
