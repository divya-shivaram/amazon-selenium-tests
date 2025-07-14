package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Log;

public class HeaderPage {
    private WebDriver driver;

    // private By signInMenu = By.id("nav-link-accountList");
    // WebElements using @FindBy
    @FindBy(id = "nav-link-accountList")
    private WebElement accountMenu;

    @FindBy(xpath = "//span[text()='Sign Out']")
    private WebElement signOutLink;

    // Constructor to initialize PageFactory elements
    public HeaderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Logout method
    public void logout() {
        try {
            new Actions(driver).moveToElement(accountMenu).perform();
            signOutLink.click();
            Log.info("Logged out successfully");
        } catch (Exception e) {
            Log.warn("Logout skipped or failed: " + e.getMessage());
        }
    }
}
