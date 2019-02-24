package Test;

import Pages.ChatRoomPage;
import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class Test1 extends BaseTest{

    private static String message1 = "MESSAGE_1";
    private static String message2 = "MESSAGE_2";
    private static String message3 = "MESSAGE_3";

    @Test(dataProvider = "dataProviderTest123")
    public void basicChatTest(int users, int rooms) {

        setupTest(users);
        Map<Integer, LoginPage> loginPages = createAllUsers();
        Map<Integer, HomePage> homePages = loginAllUsers(loginPages);
        createAllRooms(homePages.get(1), rooms);
        ChatRoomPage chatRoomPage1 = homePages.get(1).enterToRoom("Room 1 " +r);
        Assert.assertEquals(chatRoomPage1.getChatRoomName(), "Room 1 " +r);
        sleep(2000);
        Assert.assertEquals(chatRoomPage1.getChatNumUsers(), "1 User(s)");

        ChatRoomPage chatRoomPage2 = homePages.get(2).enterToRoom("Room 1 " +r);
        Assert.assertEquals(chatRoomPage2.getChatRoomName(), "Room 1 " +r);
        sleep(2000);
        Assert.assertEquals(chatRoomPage2.getChatNumUsers(), "2 User(s)");

        chatRoomPage1.sendMessage(message1);
        sleep(1000);
        chatRoomPage2.sendMessage(message2);
        sleep(1000);
        Assert.assertEquals(chatRoomPage1.getMessageByPosition(1), message1);
        Assert.assertEquals(chatRoomPage1.getMessageByPosition(2), message2);
        Assert.assertEquals(chatRoomPage2.getMessageByPosition(1), message1);
        Assert.assertEquals(chatRoomPage2.getMessageByPosition(2), message2);
    }

    @Test(dataProvider = "dataProviderTest123")
    public void privacyTest(int users, int rooms){
        setupTest(users);
        Map<Integer, LoginPage> loginPages = createAllUsers();
        Map<Integer, HomePage> homePages = loginAllUsers(loginPages);
        createAllRooms(homePages.get(1), rooms);
        ChatRoomPage chatRoomPage1 = homePages.get(1).enterToRoom("Room 1 " +r);
        sleep(3000);
        chatRoomPage1.sendMessage(message1);
        sleep(1000);
        chatRoomPage1.sendMessage(message2);
        sleep(1000);
        chatRoomPage1.sendMessage(message3);
        sleep(1000);
        Assert.assertEquals(chatRoomPage1.getAmountOfMessages(), 3);

        ChatRoomPage chatRoomPage2 = homePages.get(2).enterToRoom("Room 1 " +r);
        Assert.assertEquals(chatRoomPage2.getAmountOfMessages(), 0);
    }

    @Test(dataProvider = "dataProviderTest123")
    public void messageTrackingTest(int users, int rooms){
        setupTest(users);
        Map<Integer, LoginPage> loginPages = createAllUsers();
        Map<Integer, HomePage> homePages = loginAllUsers(loginPages);
        createAllRooms(homePages.get(1), rooms);
        ChatRoomPage chatRoomPage1 = homePages.get(1).enterToRoom("Room 1 " +r);
        ChatRoomPage chatRoomPage2 = homePages.get(2).enterToRoom("Room 1 " +r);

        chatRoomPage1.sendMessage(message1);
        Assert.assertFalse(chatRoomPage1.isDuplicated(message1), "Message: " + message1 + " is duplicated");

        chatRoomPage1.sendMessage(message1);
        Assert.assertTrue(chatRoomPage2.isDuplicated(message1),"Message: " + message1 + "is duplicated but could not find it");

        chatRoomPage2.sendMessage(message2);
        Assert.assertFalse(chatRoomPage2.isDuplicated(message2),"Message: " + message2 + "is duplicated");

        chatRoomPage2.sendMessage(message1);
        Assert.assertTrue(chatRoomPage2.isDuplicated(message1),"Message: " + message1 + "is duplicated but could not find it");
    }

    @Test(dataProvider = "dataProviderTest4")
    public void SimulateUserBehaviorTest(int users, int rooms){
        setupTest(users);
        Map<Integer, LoginPage> loginPages = createAllUsers();
        Map<Integer, HomePage> homePages = loginAllUsers(loginPages);
        createAllRooms(homePages.get(1), rooms);
        Map<Integer, ChatRoomPage> chatRoomPages = enterToRoomAllUsers(homePages, rooms);
    }



    /*DATA PROVIDERS*/
    @DataProvider(name = "dataProviderTest123")
    public Object[][] dataProvider123() {
        return new Object[][] { { 2, 1}};
    }

    @DataProvider(name = "dataProviderTest4")
    public Object[][] dataProvider4() {
        return new Object[][] { { 3, 1}};
    }
}

