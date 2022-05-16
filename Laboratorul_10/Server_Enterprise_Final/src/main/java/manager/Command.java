package manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//register name: adds a new person to the social network;
//        login name: establishes a connection between the server and the client;
//        friend name1 name2 ... namek: adds friendship relations between the person that sends the command and other persons;
//        send message: sends a message to all friends.
//        read: reads the messages from the server.
public abstract class Command {
    public static List<String> getListOfArguments(String command) {
        List<String> initialList = Arrays.stream(command.split(" ")).toList();
        List<String> finalList = new ArrayList<>();
        for (int elIndex = 0; elIndex < initialList.size(); elIndex++)
            if (elIndex > 0)
                finalList.add(initialList.get(elIndex));
        return finalList;
    }

    public static String getBulkArgument(String command) {
        List<String> initialList = Arrays.stream(command.split(" ", 2)).toList();
        return initialList.get(1);
    }

    public static String getKeyword(String command) {
        List<String> returnList = Arrays.stream(command.split(" ")).toList();
        return returnList.get(0);
    }

    abstract boolean validate(List<String>commandArgs);
}
