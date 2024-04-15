import java.util.Iterator;
import java.util.List;

public class User implements IterableByUser{
    private final String name;
    private ChatServer chatServer;
    private final ChatHistory chatHistory;
    private MessageMemento messageMemento;

    public User(String name) {
        this.name = name;
        this.chatHistory = new ChatHistory();
        this.messageMemento = new MessageMemento("", System.currentTimeMillis());
    }

    public String getName() {
        return name;
    }

    public void sendMessage(List<User> recipients, String content) {
        checkServerStatus();

        Message newMessage = new Message(this, recipients, content);

        save(newMessage.getMessageContent(), newMessage.getTimestamp());
        chatServer.sendMessage(newMessage);
        chatHistory.addMessage(newMessage);
    }

    public void receiveMessage(Message message) {
        System.out.println(name + " received a message: " + message);
        chatHistory.addMessage(message);
    }

    public void save(String message, double timestamp) {
        messageMemento.setContentState(message);
        messageMemento.setTimestampState(timestamp);
    }

    public MessageMemento getMessageMemento()
    {
        return messageMemento;
    }

    public ChatHistory getChatHistory()
    {
        return chatHistory;
    }

    public void undoLastMessage()
    {
        checkServerStatus();
        chatServer.undoMessage(this);
        System.out.println("\nUSER " + name + " UNDID THEIR MESSAGE.");
    }

    public void connectToChatServer(ChatServer chatServer)
    {
        this.chatServer = chatServer;
    }

    public void viewChatHistory() {
        System.out.println(name + "'s chat history: ");
        System.out.println(chatHistory);
    }

    @Override
    public Iterator<Message> iterator(User userToSearchWith) {
        return chatHistory.iterator(userToSearchWith);
    }

    private void checkServerStatus()
    {
        if (chatServer == null) {
            System.out.println("User " + name + " is not connected to a server.");
            return;
        }
    }
}
