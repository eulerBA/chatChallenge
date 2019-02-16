package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void LoginUser(String username, String password)
    {
        this.driver.findElement(By.name("username")).sendKeys(username);
        this.driver.findElement(By.name("password")).sendKeys(username);
    }
}
