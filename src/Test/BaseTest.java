package Test;

import Pages.ChatRoomPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    Map<Integer,WebDriver> drivers = new HashMap<Integer,WebDriver>();
    Map<Integer,Page> pages = new HashMap<Integer,Page>();
    public static String baseUrl = "https://chatchallengeproj.herokuapp.com/";
    public final String r = getRandomNumber();

    public void setupTest(int users){
        WebDriver driver;
        Page p;
        for(int i=1; i<=users; i++) {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            p = new Page(driver);
            drivers.put(i,driver);
            pages.put(i,p);
            p.navigateTo(baseUrl);
        }
    }

    private String getRandomNumber(){
        return  new Long(new Date().getTime()).toString();
    }

    @AfterMethod
    public void tearDown(){
        if (drivers != null & drivers.size() != 0){
        for(int i=1; i<=drivers.size(); i++) {
            WebDriver d = drivers.get(i);
            d.quit();
        }
        }
    }

    public void sleep(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected Map<Integer, ChatRoomPage> enterToRoomAllUsers(Map<Integer, HomePage> homePages, int rooms) {
        Map<Integer, ChatRoomPage> chatRoomPages = new HashMap<Integer, ChatRoomPage>();
        ChatRoomPage crp;
        ArrayList<String> listUsernames;
        int random = 0;
        for(int i=1; i<=homePages.size(); i++) {
            int index = (int) (Math.random() * (rooms - 1)) + 1;
            crp = homePages.get(i).enterToRoom("Room "+index+ " " +r);
            chatRoomPages.put(i,crp);
            listUsernames = crp.listUsernames();
            for(int x=0; x<listUsernames.size(); x++)
                if (!("User " + i + " " + r).equals(listUsernames.get(x))){
                    crp.sendMessage("HELLO " + listUsernames.get(x));
                }

        }
        return chatRoomPages;
    }

    protected void createAllRooms(HomePage homePage, int rooms) {
        for (int i = 1; i <= rooms; i++) {
            homePage.createNewRoom("Room "+i+ " " +r);
        }
    }

    protected Map<Integer,HomePage> loginAllUsers(Map<Integer, LoginPage> loginPages) {
        Map<Integer, HomePage> homePages = new HashMap<Integer, HomePage>();
        LoginPage lp;
        HomePage hp;
        for (int i = 1; i <= loginPages.size(); i++) {
            lp = loginPages.get(i);
            hp = lp.login("User " + i + " " + r,"pass123");
            homePages.put(i,hp);
        }
        return homePages;
    }

    protected Map<Integer,LoginPage> createAllUsers() {
        Map<Integer, LoginPage> loginPages = new HashMap<Integer, LoginPage>();
        LoginPage lp;
        for (int i = 1; i <= drivers.size(); i++) {
            lp = new LoginPage(drivers.get(i));
            lp.createNewUser("User " + i + " " + r, "pass123");
            loginPages.put(i, lp);
        }
        return loginPages;
    }
}
