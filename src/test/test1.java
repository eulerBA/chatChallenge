package test;

import Pages.LoginPage;
import Pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class test1 extends BaseTest{


    @Test
    public void createUserTest() {
        LoginPage loginPage = new LoginPage(driver);

    }

}

