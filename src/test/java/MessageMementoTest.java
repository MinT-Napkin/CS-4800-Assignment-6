import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageMementoTest {

    @Test
    public void testGetContentState() {
        String content = "Test content";
        double timestamp = 1234567890.0;
        MessageMemento memento = new MessageMemento(content, timestamp);
        assertEquals(content, memento.getContentState());
    }

    @Test
    public void testGetTimestampState() {
        String content = "Test content";
        double timestamp = 1234567890.0;
        MessageMemento memento = new MessageMemento(content, timestamp);
        assertEquals(timestamp, memento.getTimestampState(), 0.0);
    }

    @Test
    public void testSetContentState() {
        String content = "Test content";
        double timestamp = 1234567890.0;
        MessageMemento memento = new MessageMemento(content, timestamp);
        String newContent = "New content";
        memento.setContentState(newContent);
        assertEquals(newContent, memento.getContentState());
    }

    @Test
    public void testSetTimestampState() {
        String content = "Test content";
        double timestamp = 1234567890.0;
        MessageMemento memento = new MessageMemento(content, timestamp);
        double newTimestamp = 9876543210.0;
        memento.setTimestampState(newTimestamp);
        assertEquals(newTimestamp, memento.getTimestampState(), 0.0);
    }
}