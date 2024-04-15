import java.util.Deque;
import java.util.LinkedList;

public class ChatHistory {
    private final Deque<Message> messageHistory;

    public ChatHistory() {
        this.messageHistory = new LinkedList<>();
    }

    public void addMessage(Message message) {
        messageHistory.push(message);
    }

    public Message getLastMessage() {
        return messageHistory.peek();
    }

    public void removeUserLastMessage(MessageMemento messageToRemove) {
        for (Message message : messageHistory) {
            if (message.getMessageContent().equals(messageToRemove.getContentState())) {
                messageHistory.remove(message);
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Message message : messageHistory) {
            sb.append(message).append("\n");
        }
        return sb.toString();
    }
}