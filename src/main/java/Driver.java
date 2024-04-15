import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
        System.out.println("\n");

        ChatServer chatServer = new ChatServer();
        User userNimo = new User("Nimo", chatServer);
        User userChu = new User("Chu", chatServer);
        User userCarriet = new User("Carriet", chatServer);

        chatServer.registerUser(userNimo);
        chatServer.registerUser(userChu);
        chatServer.registerUser(userCarriet);

        //note that this calls the sendMessage method in ChatServer
        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "SUP GUYS");
        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "WANNA HEAR A JOKE?");
        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "WANNA HEAR A JOKE?");

        userNimo.undoLastMessage();

        userNimo.sendMessage(Arrays.asList(userCarriet), "Hey man... wanna hear a joke? :3");
        userNimo.sendMessage(Arrays.asList(userCarriet), "Hey man... wanna hear a joke? :3");

        userNimo.undoLastMessage();

//        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "Hello Chu and Carriet!");
//        userChu.sendMessage(Arrays.asList(userNimo), "Hi Nimo!");
//
//        userNimo.undoLastMessage();
//
//        userCarriet.blockUser(userNimo);
//        userNimo.sendMessage(Arrays.asList(userChu, userCarriet), "This message should be blocked for Alice.");
//

        System.out.println("\n");
        userNimo.viewChatHistory();
        userChu.viewChatHistory();
        userCarriet.viewChatHistory();
    }
}
