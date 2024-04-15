import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//mediator
class ChatServer {
    private final Map<String, User> users;
    private final Map<User, List<User>> blockedUsers;

    public ChatServer() {
        this.users = new HashMap<>();
        this.blockedUsers = new HashMap<>();
    }

    public void registerUser(User user) {
        users.put(user.getName(), user);
        blockedUsers.put(user, new ArrayList<>());
    }

    public void unregisterUser(User user) {
        users.remove(user.getName());
        blockedUsers.remove(user.getName());
    }

    public void sendMessage(Message message) {
        for (User recipient : message.getRecipients()) {
            if (!blockedUsers.get(recipient).contains(message.getSender().getName())) {
                users.get(recipient.getName()).receiveMessage(message);
            }
            else
            {
                System.out.println("Message blocked");
            }
        }
    }

    public void undoMessage(User user) {
        MessageMemento msgMemento = user.getMessageMemento();

        for (User _user : users.values()) {
            ChatHistory userChatHistory = _user.getChatHistory();
            if (userChatHistory != null) {
                userChatHistory.removeUserLastMessage(msgMemento);
            }
        }

        msgMemento.setContentState(null); // prevents removing duplicate messages
    }

    public void blockUser(User user, User blockedUser) {
        blockedUsers.get(user.getName()).add(blockedUser);
    }

    public void unblockUser(User user, User unblockedUser) {
        blockedUsers.get(user.getName()).remove(unblockedUser);
    }
}
