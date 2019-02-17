package Pages;

import org.openqa.selenium.WebDriver;

public class Page {

    WebDriver driver;

    public Page(WebDriver driver){
        this.driver=driver;
    }

    public Page navigateTo(String url) {
        this.driver.get(url);
        return this;
    }

    public void sleep(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
