package fr.esiea.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ProductCategoryPage extends BasePage {
    
    private final String categoryName;
    
    private static final String PAGE_TITLE = "h1, .hero-headline, [data-autom='headline']";
    private static final String PRODUCT_LINKS = ".chapternav-item a, .localnav-menu-link";
    private static final String BUY_BUTTON = "a[href*='buy'], .ac-ln-button";
    private static final String LEARN_MORE_LINKS = "a[data-autom='learn-more'], .tile-copy a";
    private static final String PRODUCT_CARDS = ".product-tile, .rf-card, .as-producttile";
    private static final String COMPARE_SECTION = "[data-autom='compare'], .compare-section";
    private static final String HERO_IMAGE = ".hero-image, [data-autom='hero-image']";
    
    public ProductCategoryPage(Page page, String categoryName) {
        super(page);
        this.categoryName = categoryName;
    }
    
    public ProductCategoryPage open() {
        String path = "/" + categoryName.toLowerCase().replace(" ", "-");
        navigate(path);
        waitForPageLoad();
        acceptCookiesIfPresent();
        return this;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public boolean isCategoryPageLoaded() {
        String url = getCurrentUrl().toLowerCase();
        return url.contains(categoryName.toLowerCase()) || 
               getTitle().toLowerCase().contains(categoryName.toLowerCase());
    }
    
    public String getHeadline() {
        try {
            return getText(PAGE_TITLE);
        } catch (Exception e) {
            return getTitle();
        }
    }
    
    public Locator getProductLinks() {
        return getLocator(PRODUCT_LINKS);
    }
    
    public int getProductLinkCount() {
        return getLocator(PRODUCT_LINKS).count();
    }
    
    public StorePage clickBuy() {
        click(BUY_BUTTON);
        waitForPageLoad();
        return new StorePage(page);
    }
    
    public Locator getLearnMoreLinks() {
        return getLocator(LEARN_MORE_LINKS);
    }
    
    public Locator getProductCards() {
        return getLocator(PRODUCT_CARDS);
    }
    
    public boolean hasCompareSection() {
        scrollToElement("footer");
        return isVisible(COMPARE_SECTION);
    }
    
    public boolean isHeroImageDisplayed() {
        return isVisible(HERO_IMAGE);
    }
    
    public ProductCategoryPage selectProduct(String productName) {
        String selector = String.format("a:has-text('%s'), [data-autom*='%s']", 
                productName, productName.toLowerCase().replace(" ", "-"));
        click(selector);
        waitForPageLoad();
        return this;
    }
    
    public ProductCategoryPage scrollToCompare() {
        if (isVisible(COMPARE_SECTION)) {
            scrollToElement(COMPARE_SECTION);
        }
        return this;
    }
}
