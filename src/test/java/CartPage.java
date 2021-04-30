import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Set;

public class CartPage extends BasePage {

    private WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By addProduct = By.xpath("//button[@class=\"_2KpZ6l _2U9uOA _3v1-ww\"]");
    private By removeProduct = By.xpath("//*[@id=\"container\"]/div/div[2]/div/div/div[1]/div/div[2]/div/div[2]/div[2]/div[2]");
    private By removeButton = By.xpath("//div[@class=\"_3dsJAO _24d-qY FhkMJZ\"]");
    private By enterPincode = By.xpath("//input[@id=\"pincodeInputId\"]");
    private By enterPincodeCheck = By.xpath("//span[@class=\"_2P_LDn\"]");
    private By placeOrderAmount = By.xpath("//span[@class=\"_2-ut7f _1WpvJ7\"]");
    private By totalAmount = By.xpath("//div[@class=\"_1dqRvU\"]");
    private By product1 = By.xpath("//*[@id=\"container\"]/div/div[2]/div/div/div[1]/div/div[2]/div/div[1]/div[1]/span[1]");
    private By product2 = By.xpath("//*[@id=\"container\"]/div/div[2]/div/div/div[1]/div/div[3]/div/div[1]/div[1]/span[1]");
    private By searchTab = By.xpath("//input[@name=\"q\"]");
    private By searchButton = By.xpath("//button[@class=\"L0Z3Pu\"]");
    private By selectProduct = By.xpath("//div[@class=\"_4ddWXP\"]");
    private By gotoCart = By.xpath("//button[@class=\"_2KpZ6l _2U9uOA _3v1-ww\"]");
    private By searchProduct3 = By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div[3]/div/a[2]");
    private By addProduct2 = By.xpath("//button[@class=\"_2KpZ6l _2U9uOA _3v1-ww\"]");
    private By cartEmpty = By.xpath("//div[@class=\"_1AtVbE col-12-12\"]");
    private By pinMessage = By.xpath("//div[@class=\"_1NQ_ER\"]");
    private By validPin = By.xpath("//div[@class=\"_3XINqE\"]");
    private By loginOption = By.xpath("//span[@class=\"_1aULyb\"]");
    private By clickProduct = By.xpath("//div[@class=\"_4rR01T\"]");
    private By buyNow = By.xpath("//button[@class=\"_2KpZ6l _2U9uOA ihZ75k _3AWRsL\"]");
    private By searchProduct4 = By.xpath("//button[@class=\"_2KpZ6l _2U9uOA _3v1-ww\"]");
    private By selectProduct2 = By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div[2]/div/a[2]");

    //To handle different methods which involves switching of Windows
    public void multiFunctionHandle(int function, int pincode) throws InterruptedException, IOException {
        int window2 = 0;
        String parentWindow = driver.getWindowHandle();

        Set<String> childWindow = driver.getWindowHandles();

        for (String window : childWindow) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                switch (function) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        removeProduct();
                        break;
                    case 3:
                        enterDeliveryPincode(pincode);
                        break;
                    case 4:
                        buyNowProduct();
                        break;
                    case 5:
                        validateTotalAmountSingle();
                        break;
                    case 6:
                        click(addProduct);
                        isElementVisible(totalAmount);
                        window2 = 1;
                        break;
                    default:
                        System.out.println("Not in the list");
                }
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        if (window2 == 1) {
            parentWindow = driver.getWindowHandle();
            click(selectProduct2);
            Thread.sleep(3000);
            Set<String> childWindow2 = driver.getWindowHandles();
            for (String window : childWindow2) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    validateTotalAmountMultiple();
                    driver.close();
                }
            }
            driver.switchTo().window(parentWindow);
        }
    }

    //add products to the cart
    public void addProduct() throws IOException, InterruptedException {

        isElementVisible(addProduct);
        click(addProduct);
        Thread.sleep(2000);
        driver.manage().window().setPosition(new Point(3000, 0));
        Reports.extentTest.log(Status.INFO, "Product added to cart", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }

    //remove products from the cart
    public void removeProduct() throws IOException, InterruptedException {
        click(addProduct);
        try {
            click(removeProduct);
            isElementVisible(removeButton);
            click(removeButton);
            isElementVisible(cartEmpty);
            driver.manage().window().setPosition(new Point(3000, 0));
            Reports.extentTest.log(Status.PASS, "Product removed from cart", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        } catch (Exception e) {
            Reports.extentTest.log(Status.FAIL, "Product not removed from cart", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
        }
    }

    //Entering pincode and check the delivery in days
    public void enterDeliveryPincode(int pincode) throws IOException, InterruptedException {
        sendElements(enterPincode, String.valueOf(pincode));
        click(enterPincodeCheck);
        Thread.sleep(4000);
        Reports.extentTest.log(Status.INFO, " Pincode ", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenshot()).build());
    }


    public void setClickProduct() throws InterruptedException {
        click(clickProduct);
    }

    //Verify whether it asks for login or signup after entering buy now option
    public void buyNowProduct() throws IOException, InterruptedException {
        click(buyNow);
        isElementVisible(loginOption);
        String login = getText(loginOption);
        assertTrue(login, "LOGIN OR SIGNUP", "Login or signup option found ", "Login or signup option not found ");
    }

    //validate whether the Amount displayed in My cart and Total Amount matches for a single product addition
    public void validateTotalAmountSingle() throws InterruptedException, IOException {
        click(addProduct);
        String placeOrder = getText(placeOrderAmount);
        String totalAmountProduct = getText(totalAmount);
        assertEquals(placeOrder, totalAmountProduct, "Amount matching, total amount is ", "Amount not matching");
    }

    //validate whether the Amount displayed in My cart for the products and Total Amount matches for multiple product addition
    public void validateTotalAmountMultiple() throws InterruptedException, IOException {
        click(addProduct);
        isElementVisible(totalAmount);
        String product1Amount = getText(product1);
        product1Amount = product1Amount.replaceAll("[^0-9]", "");
        String product2Amount = getText(product2);
        product2Amount = product2Amount.replaceAll("[^0-9]", "");
        String total = getText(totalAmount);
        total = total.replaceAll("[^0-9]", "");
        int i = Integer.parseInt(product1Amount);
        int j = Integer.parseInt(product2Amount);
        int totalAmountProducts = i + j;
        int actualAmount = Integer.parseInt(total);
        assertEqualsInteger(actualAmount, totalAmountProducts, "Amount matching, total amount is ", "Amount not matching");
    }

}