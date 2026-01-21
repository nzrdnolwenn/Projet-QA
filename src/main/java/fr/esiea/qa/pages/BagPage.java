package fr.esiea.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BagPage extends BasePage {
    
    private static final String BAG_ITEMS = "[data-autom*='bag-item'], .rs-bag-item";
    private static final String ITEM_NAMES = "[data-autom*='bag-item-name'], .rs-bag-item-title";
    private static final String ITEM_PRICES = "[data-autom*='bag-item-price'], .rs-bag-item-price";
    private static final String REMOVE_BUTTONS = "[data-autom*='remove'], button:has-text('Remove')";
    private static final String CHECKOUT_BUTTON = "[data-autom*='checkout'], a:has-text('Check Out')";
    private static final String SUBTOTAL = "[data-autom*='subtotal'], .rs-bag-subtotal";
    private static final String QUANTITY_SELECTOR = "select[data-autom*='quantity']";
    
    public BagPage(Page page) {
        super(page);
    }
    
    public BagPage open() {
        navigate("/us/shop/goto/bag");
        waitForPageLoad();
        acceptCookiesIfPresent();
        return this;
    }
    
    public boolean isBagPageLoaded() {
        String url = getCurrentUrl().toLowerCase();
        return url.contains("/bag") || url.contains("/cart") || url.contains("/shop");
    }
    
    public boolean isEmpty() {
        try {
            String pageText = page.textContent("main").toLowerCase();
            return pageText.contains("your bag is empty") || 
                   pageText.contains("no items") ||
                   pageText.contains("bag is empty") ||
                   getItemCount() == 0;
        } catch (Exception e) {
            return true;
        }
    }
    
    public Locator getBagItems() {
        return getLocator(BAG_ITEMS);
    }
    
    public int getItemCount() {
        try {
            return getLocator(BAG_ITEMS).count();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public Locator getItemNames() {
        return getLocator(ITEM_NAMES);
    }
    
    public Locator getItemPrices() {
        return getLocator(ITEM_PRICES);
    }
    
    public BagPage removeFirstItem() {
        try {
            Locator removeButtons = getLocator(REMOVE_BUTTONS);
            if (removeButtons.count() > 0) {
                removeButtons.first().click();
                waitForPageLoad();
            }
        } catch (Exception e) {
            // No items to remove
        }
        return this;
    }
    
    public BagPage removeItem(int index) {
        try {
            Locator removeButtons = getLocator(REMOVE_BUTTONS);
            if (removeButtons.count() > index) {
                removeButtons.nth(index).click();
                waitForPageLoad();
            }
        } catch (Exception e) {
            // Index out of bounds
        }
        return this;
    }
    
    public void proceedToCheckout() {
        try {
            page.locator(CHECKOUT_BUTTON).first().click();
            waitForPageLoad();
        } catch (Exception e) {
            // Checkout unavailable
        }
    }
    
    public boolean isCheckoutEnabled() {
        try {
            Locator checkout = page.locator(CHECKOUT_BUTTON).first();
            return checkout.isVisible() && checkout.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getSubtotal() {
        try {
            return page.locator(SUBTOTAL).first().textContent();
        } catch (Exception e) {
            return "$0.00";
        }
    }
    
    public StorePage continueShopping() {
        page.navigate(BASE_URL + "/us/shop/goto/store");
        waitForPageLoad();
        return new StorePage(page);
    }
    
    public BagPage updateQuantity(int quantity) {
        try {
            Locator quantitySelector = getLocator(QUANTITY_SELECTOR);
            if (quantitySelector.count() > 0) {
                quantitySelector.first().selectOption(String.valueOf(quantity));
                waitForPageLoad();
            }
        } catch (Exception e) {
            // No quantity selector
        }
        return this;
    }
    
    public boolean containsProduct(String productName) {
        try {
            String pageContent = page.textContent("main").toLowerCase();
            return pageContent.contains(productName.toLowerCase());
        } catch (Exception e) {
            return false;
        }
    }
}
