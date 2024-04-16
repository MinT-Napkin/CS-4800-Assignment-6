import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private ChatServer chatServer;
    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        chatServer = new ChatServer();
        user1 = new User("user1");
        user2 = new User("user2");
        chatServer.registerUser(user1);
        chatServer.registerUser(user2);
    }

    @Test
    public void testSendMessage() {
        user1.sendMessage(Arrays.asList(user2), "Hello, User2!");

        ChatHistory user2ChatHistory = user2.getChatHistory();
        assertEquals(1, user2ChatHistory.getMessageHistory().size());
    }

    @Test
    public void testReceiveMessage() {
        Message message = new Message(user2, Arrays.asList(user1), "Hello, User1!");
        user1.receiveMessage(message);

        ChatHistory user1ChatHistory = user1.getChatHistory();
        assertEquals(1, user1ChatHistory.getMessageHistory().size());
    }

    @Test
    public void testSave() {
        User user = new User("User");
        user.save("Saved message", 123456789);

        MessageMemento messageMemento = user.getMessageMemento();
        assertEquals("Saved message", messageMemento.getContentState());
        assertEquals(123456789, messageMemento.getTimestampState());
    }

    @Test
    public void testUndoLastMessage() {

        user1.sendMessage(Arrays.asList(user2), "This message should be undone.");
        user1.undoLastMessage();

        ChatHistory user1ChatHistory = user1.getChatHistory();
        assertEquals(0, user1ChatHistory.getMessageHistory().size());
    }

    @Test
    public void testConnectToChatServer() {
        ChatServer chatServer = new ChatServer();
        User user = new User("User");
        user.connectToChatServer(chatServer);

        assertEquals(chatServer, user.getChatServer());
    }

    @Test
    public void testIterator() {
        user1.sendMessage(Arrays.asList(user2), "Message 1");
        user2.sendMessage(Arrays.asList(user1), "Message 2");
        user1.sendMessage(Arrays.asList(user2), "Message 3");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Iterator<Message> iterator = user1.iterator(user2);
        while (iterator.hasNext()) {
            Message message = iterator.next();
            System.out.print(message);
        }

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formattedTime = sdf.format(date);

        System.setOut(System.out);
        assertEquals("[" + formattedTime +"] user2 -> Message 2", outContent.toString());
    }
}