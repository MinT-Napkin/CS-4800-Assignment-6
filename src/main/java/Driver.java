import java.util.Arrays;
import java.util.Iterator;

public class Driver {
    public static void main(String[] args) {
        System.out.println("\n");

        ChatServer chatServer = new ChatServer();
        User userNimo = new User("Nimo");
        User userChu = new User("Chu");
        User userCarriet = new User("Carriet");

        chatServer.registerUser(userNimo);
        chatServer.registerUser(userChu);
        chatServer.registerUser(userCarriet);

        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "SUP GUYS");
        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "WANNA HEAR A JOKE?");
        userChu.sendMessage(Arrays.asList(userNimo), "The hell you want?");
        userChu.undoLastMessage();
        userChu.sendMessage(Arrays.asList(userNimo), "Busy rn.");
        userCarriet.sendMessage(Arrays.asList(userNimo), "Sure?");
        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "What do you call a mouse that likes mozzarella cheese?");
        userNimo.undoLastMessage();
        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "What do you call a rat that likes mozzarella cheese?");

        chatServer.blockUser(userChu, userNimo);
        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "herro?");
        userCarriet.sendMessage(Arrays.asList(userNimo), "uuuh, i dunno");
        userCarriet.undoLastMessage();
        userCarriet.sendMessage(Arrays.asList(userNimo), "cheesed rat?");

        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "A CHEESED RAT NGAHAHAHA--");
        userCarriet.sendMessage(Arrays.asList(userNimo), "ok");

        chatServer.unregisterUser(userNimo);
        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "herro? :3"); //should not appear

        System.out.println("\n");
        userNimo.viewChatHistory();
        userChu.viewChatHistory();
        userCarriet.viewChatHistory();

        System.out.println("\n");
        System.out.println("Nimo's User Filtered Chat History:");
        Iterator<Message> userIterator = userNimo.iterator(userNimo);
        while (userIterator.hasNext()) {
            Message message = userIterator.next();
            System.out.println(message);
        }
    }
}
