package Test;

import Pages.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver1;
    WebDriver driver2;
    Page page1;
    Page page2;
    public static String baseUrl = "https://chatchallengeproj.herokuapp.com/";
    public String r;


    @BeforeTest
    public void setup(){
         driver1 = new ChromeDriver();
         driver2 = new ChromeDriver();
         driver1.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         this.page1 = new Page(driver1);
         this.page2 = new Page(driver2);
         setRandomNumber();
         page1.navigateTo(baseUrl);
         page2.navigateTo(baseUrl);
    }

    private void setRandomNumber(){
        this.r =  new Long(new Date().getTime()).toString();
    }

    @AfterTest
    public void tearDown(){
        this.driver1.quit();
        this.driver2.quit();
    }

    public void sleep(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
