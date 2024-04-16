import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Iterator;

public class ChatHistoryTest {

    private ChatHistory chatHistory;
    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        ChatServer chatServer = new ChatServer();
        user1 = new User("user1");
        user2 = new User("user2");

        chatHistory = user1.getChatHistory();

        chatServer.registerUser(user1);
        chatServer.registerUser(user2);
    }

    @Test
    public void testAddMessage() {
        Message message = new Message(user1, Arrays.asList(user2), "Hello!");
        chatHistory.addMessage(message);
        assertTrue(chatHistory.getMessageHistory().contains(message));
    }

    @Test
    public void testGetLastMessage() {
        Message message1 = new Message(user1, Arrays.asList(user2), "Message 1");
        Message message2 = new Message(user1, Arrays.asList(user2), "Message 2");
        chatHistory.addMessage(message1);
        chatHistory.addMessage(message2);
        assertEquals(message2, chatHistory.getLastMessage());
    }

    @Test
    public void testRemoveUserLastMessage() {
        Message message1 = new Message(user1, Arrays.asList(user2), "Message 1");
        Message message2 = new Message(user1, Arrays.asList(user2), "Message 2");
        chatHistory.addMessage(message1);
        chatHistory.addMessage(message2);
        chatHistory.removeUserLastMessage(new MessageMemento("Message 2", System.currentTimeMillis()));
        assertFalse(chatHistory.getMessageHistory().contains(message2));
    }

    @Test
    public void testIterator() {
        Message message1 = new Message(user1, Arrays.asList(user2), "Message 1");
        Message message2 = new Message(user2, Arrays.asList(user1), "Message 2");
        Message message3 = new Message(user1, Arrays.asList(user2), "Message 3");
        chatHistory.addMessage(message1);
        chatHistory.addMessage(message2);
        chatHistory.addMessage(message3);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Iterator<Message> iterator = chatHistory.iterator(user2);
        while (iterator.hasNext()) {
            Message message = iterator.next();
            System.out.print(message);
        }

        System.setOut(System.out);
        assertEquals(message2.toString(), outContent.toString());

    }
}