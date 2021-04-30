import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;


public class BasePage {
    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement locateElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //specified different common operations in the base page

    //send inputs to the located field
    public void sendElements(By locator, String product) {
        locateElement(locator).sendKeys(product);

    }

    public void click(By locator) {

        locateElement(locator).click();
    }

    //explicit wait
    public void isElementVisible(By locator) {
        int i = 0;
        WebDriverWait wait = new WebDriverWait(driver, 30);
        while (i < 12) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;
            } catch (Exception e) {
                i++;
            }
        }

    }

    //common for assertTrue checking
    public void assertTrue(String actual, String expected, String passMessage, String failMessage) throws IOException {

        try {
            boolean contains = actual.contains(expected);
            Assert.assertTrue(contains);
            Reports.extentTest.log(Status.PASS, passMessage + actual, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e) {
            Reports.extentTest.log(Status.FAIL, failMessage + actual, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    //common for assertEquals checking
    public void assertEquals(String actual, String expected, String passMessage, String failMessage) throws IOException {
        try {
            Assert.assertEquals(actual, expected, passMessage + actual);
            Reports.extentTest.log(Status.PASS, passMessage + actual, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e) {
            Reports.extentTest.log(Status.FAIL, failMessage + actual, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    //common for assertEquals checking of integer values
    public void assertEqualsInteger(int actual, int expected, String passMessage, String failMessage) throws IOException {
        try {
            Assert.assertEquals(actual, expected, passMessage + actual);
            Reports.extentTest.log(Status.PASS, passMessage + actual, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
        catch (Exception e) {
            Reports.extentTest.log(Status.FAIL, failMessage + actual, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    //common for assertNotEquals checking
    public void assertNotEquals(String actual, String expected, String passMessage, String failMessage) throws IOException {
        try {
            Assert.assertNotEquals(actual, expected, passMessage + actual);
            Reports.extentTest.log(Status.PASS, passMessage + actual, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL, failMessage + actual, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    //scroll down to the bottom of the page
    public void scrollDownPage() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //This will scroll the web page till end.
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

    }

    //to take screenshot
    public String takeScreenshot() throws IOException {

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Random random = new Random();
        String fileName = "Screenshot" + random.nextInt(10000);

        Files.move
                (Paths.get(screenshot.getAbsolutePath()),
                        Paths.get(System.getProperty("user.dir") + "/report/" + fileName + ".png"));
        return fileName + ".png";
    }

    public String getText(By locator) {
        return locateElement(locator).getText();
    }

}
