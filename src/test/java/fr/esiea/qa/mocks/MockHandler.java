package fr.esiea.qa.mocks;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;

/**
 * Intercept and mock network requests for testing edge cases.
 */
public class MockHandler {
    
    private final Page page;
    
    public MockHandler(Page page) {
        this.page = page;
    }
    
    public void mockProductApi() {
        page.route("**/api/products/**", route -> {
            route.fulfill(new Route.FulfillOptions()
                    .setStatus(200)
                    .setContentType("application/json")
                    .setBody(getMockProductResponse())
            );
        });
    }
    
    public void mockSearchApi(String mockResponse) {
        page.route("**/search/**", route -> {
            route.fulfill(new Route.FulfillOptions()
                    .setStatus(200)
                    .setContentType("application/json")
                    .setBody(mockResponse)
            );
        });
    }
    
    public void mockBagApi() {
        page.route("**/shop/bag/**", route -> {
            route.fulfill(new Route.FulfillOptions()
                    .setStatus(200)
                    .setContentType("application/json")
                    .setBody(getMockBagResponse())
            );
        });
    }
    
    public void mockNetworkError(String urlPattern) {
        page.route(urlPattern, route -> route.abort("failed"));
    }
    
    public void mockSlowNetwork(String urlPattern, int delayMs) {
        page.route(urlPattern, route -> {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            route.resume();
        });
    }
    
    public void mock404(String urlPattern) {
        page.route(urlPattern, route -> {
            route.fulfill(new Route.FulfillOptions()
                    .setStatus(404)
                    .setContentType("application/json")
                    .setBody("{\"error\": \"Not found\"}")
            );
        });
    }
    
    public void mock500(String urlPattern) {
        page.route(urlPattern, route -> {
            route.fulfill(new Route.FulfillOptions()
                    .setStatus(500)
                    .setContentType("application/json")
                    .setBody("{\"error\": \"Internal server error\"}")
            );
        });
    }
    
    public void clearMock(String urlPattern) {
        page.unroute(urlPattern);
    }
    
    private String getMockProductResponse() {
        return """
            {
                "products": [
                    {"id": "iphone-15-pro", "name": "iPhone 15 Pro", "price": 999.00},
                    {"id": "macbook-pro-14", "name": "MacBook Pro 14", "price": 1999.00},
                    {"id": "ipad-pro", "name": "iPad Pro", "price": 799.00}
                ]
            }
            """;
    }
    
    private String getMockBagResponse() {
        return """
            {
                "items": [],
                "subtotal": 0.00,
                "currency": "USD",
                "itemCount": 0
            }
            """;
    }
}
