package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotPath = "test-output/screenshots/" + testName + "_" + timestamp + ".png";

            Files.createDirectories(Paths.get("test-output/screenshots"));
            File destFile = new File(screenshotPath);
            Files.copy(srcFile.toPath(), destFile.toPath());

            return screenshotPath;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
