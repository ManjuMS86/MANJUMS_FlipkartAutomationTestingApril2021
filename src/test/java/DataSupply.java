
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DataSupply {
    @DataProvider(name = "validateItemDP")
    public Object[][] validateItemDP() {
        return new Object[][]{{"iPhone 12 Pro Max","iPhone 12 Pro Max"}};
    }

    @DataProvider(name = "validateInvalidItemDP")
    public Object[][] validateInvalidItemDP() {
        return new Object[][]{{"1457543465643","Sorry, no results found!"}};
    }

    @DataProvider(name = "itemNameDP")
    public Object[][] itemNameDP() {
        return new Object[][]{{"iPhone"}};
    }

    @DataProvider(name = "invalidPincodeDP")
    public Object[][] invalidPincodeDP() {
        return new Object[][]{{"iPhone", 76975689}};
    }

    @DataProvider(name = "validPincodeDP")
    public Object[][] validPincodeDP() {
        return new Object[][]{{"mobile", 682030}};
    }

    @DataProvider(name = "filterDP")
    public Object[][] filterDP() {
        return new Object[][]{{"T-shirts"}};
    }

    @DataProvider(name = "registeredAddressDP")
    public Object[][] registeredAddressDP() {
        return new Object[][]{{"Registered Office Address:\n" +
                "Flipkart Internet Private Limited,\n" +
                "Buildings Alyssa, Begonia &\n" +
                "Clove Embassy Tech Village,\n" +
                "Outer Ring Road, Devarabeesanahalli Village,\n" +
                "Bengaluru, 560103,\n" +
                "Karnataka, India\n" +
                "CIN : U51109KA2012PTC066107\n" +
                "Telephone: 1800 202 9898"}};
    }

    @DataProvider(name = "topOffersDP")
    public Object[][] topOffersDP() {
        return new Object[][]{{"Deals of the Day,Fashion Top Deals"}};
    }

    @DataProvider(name = "loginDP")
    public Object[][] loginDP() {
        return new Object[][]{{"APPLE iPhone 11"}};
    }

    @DataProvider(name = "aboutFooterDP")
    public Object[][] aboutFooterDP() {
        return new Object[][]{{"ABOUT\n" + "Contact Us\n" + "About Us\n" + "Careers\n" + "Flipkart Stories\n" + "Press\n" + "Flipkart Wholesale"}};
    }

    @DataProvider(name = "singleProductDP")
    public Object[][] singleProductDP() {
        return new Object[][]{{"Mobile"}};

    }
    @DataProvider(name = "browserUrlDP")
    public Object[][] browserUrlDP() {
        return new Object[][]{{"Chrome","https://www.flipkart.com/"}};
    }
}
