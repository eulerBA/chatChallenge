package test;

import Pages.LoginPage;
import Pages.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Test1 extends BaseTest{


    @Test()
    @Parameters({})
    public void basicChat() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.createNewUser("interview0","test0");
        loginPage.loginUser("interview0","test0");

        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys(Keys.chord(Keys.CONTROL, "n"));

        loginPage.createNewUser("interview2","test2");
        loginPage.loginUser("interview2","test2");

    }

}

