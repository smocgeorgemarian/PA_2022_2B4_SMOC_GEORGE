package manager;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class StateManager {
    private static final String SRV_STOP = "Server stopped";
    private static final String SRV_EXIT = "Server received the request exit";

    private final Socket socket;
    private final EntityManager em;
    private String command;
    private String answer;
    // state
    private String lastUserLoggedIn = "";
    // flags
    private boolean hasToStop;
    private boolean hasToExit;

    public StateManager(Socket socket, EntityManager em) {
        this.socket = socket;
        this.em = em;
    }

    public void parseInput() throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        command = in.readLine();
    }

    public void setOutputPrintable() throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream());
        out.println(answer);
        out.flush();
    }

    private void setAllFlagsToFalse() {
        hasToStop = false;
        hasToExit = false;
    }

    public void execute() {
        setAllFlagsToFalse();
        String commandKeyword = Command.getKeyword(command);
        List<String> commandArgs = Command.getListOfArguments(command);
        boolean isUpdated = false;
        try {
            switch (commandKeyword) {
                case "stop" -> {
                    answer = SRV_STOP;
                    hasToStop = true;
                }
                case "exit" -> {
                    answer = SRV_EXIT;
                    hasToExit = true;
                }
                case "register" -> {
                    answer = "Server received the request " + command;
                    RegisterCommand registerCommand = new RegisterCommand();
                    registerCommand.execute(commandArgs, em);
                }
                case "login" -> {
                    LoginCommand loginCommand = new LoginCommand();
                    loginCommand.execute(commandArgs, em);
                    lastUserLoggedIn = commandArgs.get(0);
                }
                case "friend" -> {
                    FriendCommand friendCommand = new FriendCommand();
                    friendCommand.execute(commandArgs, em, lastUserLoggedIn);
                }
                case "message" -> {
                    MessageCommand messageCommand = new MessageCommand();
                    messageCommand.execute(Command.getBulkArgument(command), em, lastUserLoggedIn);
                }
                case "logout" -> {
                    LogoutCommand logoutCommand = new LogoutCommand();
                    logoutCommand.execute(commandArgs, em, lastUserLoggedIn);
                    lastUserLoggedIn = "";
                }
                default -> {
                    ReadCommand readCommand = new ReadCommand();
                    answer = readCommand.execute(commandArgs, em, lastUserLoggedIn);
                    isUpdated = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            answer = e.getMessage();
            return;
        }
        if (!isUpdated)
            answer = "[answer] Command " + commandKeyword + " executed successfully";
    }

    public boolean getHasToStop() {
        return hasToStop;
    }

    public boolean getHasToExit() {
        return hasToExit;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isLoggedIn() {
        return !lastUserLoggedIn.isEmpty();
    }
}
