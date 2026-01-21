package fr.esiea.qa.hooks;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/**
 * Shared browser context for Cucumber step definitions via PicoContainer DI.
 */
public class TestContext {
    
    private Playwright playwright;
    private Browser browser;
    private BrowserContext browserContext;
    private Page page;
    
    public void initBrowser(boolean headless) {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new com.microsoft.playwright.BrowserType.LaunchOptions()
                        .setHeadless(headless)
                        .setSlowMo(100)
        );
        browserContext = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1920, 1080)
                        .setLocale("en-US")
        );
        page = browserContext.newPage();
        page.setDefaultTimeout(30000);
        page.setDefaultNavigationTimeout(30000);
    }
    
    public Page getPage() {
        return page;
    }
    
    public Browser getBrowser() {
        return browser;
    }
    
    public BrowserContext getBrowserContext() {
        return browserContext;
    }
    
    public Playwright getPlaywright() {
        return playwright;
    }
    
    public void closeBrowser() {
        if (page != null) page.close();
        if (browserContext != null) browserContext.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
    
    public byte[] takeScreenshot() {
        return page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
    }
    
    public void navigateTo(String url) {
        page.navigate(url);
    }
}
