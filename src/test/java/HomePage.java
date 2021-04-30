import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class HomePage extends BasePage {

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By searchTab = By.xpath("//input[@name=\"q\"]");
    private By viewedProductName = By.xpath("//div[@class=\"_4rR01T\"]");
    private By noProduct = By.xpath("//div[@class=\"_3uTeW4\"]");
    private By searchButton = By.xpath("//button[@class=\"L0Z3Pu\"]");
    private By selectProduct = By.xpath("//div[@class=\"_4ddWXP\"]");
    private By sortHighToLow = By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[1]/div/div/div[2]/div[4]");
    private By firstProductName = By.xpath("//a[@class=\"s1Q9rs\"]");
    private By flipkartAddress = By.xpath("//*[@id=\"container\"]/div/footer/div/div[3]/div[1]/div[6]");
    private By footerAbout = By.xpath("//div[@class=\"_2Brcj4\"]");
    private By more = By.xpath("//div[@class=\"exehdJ\"]");
    private By topOffers = By.xpath("//div[@class=\"xtXmba\"]");
    private By dealsTopOffers = By.xpath("//*[@id=\"container\"]/div/div[3]/div[2]/div/div[1]/div/h2");
    private By fashionTopOffers = By.xpath("//*[@id=\"container\"]/div/div[3]/div[3]/div/div[1]/div/h2");
    private By filterOption = By.xpath("//div[@class=\"_24_Dny\"]");
    private By resultCount = By.xpath("//span[@class=\"_10Ermr\"]");
    private By searchResult = By.xpath("//div[@class=\"_1YokD2 _2GoDe3\"]");
    private By filteredResult = By.xpath("//div[@class=\"_36fx1h _6t1WkM _3HqJxg\"]");
    private By newOffers = By.xpath("//div[@class=\"_6t1WkM _3HqJxg\"]");


    //Searching product in the search tab
    public void searchProduct(String productName) throws InterruptedException {
        sendElements(searchTab, productName);
        click(searchButton);
        isElementVisible(searchResult);
        Reports.extentTest.log(Status.INFO, "Viewed Products corresponding to the search " + productName);
    }

    //select the product from the products displayed
    public void selectProduct() throws InterruptedException {
        isElementVisible(selectProduct);
        click(selectProduct);
        Reports.extentTest.log(Status.INFO, "Selected the Product ");
    }

    //Verify whether the product displayed is same as the one searched
    public void verifyProductDisplayed(String productName, String expectedProduct) throws IOException, InterruptedException {
        searchProduct(productName);
        String actualProduct = getText(viewedProductName);
        isElementVisible(viewedProductName);
        assertTrue(actualProduct, expectedProduct, "Product verified as ", "Not the expected one,Product verified as ");
    }

    //Verify whether the no results page found for invalid item
    public void invalidProduct(String productName, String expectedResult) throws IOException, InterruptedException {
        searchProduct(productName);
        String actualProduct = getText(noProduct);
        assertTrue(actualProduct, expectedResult, "No results found for the invalid item", "Some Products displayed");
    }

    //Verify the most expensive item
    public void mostExpensiveItem(String productName) throws IOException, InterruptedException {
        searchProduct(productName);
        click(sortHighToLow);
        //  isElementVisible(searchResult);
        Thread.sleep(4000);
        String actualProductName = getText(firstProductName);
        isElementVisible(firstProductName);
        Reports.extentTest.log(Status.INFO, "Most expensive product is  " + actualProductName, MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }

    //Verify the registered flipkart address at the footer
    public void verifyRegisteredAddress(String registeredAddress) throws IOException, InterruptedException {
        scrollDownPage();
        isElementVisible(flipkartAddress);
        String actualAddress = getText(flipkartAddress);
        assertEquals(actualAddress, registeredAddress, "Address verified as ", "Address not verified ");

    }

    //Verify the contents in ABOUT Footer
    public void verifyAbout(String aboutFooter) throws IOException, InterruptedException {

        scrollDownPage();
        isElementVisible(footerAbout);
        String actualAbout = getText(footerAbout);
        assertEquals(actualAbout, aboutFooter, "I see Contact Us, Careers and Press under ABOUT section in the footer ", "I couldn't see Contact Us, Careers and Press under ABOUT section in the footer ");
    }

    //verify the options in More is visible
    public void verifyMore() throws IOException, InterruptedException {
        Actions actions = new Actions(driver);
        WebElement moreElement = driver.findElement(more);
        actions.moveToElement(moreElement).perform();
        isElementVisible(more);
        Reports.extentTest.log(Status.INFO, "More Option visible ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }

    //verify whether different offers are available under Top offers
    public void verifyOffers(String offers) throws InterruptedException, IOException {
        click(topOffers);
        isElementVisible(topOffers);
        //     isElementVisible(dealsTopOffers);
        //   String actualTitle = getText(dealsTopOffers) + "," + getText(fashionTopOffers);
        //  assertTrue(actualTitle,offers,"Top offers visible ","Top offers not visible "); //-as the page changed on 30.04.2021
        Reports.extentTest.log(Status.INFO, "Top offers visible ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }

    //Verify whether the filtered products are visible
    public void verifyFilterOption(String productName) throws InterruptedException, IOException {
        searchProduct(productName);
        String resultCountValue = getText(resultCount);
        isElementVisible(filterOption);
        click(filterOption);
        Thread.sleep(2000);
        String filterResultCount = getText(resultCount);
        Thread.sleep(2000);
        assertNotEquals(resultCountValue, filterResultCount, "Result count is updated ", "Result count is not updated ");
    }

}




