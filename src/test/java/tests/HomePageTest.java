package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void testHomePageTitle() {
        String title = getDriver().getTitle();
        System.out.println("Page title: " + title);
        assert title.contains("Amazon.in");

    }
}
