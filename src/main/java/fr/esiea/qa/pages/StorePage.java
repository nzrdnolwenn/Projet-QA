package fr.esiea.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class StorePage extends BasePage {
    
    private static final String STORE_CONTENT = "main";
    private static final String PRODUCT_TILES = ".rf-flagship-product, [data-autom*='tile']";
    
    public StorePage(Page page) {
        super(page);
    }
    
    public StorePage open() {
        navigate("/us/shop/goto/store");
        waitForPageLoad();
        acceptCookiesIfPresent();
        return this;
    }
    
    public boolean isStorePageLoaded() {
        String url = getCurrentUrl().toLowerCase();
        return url.contains("/store") || url.contains("/shop");
    }
    
    public boolean isHeroVisible() {
        return isVisible(STORE_CONTENT);
    }
    
    public Locator getProductTiles() {
        return getLocator(PRODUCT_TILES);
    }
    
    public int getProductTileCount() {
        try {
            return getLocator(PRODUCT_TILES).count();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public ProductCategoryPage shopMac() {
        page.navigate(BASE_URL + "/us/shop/goto/buy_mac");
        waitForPageLoad();
        return new ProductCategoryPage(page, "Mac");
    }
    
    public ProductCategoryPage shopIPhone() {
        page.navigate(BASE_URL + "/us/shop/goto/buy_iphone");
        waitForPageLoad();
        return new ProductCategoryPage(page, "iPhone");
    }
    
    public ProductCategoryPage shopIPad() {
        page.navigate(BASE_URL + "/us/shop/goto/buy_ipad");
        waitForPageLoad();
        return new ProductCategoryPage(page, "iPad");
    }
    
    public ProductCategoryPage shopWatch() {
        page.navigate(BASE_URL + "/us/shop/goto/buy_watch");
        waitForPageLoad();
        return new ProductCategoryPage(page, "Watch");
    }
    
    public StorePage goToAccessories() {
        page.navigate(BASE_URL + "/us/shop/goto/buy_accessories");
        waitForPageLoad();
        return this;
    }
    
    public Locator getCategoryCards() {
        return page.locator("main a[href*='/shop/']");
    }
    
    public boolean hasProductCategory(String category) {
        String pageContent = page.textContent("main").toLowerCase();
        return pageContent.contains(category.toLowerCase());
    }
}
