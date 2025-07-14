package pages;

import lombok.With;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;


public class LoginPage {
    WebDriver driver;

    // Assume we want to find the input below the label
    //WebElement label = driver.findElement(By.id("usernameLabel"));

    // Find the input field below the label using relative locator selenium 4
    //WebElement usernameInput = driver.findElement(
    //  with(By.tagName("input")).below(label)
    //);

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

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

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
