package manager;

import exception.InvalidNumberOfArgs;

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
    private String commandKeyword;
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
        String command = in.readLine();
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

    public void execute() throws InvalidNumberOfArgs {
        setAllFlagsToFalse();
        switch (command) {
            case "stop":
                answer = SRV_STOP;
                hasToStop = true;
                break;
            case "exit":
                answer = SRV_EXIT;
                hasToExit = true;
                break;
            case "register":
                answer = "Server received the request " + command;
                RegisterCommand registerCommand = new RegisterCommand();
                List<String> commandArgs = registerCommand.getListOfArguments(command);
                registerCommand.execute(commandArgs, em);
        }
    }

    public boolean getHasToStop() {
        return hasToStop;
    }

    public boolean getHasToExit() {
        return hasToExit;
    }
}
