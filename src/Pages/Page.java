package Pages;

import org.openqa.selenium.WebDriver;

public class Page {

    WebDriver driver;

    public Page(){
    };

    public Page(WebDriver driver){
        this.driver=driver;
    }

    public Page navigateTo(String url) {
        this.driver.get(url);
        return this;
    }
}
