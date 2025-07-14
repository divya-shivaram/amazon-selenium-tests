package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.JsonCredentialsReader;

public class LoginTest extends BaseTest {

    @Test
    public void testAmazonTitle() {
        String title = getDriver().getTitle();
        assert title.contains("Amazon");
        System.out.println("test");
    }

    @Test
    public void testAmazonLoginWithValidCredentials() {

        JsonCredentialsReader reader = new JsonCredentialsReader();
        String username = reader.getUsername();
        String password = reader.getPassword();

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage.clickSignIn();
        loginPage.enterEmail(username);
        loginPage.clickContinue();
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }
}
