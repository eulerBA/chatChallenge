package test;

import Pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;
    Page page;
    public static String baseUrl = "https://chatchallengeproj.herokuapp.com/";

    @BeforeTest
    public void setup(){
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.page = new Page(driver);

        page.navigateTo(baseUrl);
    }

    @AfterTest
    public void tearDown(){
        this.driver.close();
    }
}
