package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;
    //private static Log Log = LogManager.getLog(SearchPage.class);

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    private WebElement searchButton;

    @FindBy(css = "[data-component-type='s-search-result']")
    private List<WebElement> searchResults;

    @FindBy(css = "h2 span")
    private List<WebElement> productTitles;

    @FindBy(css = ".a-price-whole")
    private List<WebElement> productPrices;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        Log.info("AmazonSearchPage initialized");
    }

    public void navigateToAmazon() {
        driver.get("https://www.amazon.com");
        Log.info("Navigated to Amazon homepage");
    }

    public void searchProduct(String productName) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(searchBox));
            searchBox.clear();
            searchBox.sendKeys(productName);
            searchButton.click();
            Log.info("Searched for product: " + productName);
        } catch (Exception e) {
            Log.error("Error while searching for product: " + e.getMessage());
            throw e;
        }
    }

    public boolean areSearchResultsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
            boolean resultsDisplayed = searchResults.size() > 0;
            Log.info("Search results displayed: " + resultsDisplayed + " (Count: " + searchResults.size() + ")");
            return resultsDisplayed;
        } catch (Exception e) {
            Log.error("Error checking search results: " + e.getMessage());
            return false;
        }
    }

    public int getSearchResultsCount() {
        int count = searchResults.size();
        Log.info("Total search results count: " + count);
        return count;
    }

    public String getFirstProductTitle() {
        try {
            if (!productTitles.isEmpty()) {
                String title = productTitles.get(0).getText();
                Log.info("First product title: " + title);
                return title;
            }
        } catch (Exception e) {
            Log.error("Error getting first product title: " + e.getMessage());
        }
        return "";
    }

    public boolean isProductInResults(String expectedProduct) {
        try {
            for (WebElement title : productTitles) {
                if (title.getText().toLowerCase().contains(expectedProduct.toLowerCase())) {
                    Log.info("Product found in results: " + expectedProduct);
                    return true;
                }
            }
            Log.info("Product not found in results: " + expectedProduct);
            return false;
        } catch (Exception e) {
            Log.error("Error checking product in results: " + e.getMessage());
            return false;
        }
    }
}