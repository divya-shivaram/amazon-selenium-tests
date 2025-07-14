package practice;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class PracticePgaeTest {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.automationtesting.in/Frames.html");
        System.out.println("Page title is: " + driver.getTitle());

//        //-----------------------------Frames--------------------------------------------
//        WebElement singleFrameButton = driver.findElement(By.xpath("//a[contains(text(), 'Single Iframe')]"));
//        WebElement multiFrameButton = driver.findElement(By.xpath("//a[contains(text(), 'Iframe with')]"));
//        multiFrameButton.click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        WebElement parentFrame = driver.findElement(By.xpath("//iframe[@src= 'MultipleFrames.html']"));
//        driver.switchTo().frame(parentFrame);
//        WebElement parentFrameText = driver.findElement(By.xpath("//h5[contains(text(),'Nested iFrames')]"));
//        System.out.println(parentFrameText.getText());
//        System.out.println("parentFrameText");
//
//        WebElement childFrame = driver.findElement(By.xpath("//iframe[@src= 'SingleFrame.html']"));
//        driver.switchTo().frame(childFrame);
//        WebElement childFrameText = driver.findElement(By.xpath("//h5[contains(text(),'iFrame Demo')]"));
//        System.out.println(childFrameText.getText());
//        System.out.println("childFrameText");
//
//        driver.switchTo().parentFrame();
//        driver.switchTo().parentFrame();
//        //driver.switchTo().defaultContent();
//        singleFrameButton.click();
//        Thread.sleep(1000);
//
//        //---------------------------Verbose Code----------------------------------------
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOf(multiFrameButton));
//        multiFrameButton.click();
//        Thread.sleep(1000);
//
//        //---------------------------Lambda Expression----------------------------------------
//        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait1.until((driver1) -> singleFrameButton.isDisplayed());
//        singleFrameButton.click();
//        Thread.sleep(1000);



        //---------------------------Alerts----------------------------------------

        WebElement swithToMenu = driver.findElement(By.xpath("//a[contains(text(), 'SwitchTo')]"));
        swithToMenu.click();

        WebElement alertButton = driver.findElement(By.xpath("//a[contains(text(), 'Alerts')]"));
        alertButton.click();

        WebElement alertWithOkButton = driver.findElement(By.xpath("//a[@href='#OKTab']"));
        alertWithOkButton.click();

        WebElement alertWithOkClick = driver.findElement(By.xpath("//button[@class = 'btn btn-danger']"));
        alertWithOkClick.click();

        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();

        WebElement alertWithOkAndCancelButton = driver.findElement(By.xpath("//a[contains(text(), " +
                "'Alert with OK & Cancel')]"));
        alertWithOkAndCancelButton.click();

        WebElement alertWithOkAndCancelClick = driver.findElement(By.xpath("//div[@id='CancelTab']/button"));
        alertWithOkAndCancelClick.click();
        System.out.println(alert.getText());
       alert.accept();

        alertWithOkAndCancelClick.click();
        System.out.println(alert.getText());
        alert.dismiss();


        WebElement alertWithTextBoxButton = driver.findElement(By.xpath("//a[contains(text(), 'Alert with Textbox')]"));
        alertWithTextBoxButton.click();

        WebElement alertWithTextBoxClick = driver.findElement(By.xpath("//div[@id='Textbox']/button"));
        alertWithTextBoxClick.click();

        System.out.println(alert.getText());
        alert.sendKeys("divya");
        Thread.sleep(5000);
        alert.accept();

        WebElement para = driver.findElement(By.xpath("//p[@id = 'demo1']"));
        System.out.println(para.getText());


        Thread.sleep(1000);
        //driver.quit();
    }
}
