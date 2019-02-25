package Utils;

import com.sun.istack.internal.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebElement {

    public static void waitAndClickElement(WebDriver driver, By by) {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
            driver.findElement(by).click();}
    catch (StaleElementReferenceException sere) {
                driver.findElement(by).click();
            }
    catch (TimeoutException toe) {
                System.out.println("Element identified by " + by.toString() + " was not clickable after 10 seconds");
            }
    }

    public void submit() {

    }

    public static void sendKeys(WebDriver driver, By by, CharSequence... keysToSend) {
        try{
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(keysToSend);
        } catch (IllegalArgumentException iae){

        }catch (TimeoutException toe) {
            System.out.println("Element identified by " + by.toString() + " was not visible after 10 seconds");
        }
    }

    public void clear() {

    }

    public String getTagName() {
        return null;
    }

    public String getAttribute(String name) {
        return null;
    }

    public boolean isSelected() {
        return false;
    }

    public boolean isEnabled() {
        return false;
    }

    public static String getText(WebDriver driver, By by) {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(by));
            return driver.findElement(by).getText();}
        catch (StaleElementReferenceException sere) {
            return driver.findElement(by).getText();
        }
        catch (TimeoutException toe) {
            System.out.println("Element identified by " + by.toString() + " could not get text after 10 seconds");
        }
        return driver.findElement(by).getText();
    }

    public static List<org.openqa.selenium.WebElement> findElements(WebDriver driver, By by) {
        return driver.findElements(by);
    }

    public org.openqa.selenium.WebElement findElement(By by) {
        return null;
    }

    public boolean isDisplayed() {
        return false;
    }

    public Point getLocation() {
        return null;
    }

    public Dimension getSize() {
        return null;
    }

    public Rectangle getRect() {
        return null;
    }

    public String getCssValue(String propertyName) {
        return null;
    }

    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return null;
    }
}
