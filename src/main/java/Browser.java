import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.DataProvider;

public class Browser {

    public static WebDriver openBrowser(WebDriver driver, String browserName, String appURL) {
        if (browserName == "Chrome") {
            String baseDirectory = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", baseDirectory + "/src/main/resources/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
        } else if (browserName == "Firefox") {
            String baseDirectory = System.getProperty("user.dir");
            System.setProperty("webdriver.gecko.driver", baseDirectory + "/src/main/resources/geckodriver.exe");
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-notifications");
            driver = new FirefoxDriver(options);
        } else {
            System.out.println("Do not support this browser");
        }
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.findElement(By.xpath("//button[@class=\"_2KpZ6l _2doB4z\"]")).click();
        return driver;

    }

    public static void closeBrowser(WebDriver driver) {

        driver.close();
    }
}
