import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ChatHistory implements IterableByUser {
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

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return new Iterator<>() {
            private Iterator<Message> dequeIterator = messageHistory.iterator();
            private Message nextMessage = null;

            @Override
            public boolean hasNext() {
                while (dequeIterator.hasNext()) {
                    Message message = dequeIterator.next();
                    if (message.getSender().getName().equals(userToSearchWith.getName())) {
                        nextMessage = message;
                        return true;
                    }
                }
                nextMessage = null;
                return false;
            }

            @Override
            public Message next() {
                if (nextMessage == null && !hasNext()) {
                    return null;
                }
                Message message = nextMessage;
                nextMessage = null;
                return message;
            }

            @Override
            public void remove() {
                Iterator.super.remove();
            }
        };
    }

}