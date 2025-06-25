package pages;

import utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    WebDriver driver;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initializes all @FindBy elements
    }

    // private By signInMenu = By.id("nav-link-accountList");

    // Elements using @FindBy
    @FindBy(id = "nav-link-accountList")
    private WebElement signInMenu;

    @FindBy(id = "ap_email_login")
    private WebElement emailField;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    @FindBy(id = "ap_password")
    private WebElement passwordField;

    @FindBy(id = "signInSubmit")
    private WebElement loginBtn;

    @FindBy(css = ".a-alert-content")
    private WebElement errorMessage;

    // Actions

//    public void clickSignIn() {
//        driver.findElement(signInMenu).click();
//    }

    public void clickSignIn() {
        Log.info("Clicking SignIn button");
        signInMenu.click();
    }

    public void enterEmail(String email) {
        Log.info("Entering email: " + email);
        emailField.sendKeys(email);
    }

    public void clickContinue() {
        Log.info("Clicking Continue button");
        continueBtn.click();
    }

    public void enterPassword(String password) {
        Log.info("Enter Password");
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        Log.info("Clicking Login button");
        loginBtn.click();
    }
}
