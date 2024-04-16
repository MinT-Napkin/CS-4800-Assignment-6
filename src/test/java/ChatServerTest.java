import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class ChatServerTest {

    private ChatServer chatServer;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setUp() {
        chatServer = new ChatServer();
        user1 = new User("user1");
        user2 = new User("user2");
        user3 = new User("user3");
        chatServer.registerUser(user1);
        chatServer.registerUser(user2);
        chatServer.registerUser(user3);
    }

    @Test
    public void testRegisterUser() {
        assertTrue(chatServer.getUsers().containsKey("user1"));
        assertTrue(chatServer.getUsers().containsKey("user2"));
        assertTrue(chatServer.getUsers().containsKey("user3"));
    }

    @Test
    public void testUnregisterUser() {
        chatServer.unregisterUser(user1);
        assertTrue(user1.getChatServer() == null);
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(user1, Arrays.asList(user2, user3), "Hello!");
        chatServer.sendMessage(message);
        assertEquals(1, user2.getChatHistory().getMessageHistory().size());
        assertEquals(1, user3.getChatHistory().getMessageHistory().size());
    }

    @Test
    public void testUndoMessage() {
        user1.sendMessage(Arrays.asList(user2), "Message 1");
        user1.sendMessage(Arrays.asList(user2), "Message 2");

        chatServer.undoMessage(user1);
        assertEquals(1, user2.getChatHistory().getMessageHistory().size());
    }

    @Test
    public void testBlockUser() {
        chatServer.blockUser(user1, user2);
        assertTrue(chatServer.getBlockedUsers().get(user1).contains(user2));
    }
}