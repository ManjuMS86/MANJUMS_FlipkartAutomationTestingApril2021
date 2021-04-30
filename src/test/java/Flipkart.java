import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class Flipkart {

    private WebDriver driver;

    //opening the browser
    @BeforeMethod
    public void openBrowser() {
        driver = Browser.openBrowser(driver, "Chrome", "https://www.flipkart.com/");
    }

    //Testcase1: Validate Search for item - Positive Scenario
    @Test(dataProvider = "validateItemDP", dataProviderClass = DataSupply.class)
    public void validateSearchValidItem(String productName, String expectedProduct) throws IOException, InterruptedException {
        Reports.createTest("Validate search for item-Positive");
        HomePage homePage = new HomePage(driver);
        homePage.verifyProductDisplayed(productName, expectedProduct);
    }

    //Testcase2: Validate Search for item - Negative Scenario
    @Test(dataProvider = "validateInvalidItemDP", dataProviderClass = DataSupply.class)
    public void validateSearchInvalidItem(String productName, String expectedResult) throws IOException, InterruptedException {
        Reports.createTest("Validate search for item-Negative");
        HomePage homePage = new HomePage(driver);
        homePage.invalidProduct(productName, expectedResult);
    }

    //Testcase 3:Validate Adding item to cart
    @Test(dataProvider = "itemNameDP", dataProviderClass = DataSupply.class)
    public void addItemToCart(String productName) throws IOException, InterruptedException {
        Reports.createTest("Validate adding item to Cart");
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        homePage.searchProduct(productName);
        homePage.selectProduct();
        cartPage.multiFunctionHandle(1, 0);
    }

    //TestCase 4:Validate remove item from cart
    @Test(dataProvider = "itemNameDP", dataProviderClass = DataSupply.class)
    public void removeItemFromCart(String productName) throws IOException, InterruptedException {
        Reports.createTest("Validate remove item from cart");
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        homePage.searchProduct(productName);
        homePage.selectProduct();
        cartPage.multiFunctionHandle(2, 0);
    }

    //TestCase 5: Verify the name of the most expensive mobile phone
    @Test(dataProvider = "itemNameDP", dataProviderClass = DataSupply.class)
    public void verifyExpensiveItemName(String productName) throws InterruptedException, IOException {
        Reports.createTest("Verify the name of the most expensive mobile phone is x");
        HomePage homePage = new HomePage(driver);
        homePage.mostExpensiveItem(productName);
    }

    //TestCase 6: Verify invalid pincode is not added
    @Test(dataProvider = "invalidPincodeDP", dataProviderClass = DataSupply.class)
    public void verifyInvalidPincode(String productName, int pincode) throws InterruptedException, IOException {
        Reports.createTest("Verify invalid pincode is not added");
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        homePage.searchProduct(productName);
        homePage.selectProduct();
        cartPage.multiFunctionHandle(3, pincode);
    }

    //TestCase 7 : Verify filter options
    @Test(dataProvider = "filterDP", dataProviderClass = DataSupply.class)
    public void verifyFilters(String productName) throws InterruptedException, IOException {
        Reports.createTest("Verify filter options");
        HomePage homePage = new HomePage(driver);
        homePage.verifyFilterOption(productName);
    }

    //TestCase 8: Verify the registered address of Flipkart
    @Test(dataProvider = "registeredAddressDP", dataProviderClass = DataSupply.class)
    public void verifyRegisteredAddress(String address) throws InterruptedException, IOException {
        Reports.createTest("Verify the registered address of Flipkart");
        HomePage homePage = new HomePage(driver);
        homePage.verifyRegisteredAddress(address);
    }


    //top offers page changed on 30.04.2021
    //TestCase 9: Verify the list of offers available in offer zone
    @Test(dataProvider = "topOffersDP", dataProviderClass = DataSupply.class)
    public void verifyTopOffers(String offers) throws InterruptedException, IOException {
        Reports.createTest("Verify the list of offers available in offer zone");
        HomePage homePage = new HomePage(driver);
        homePage.verifyOffers(offers);
    }

    //TestCase 10: Verify pincode works
    @Test(dataProvider = "validPincodeDP", dataProviderClass = DataSupply.class)
    public void verifyValidPincode(String productName, int pincode) throws InterruptedException, IOException {
        Reports.createTest("Verify pincode works");
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        homePage.searchProduct(productName);
        homePage.selectProduct();
        cartPage.multiFunctionHandle(3, pincode);
    }

    //
    //TestCase 11: Verify Login page is displayed after clicking on Buy now for an item
    @Test(dataProvider = "loginDP", dataProviderClass = DataSupply.class)
    public void verifyLoginPage(String productName) throws InterruptedException, IOException {
        Reports.createTest("Verify Login page is displayed after clicking on Buy now for an item");
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        homePage.searchProduct(productName);
        cartPage.setClickProduct();
        cartPage.multiFunctionHandle(4, 0);
    }

    //TestCase 12:Footer Text Validation
    @Test(dataProvider = "aboutFooterDP", dataProviderClass = DataSupply.class)
    public void verifyAboutFooter(String about) throws InterruptedException, IOException {
        Reports.createTest("Verify the ABOUT Footer of Flipkart");
        HomePage homePage = new HomePage(driver);
        homePage.verifyAbout(about);
    }

    //TestCase 13:Validate More Option in Home page
    @Test
    public void verifyMoreOption() throws IOException, InterruptedException {
        Reports.createTest("Verify the More option in Home Page");
        HomePage homePage = new HomePage(driver);
        homePage.verifyMore();
    }

    //TestCase 14:Validate Total Amount in Cart(Single Product)
    @Test(dataProvider = "singleProductDP", dataProviderClass = DataSupply.class)
    public void validateAmountSingle(String productName) throws InterruptedException, IOException {
        Reports.createTest("Validate Total Amount in Cart(Single Product)");
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        homePage.searchProduct(productName);
        homePage.selectProduct();
        cartPage.multiFunctionHandle(5, 0);
    }

    //TestCase 15: Validate Total Amount in Cart(Multi Product)
    @Test(dataProvider = "singleProductDP", dataProviderClass = DataSupply.class)
    public void validateAmountMultiple(String productName) throws InterruptedException, IOException {
        Reports.createTest("Validate Total Amount in Cart(Multiple Product)");
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        homePage.searchProduct(productName);
        homePage.selectProduct();
        cartPage.multiFunctionHandle(6, 0);
    }


    @AfterMethod
    public void closeBrowser() {
        Browser.closeBrowser(driver);
    }
}
