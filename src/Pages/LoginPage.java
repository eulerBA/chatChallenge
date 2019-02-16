package Pages;

import WebElements.MyWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page {

    By loginUsernameField = By.cssSelector("form.login-form input[name='username']");
    By loginPasswordField = By.cssSelector("form.login-form input[name='password']");
    By registerUsernameField = By.cssSelector("form.register-form input[name='username']");
    By registerPasswordField = By.cssSelector("form.register-form input[name='password']");
    By registerCreateButton = By.cssSelector("form.register-form button");
    By createNewUserLink = By.xpath("//a[contains(.,\'Create\')]");

    public LoginPage(){
        super();
    }

    public void loginUser(String username, String password)
    {
        this.driver.findElement(loginUsernameField).sendKeys(username);
        this.driver.findElement(loginPasswordField).sendKeys(username);
    }

    public LoginPage createNewUser(String username, String password){
        MyWebElement.click(driver,createNewUserLink);
        driver.findElement(registerUsernameField).sendKeys(username);
        driver.findElement(registerPasswordField).sendKeys(password);
        MyWebElement.click(driver,registerCreateButton);
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.textToBe(By.cssSelector("p.message.success"),"Your account has been created. Please log in."));
        return this;
    }
}
