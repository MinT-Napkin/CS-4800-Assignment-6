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

        //this calls the sendMessage method in ChatServer
        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "SUP GUYS");
        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "WANNA HEAR A JOKE?");
        userNimo.sendMessage(Arrays.asList(userCarriet), "Hey man... wanna hear a joke? :3");
        userChu.sendMessage(Arrays.asList(userNimo, userCarriet), "The hell you want?");
        userChu.sendMessage(Arrays.asList(userNimo, userCarriet), "Busy rn.");

        chatServer.blockUser(userCarriet, userNimo);
        userNimo.sendMessage(Arrays.asList(userCarriet), "herro?");

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
