package WebElements;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MyWebElement{

    public static void click(WebDriver driver, By by) {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
            driver.findElement(by).click();}
    catch (StaleElementReferenceException sere) {
                // simply retry finding the element in the refreshed DOM
                driver.findElement(by).click();
            }
    catch (TimeoutException toe) {
                //test.log(logStatus.Error, "Element identified by " + by.toString() + " was not clickable after 10 seconds");
            }
    }

    public void submit() {

    }

    public void sendKeys(CharSequence... keysToSend) {

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

    public String getText() {
        return null;
    }

    public List<WebElement> findElements(By by) {
        return null;
    }

    public WebElement findElement(By by) {
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
