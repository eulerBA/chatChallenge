package Test;

import Pages.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    Map<Integer,WebDriver> drivers = new HashMap<Integer,WebDriver>();
    Map<Integer,Page> pages = new HashMap<Integer,Page>();
    public static String baseUrl = "https://chatchallengeproj.herokuapp.com/";
    public final String r = getRandomNumber();


    public void setup(int users){
        WebDriver driver;
        Page p;
        for(int i=1; i<=users; i++) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            p = new Page(driver);
            drivers.put(i,driver);
            pages.put(i,p);
            p.navigateTo(baseUrl);
        }
    }

    private String getRandomNumber(){
        return  new Long(new Date().getTime()).toString();
    }

    @AfterMethod
    public void tearDown(){
        if (drivers != null & drivers.size() != 0){
        for(int i=1; i<=drivers.size(); i++) {
            WebDriver d = drivers.get(i);
            d.quit();
        }
        }
    }

    public void sleep(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
