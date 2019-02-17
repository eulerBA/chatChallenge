package Test;

import Pages.ChatRoomPage;
import Pages.HomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Test1 extends BaseTest{

    private String username1 = "interview1";
    private String password1 = "Test1";
    private String username2 = "interview2";
    private String password2 = "Test2";

    private static String message1 = "MESSAGE_1";
    private static String message2 = "MESSAGE_2";

    @Test()
    public void basicChat() {

        LoginPage loginPage1 = new LoginPage(super.driver1);
        loginPage1.createNewUser(username1+r, password1);
        HomePage homePage1 = loginPage1.login(username1+r, password1);
        homePage1.createNewRoom(r);
        ChatRoomPage chatRoomPage1 = homePage1.enterToRoom(r);
        Assert.assertEquals(chatRoomPage1.getChatRoomName(), r);
        sleep(2000);
        Assert.assertEquals(chatRoomPage1.getChatNumUsers(), "1 User(s)");

        LoginPage loginPage2 = new LoginPage(super.driver2);
        loginPage2.createNewUser(username2+r,password2);
        HomePage homePage2 = loginPage2.login(username2+r,password2);
        ChatRoomPage chatRoomPage2 = homePage2.enterToRoom(r);
        Assert.assertEquals(chatRoomPage2.getChatRoomName(), r);
        sleep(2000);
        Assert.assertEquals(chatRoomPage2.getChatNumUsers(), "2 User(s)");

        chatRoomPage1.sendMessage(message1);
        sleep(1000);
        chatRoomPage1.sendMessage(message2);

        Assert.assertEquals(chatRoomPage1.getMessageByPosition(1), message1);
        Assert.assertEquals(chatRoomPage1.getMessageByPosition(2), message2);
        Assert.assertEquals(chatRoomPage2.getMessageByPosition(1), message1);
        Assert.assertEquals(chatRoomPage2.getMessageByPosition(2), message2);
    }

}

