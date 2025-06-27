package tests;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;
import utils.Log;

public class SearchTest extends BaseTest {

    @Test(priority = 1, description = "Search for iPhone and verify results")
    public void testIPhoneSearch() {
        test = extent.createTest("iPhone Search Test");

        try {
            SearchPage searchPage = new SearchPage(getDriver());

            // Navigate to Amazon
            test.log(Status.INFO, "Navigating to Amazon homepage");
            searchPage.navigateToAmazon();

            // Search for iPhone
            test.log(Status.INFO, "Searching for iPhone");
            searchPage.searchProduct("iPhone");

            // Verify search results
            test.log(Status.INFO, "Verifying search results are displayed");
            Assert.assertTrue(searchPage.areSearchResultsDisplayed(),
                    "Search results should be displayed");

            // Verify iPhone is in results
            test.log(Status.INFO, "Verifying iPhone appears in search results");
            Assert.assertTrue(searchPage.isProductInResults("iPhone"),
                    "iPhone should appear in search results");

            // Log additional info
            int resultCount = searchPage.getSearchResultsCount();
            test.log(Status.INFO, "Total search results: " + resultCount);

            String firstProduct = searchPage.getFirstProductTitle();
            test.log(Status.INFO, "First product: " + firstProduct);

            test.log(Status.PASS, "iPhone search test completed successfully");
            Log.info("iPhone search test passed");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Log.error("iPhone search test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 2, description = "Search for laptop and verify results")
    public void testLaptopSearch() {
        test = extent.createTest("Laptop Search Test");

        try {
            SearchPage searchPage = new SearchPage(getDriver());

            test.log(Status.INFO, "Navigating to Amazon homepage");
            searchPage.navigateToAmazon();

            test.log(Status.INFO, "Searching for laptop");
            searchPage.searchProduct("laptop");

            test.log(Status.INFO, "Verifying search results are displayed");
            Assert.assertTrue(searchPage.areSearchResultsDisplayed(),
                    "Search results should be displayed");

            test.log(Status.INFO, "Verifying laptop appears in search results");
            Assert.assertTrue(searchPage.isProductInResults("laptop"),
                    "Laptop should appear in search results");

            int resultCount = searchPage.getSearchResultsCount();
            test.log(Status.INFO, "Total search results: " + resultCount);
            Assert.assertTrue(resultCount > 0, "Should have search results");

            test.log(Status.PASS, "Laptop search test completed successfully");
            Log.info("Laptop search test passed");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Log.error("Laptop search test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }

    @Test(priority = 3, description = "Search for invalid product")
    public void testInvalidProductSearch() {
        test = extent.createTest("Invalid Product Search Test");

        try {
            SearchPage searchPage = new SearchPage(getDriver());

            test.log(Status.INFO, "Navigating to Amazon homepage");
            searchPage.navigateToAmazon();

            test.log(Status.INFO, "Searching for invalid product");
            searchPage.searchProduct("xyzinvalidproduct123");

            // Even invalid searches might return results on Amazon
            test.log(Status.INFO, "Checking if any results are displayed");
            boolean resultsDisplayed = searchPage.areSearchResultsDisplayed();

            if (resultsDisplayed) {
                test.log(Status.INFO, "Amazon returned some results even for invalid product");
            } else {
                test.log(Status.INFO, "No results displayed for invalid product");
            }

            test.log(Status.PASS, "Invalid product search test completed");
            Log.info("Invalid product search test completed");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Log.error("Invalid product search test failed: " + e.getMessage());
            Assert.fail("Test failed: " + e.getMessage());
        }
    }
}