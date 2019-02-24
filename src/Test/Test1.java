package Test;

import Pages.ChatRoomPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.Page;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test1 extends BaseTest{

    private String username1 = "interview1";
    private String password1 = "Test1";
    private String username2 = "interview2";
    private String password2 = "Test2";

    private static String message1 = "MESSAGE_1";
    private static String message2 = "MESSAGE_2";
    private static String message3 = "MESSAGE_3";

//    @Test()
//    public void basicChatTest() {
//
//        LoginPage loginPage1 = new LoginPage(super.driver1);
//        loginPage1.createNewUser(username1+r, password1);
//        HomePage homePage1 = loginPage1.login(username1+r, password1);
//        homePage1.createNewRoom(r);
//        ChatRoomPage chatRoomPage1 = homePage1.enterToRoom(r);
//        Assert.assertEquals(chatRoomPage1.getChatRoomName(), r);
//        sleep(2000);
//        Assert.assertEquals(chatRoomPage1.getChatNumUsers(), "1 User(s)");
//
//        LoginPage loginPage2 = new LoginPage(super.driver2);
//        loginPage2.createNewUser(username2+r,password2);
//        HomePage homePage2 = loginPage2.login(username2+r,password2);
//        ChatRoomPage chatRoomPage2 = homePage2.enterToRoom(r);
//        Assert.assertEquals(chatRoomPage2.getChatRoomName(), r);
//        sleep(2000);
//        Assert.assertEquals(chatRoomPage2.getChatNumUsers(), "2 User(s)");
//
//        chatRoomPage1.sendMessage(message1);
//        sleep(1000);
//        chatRoomPage2.sendMessage(message2);
//        sleep(1000);
//        Assert.assertEquals(chatRoomPage1.getMessageByPosition(1), message1);
//        Assert.assertEquals(chatRoomPage1.getMessageByPosition(2), message2);
//        Assert.assertEquals(chatRoomPage2.getMessageByPosition(1), message1);
//        Assert.assertEquals(chatRoomPage2.getMessageByPosition(2), message2);
//    }
//
//    @Test
//    public void privacyTest(){
//        LoginPage loginPage1 = new LoginPage(super.driver1);
//        HomePage homePage1 = loginPage1.login(username1+r, password1);
//        ChatRoomPage chatRoomPage1 = homePage1.enterToRoom(r);
//        sleep(3000);
//        chatRoomPage1.sendMessage(message1);
//        sleep(1000);
//        chatRoomPage1.sendMessage(message2);
//        sleep(1000);
//        chatRoomPage1.sendMessage(message3);
//        sleep(1000);
//        Assert.assertEquals(chatRoomPage1.getAmountOfMessages(), 3);
//
//        LoginPage loginPage2 = new LoginPage(super.driver2);
//        HomePage homePage2 = loginPage2.login(username2+r, password2);
//        ChatRoomPage chatRoomPage2 = homePage2.enterToRoom(r);
//        Assert.assertEquals(chatRoomPage2.getAmountOfMessages(), 0);
//    }
//
//    @Test
//    public void messageTrackingTest(){
//        LoginPage loginPage1 = new LoginPage(super.driver1);
//        HomePage homePage1 = loginPage1.login(username1+r, password1);
//        ChatRoomPage chatRoomPage1 = homePage1.enterToRoom(r);
//
//        LoginPage loginPage2 = new LoginPage(super.driver2);
//        HomePage homePage2 = loginPage2.login(username2+r, password2);
//        ChatRoomPage chatRoomPage2 = homePage2.enterToRoom(r);
//
//        chatRoomPage1.sendMessage(message1);
//        Assert.assertFalse(chatRoomPage1.isDuplicated(message1), "Message: " + message1 + " is duplicated");
//
//        chatRoomPage1.sendMessage(message1);
//        Assert.assertTrue(chatRoomPage2.isDuplicated(message1),"Message: " + message1 + "is duplicated but could not find it");
//
//        chatRoomPage2.sendMessage(message2);
//        Assert.assertFalse(chatRoomPage2.isDuplicated(message2),"Message: " + message2 + "is duplicated");
//
//        chatRoomPage2.sendMessage(message1);
//        Assert.assertTrue(chatRoomPage2.isDuplicated(message1),"Message: " + message1 + "is duplicated but could not find it");
//    }

    @Test(dataProvider = "dataProviderTest4")
    public void SimulateUserBehaviorTest(int users, int rooms){
        setup(users);
        Map<Integer, LoginPage> loginPages = createAllUsers();
        Map<Integer, HomePage> homePages = loginAllUsers(loginPages);
        createAllRooms(homePages.get(1), rooms);
        Map<Integer, ChatRoomPage> chatRoomPages = enterToRoomAllUsers(homePages, rooms);
    }

    private Map<Integer,ChatRoomPage> enterToRoomAllUsers(Map<Integer, HomePage> homePages, int rooms) {
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

    private void createAllRooms(HomePage homePage, int rooms) {
        for (int i = 1; i <= rooms; i++) {
            homePage.createNewRoom("Room "+i+ " " +r);
        }
    }

    private Map<Integer,HomePage> loginAllUsers(Map<Integer, LoginPage> loginPages) {
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

    private Map<Integer,LoginPage> createAllUsers() {
        Map<Integer, LoginPage> loginPages = new HashMap<Integer, LoginPage>();
        LoginPage lp;
        for (int i = 1; i <= drivers.size(); i++) {
            lp = new LoginPage(drivers.get(i));
            lp.createNewUser("User " + i + " " + r, "pass123");
            loginPages.put(i, lp);
        }
    return loginPages;
    }


    /*DATA PROVIDERS*/
    @DataProvider(name = "dataProviderTest4")
    public Object[][] dataProvider() {
        return new Object[][] { { 3, 1}};
    }


}

