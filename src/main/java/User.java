import java.util.List;

public class User {
    private final String name;
    private final ChatServer chatServer;
    private final ChatHistory chatHistory;
    private MessageMemento messageMemento;

    public User(String name, ChatServer chatServer) {
        this.name = name;
        this.chatServer = chatServer;
        this.chatHistory = new ChatHistory();
        this.messageMemento = new MessageMemento("", System.currentTimeMillis());
        chatServer.registerUser(this);
    }

    public String getName() {
        return name;
    }

    public void sendMessage(List<User> recipients, String content) {
        Message newMessage = new Message(this, recipients, content);

        save(newMessage.getMessageContent(), newMessage.getTimestamp());
        chatServer.sendMessage(newMessage);
        chatHistory.addMessage(newMessage);
    }

    public void receiveMessage(Message message) {
        System.out.println(name + " received a message: " + message);
        chatHistory.addMessage(message);
    }

    public void blockUser(User blockedUser) {
        chatServer.blockUser(this, blockedUser);
    }

    public void unblockUser(User unblockedUser) {
        chatServer.unblockUser(this, unblockedUser);
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
        chatServer.undoMessage(this);
        System.out.println("\nUSER " + name + " UNDID THEIR MESSAGE.");
    }

    public void viewChatHistory() {
        System.out.println(name + "'s chat history: ");
        System.out.println(chatHistory);
    }
}
