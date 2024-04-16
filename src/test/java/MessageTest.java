import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTest {

    @Test
    public void testGetSender() {
        User sender = new User("Sender");
        List<User> recipients = Arrays.asList(new User("Recipient"));
        String content = "Test content";
        Message message = new Message(sender, recipients, content);
        assertEquals(sender, message.getSender());
    }

    @Test
    public void testGetRecipients() {
        User sender = new User("Sender");
        List<User> recipients = Arrays.asList(new User("Recipient1"), new User("Recipient2"));
        String content = "Test content";
        Message message = new Message(sender, recipients, content);
        assertEquals(recipients, message.getRecipients());
    }

    @Test
    public void testGetMessageContent() {
        User sender = new User("Sender");
        List<User> recipients = Arrays.asList(new User("Recipient"));
        String content = "Test content";
        Message message = new Message(sender, recipients, content);
        assertEquals(content, message.getMessageContent());
    }

    @Test
    public void testGetTimestamp() {
        User sender = new User("Sender");
        List<User> recipients = Arrays.asList(new User("Recipient"));
        String content = "Test content";
        Message message = new Message(sender, recipients, content);
        // Note: Since the timestamp is system-dependent, we can only check if it's greater than 0
        assertEquals(true, message.getTimestamp() > 0);
    }

    @Test
    public void testToString() {
        User sender = new User("Sender");
        List<User> recipients = Arrays.asList(new User("Recipient"));
        String content = "Test content";
        Message message = new Message(sender, recipients, content);

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formattedTime = sdf.format(date);

        // Assuming current time is 12:00
        String expected = "[" + formattedTime + "] Sender -> Test content";
        assertEquals(expected, message.toString());
    }
}
