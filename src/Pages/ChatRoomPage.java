package Pages;

import Utils.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChatRoomPage extends Page {

    By chatRoomName = By.cssSelector("div.chat-room");
    By chatNumUsers = By.cssSelector("div.chat-num-users");
    By textArea = By.cssSelector("textArea");
    By sendButton = By.cssSelector("div.chat-message.clearfix button");

    public ChatRoomPage(WebDriver driver) {
        super(driver);
    }

    public String getChatRoomName() {
        return WebElement.getText(driver, chatRoomName);

    }

    public String getChatNumUsers() {
        return WebElement.getText(driver,chatNumUsers);
    }

    public void sendMessage(String msg){
        WebElement.sendKeys(driver, textArea, msg);
        WebElement.waitAndClickElement(driver,sendButton);
        sleep(3000);
    }

    public String getMessageByPosition(int pos){
        return WebElement.getText(driver, By.xpath("//div[@class='chat-history']//li["+pos+"]/div[not(@class='message-data')]"));
    }
}
