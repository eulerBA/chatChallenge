package Pages;

import Utils.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChatRoomPage extends Page {

    By chatRoomName = By.cssSelector("div.chat-room");
    By chatNumUsers = By.cssSelector("div.chat-num-users");
    By listUsernames = By.cssSelector("div.users-list div.name");
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

    public ArrayList<String> listUsernames() {
        ArrayList<String> allUsers = new ArrayList<String>();
        List<org.openqa.selenium.WebElement> elements = WebElement.findElements(driver,listUsernames);
        Iterator<org.openqa.selenium.WebElement> it = elements.iterator();
        org.openqa.selenium.WebElement e;
        while(it.hasNext()) {
            e = (org.openqa.selenium.WebElement) it.next();
            allUsers.add(e.getText());
        }
        return allUsers;
    }

    public void sendMessage(String msg){
        WebElement.sendKeys(driver, textArea, msg);
        WebElement.waitAndClickElement(driver,sendButton);
        sleep(3000);
    }

    public String getMessageByPosition(int pos){
        return WebElement.getText(driver, By.xpath("//div[@class='chat-history']//li["+pos+"]/div[not(@class='message-data')]"));
    }

    public int getAmountOfMessages() {
        return driver.findElements(By.xpath("//div[@class='chat-history']//li")).size();
    }

    public boolean isDuplicated(String msg) {
        List<org.openqa.selenium.WebElement> elements = WebElement.findElements(driver,By.xpath("//div[@class='chat-history']//li/div[not(@class='message-data')]"));
        int count = 0;
        if (elements.size() > 0) {
            Iterator<org.openqa.selenium.WebElement> it = elements.iterator();
            org.openqa.selenium.WebElement e;
             while(it.hasNext()) {
                 e = (org.openqa.selenium.WebElement) it.next();
                if (e.getText().equals(msg)) {
                    count++;
                    if (count > 1) return true;
                };
            }
        }
     return false;
    }
}
