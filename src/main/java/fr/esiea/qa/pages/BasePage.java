package fr.esiea.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public abstract class BasePage {
    
    protected final Page page;
    protected static final String BASE_URL = "https://www.apple.com";
    
    public BasePage(Page page) {
        this.page = page;
    }
    
    public void navigate(String path) {
        page.navigate(BASE_URL + path);
    }
    
    public void navigateToHome() {
        page.navigate(BASE_URL);
    }
    
    public String getTitle() {
        return page.title();
    }
    
    public String getCurrentUrl() {
        return page.url();
    }
    
    public void waitForPageLoad() {
        page.waitForLoadState();
    }
    
    protected void waitForElement(String selector) {
        page.waitForSelector(selector, new Page.WaitForSelectorOptions()
                .setState(WaitForSelectorState.VISIBLE));
    }
    
    protected void click(String selector) {
        page.locator(selector).click();
    }
    
    protected void type(String selector, String text) {
        page.locator(selector).fill(text);
    }
    
    protected String getText(String selector) {
        return page.locator(selector).textContent();
    }
    
    protected boolean isVisible(String selector) {
        return page.locator(selector).isVisible();
    }
    
    protected Locator getLocator(String selector) {
        return page.locator(selector);
    }
    
    protected void scrollToElement(String selector) {
        page.locator(selector).scrollIntoViewIfNeeded();
    }
    
    public void acceptCookiesIfPresent() {
        try {
            Locator cookieButton = page.locator("[data-autom='cookie-close'], .cookie-banner button, #onetrust-accept-btn-handler");
            if (cookieButton.isVisible()) {
                cookieButton.click();
            }
        } catch (Exception e) {
            // Cookie banner not present
        }
    }
}
