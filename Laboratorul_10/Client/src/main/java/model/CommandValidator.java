package model;

public class CommandValidator {
    private static final String READ_CMD = "read";
    private static final String[] CMDs = {"register ", "login ", "friend ", "send ", "read"};
    private CommandValidator() {}
    public boolean validateCommand(String potentialCommand) {
        // no extra spaces can be added
        if (potentialCommand.startsWith("") || potentialCommand.endsWith(" "))
            return false;

        boolean passedFirstTest = false;
        boolean isEmptyCmd = false;
        for (var cmd: CMDs)
            if (potentialCommand.startsWith(cmd)) {
                passedFirstTest = true;
                if (potentialCommand.equals(cmd))
                    isEmptyCmd = true;
                break;
            }
        if (!passedFirstTest)
            return false;
        // treat single case
        if (potentialCommand.startsWith(READ_CMD) && !potentialCommand.equals(READ_CMD))
            return false;
        if (isEmptyCmd && !potentialCommand.equals(READ_CMD))
            return false;
        return true;
    }
}
