package Pages;

import Utils.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page {

    By loginUsernameField = By.cssSelector("form.login-form input[name='username']");
    By loginPasswordField = By.cssSelector("form.login-form input[name='password']");
    By loginButton = By.cssSelector("form.login-form button");
    By registerUsernameField = By.cssSelector("form.register-form input[name='username']");
    By registerPasswordField = By.cssSelector("form.register-form input[name='password']");
    By registerCreateButton = By.cssSelector("form.register-form button");
    By createNewUserLink = By.cssSelector("form.login-form a");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public HomePage login(String username, String password)
    {
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        WebElement.sendKeys(driver, loginUsernameField,username);
        WebElement.sendKeys(driver, loginPasswordField,password);
        WebElement.waitAndClickElement(driver,loginButton);

        (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBe(By.cssSelector("a.logout-btn"),"LOGOUT"));
        return new HomePage(driver);
    }

    public LoginPage createNewUser(String username, String password){
        WebElement.waitAndClickElement(driver,createNewUserLink);
        WebElement.sendKeys(driver, registerUsernameField,username);
        WebElement.sendKeys(driver, registerPasswordField,password);
        WebElement.waitAndClickElement(driver,registerCreateButton);
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBe(By.cssSelector("p.message.success"),"Your account has been created. Please log in."));
        return this;
    }
}
