
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

public class DataProviderTest {

    private static WebDriver driver;

    @DataProvider(name = "Authentication")

    public static Object[][] credentials() {

        // The number of times data is repeated, test will be executed the same no. of times

        // Here it will execute two times

        return new Object[][]{{"testuser_1", "Test@123"}, {"testuser_2", "Test@123"}};

    }

    // Here we are calling the Data Provider object with its Name

    @Test(dataProvider = "Authentication")

    public void test(String sUsername, String sPassword) {

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://semantic-ui.com/examples/login.html");

        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("password")).clear();

        // Argument passed will be used here as String Variable

        driver.findElement(By.name("email")).sendKeys(sUsername);
        driver.findElement(By.name("password")).sendKeys(sPassword);

        driver.findElement(By.xpath(".//div[contains(text(),'Login')]")).click();

        driver.quit();

    }

}

