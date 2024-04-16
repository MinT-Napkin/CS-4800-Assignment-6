import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class Message {
    private User sender;
    private List<User> recipients;
    private String content;
    private double timestamp;

    public Message(User sender, List<User> recipients, String message) {
        this.sender = sender;
        this.recipients = recipients;
        this.content = message;
        this.timestamp = System.currentTimeMillis();
    }

    public User getSender() {
        return sender;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public String getMessageContent() {
        return content;
    }

    public double getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        Date date = new Date((long) timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formattedTime = sdf.format(date);

        return "[" + formattedTime + "] " + sender.getName() + " -> " + content;
    }
}
