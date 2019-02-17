package Pages;

import Utils.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends Page {

    By roomNameInputField = By.cssSelector("div.room-create.clearfix > input");
    By createRoomButton = By.cssSelector("div.room-create.clearfix > button");
    By roomList = By.cssSelector("div.room-list");
    By chatRoom = By.cssSelector("div.chat-room");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage createNewRoom(String name) {
        WebElement.sendKeys(driver, roomNameInputField,name);
        sleep(1000);
        WebElement.waitAndClickElement(driver,createRoomButton);
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(roomList));
        return this;
    }

    public ChatRoomPage enterToRoom(String r) {
        WebElement.waitAndClickElement(driver,By.xpath("//li[text()='"+ r +"']"));
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.numberOfElementsToBeMoreThan(chatRoom, 0));
        sleep(3000);
        return new ChatRoomPage(driver);

    }
}
