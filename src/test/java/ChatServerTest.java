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
        user1 = new User("Alice");
        user2 = new User("Bob");
        user3 = new User("Eve");

        chatServer.registerUser(user1);
        chatServer.registerUser(user2);
        chatServer.registerUser(user3);
    }

    @Test
    public void testSendMessage() {
        assertEquals("false name", user1.getName());
    }
}
