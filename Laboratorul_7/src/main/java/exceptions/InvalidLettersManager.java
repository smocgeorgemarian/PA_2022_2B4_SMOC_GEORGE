package exceptions;

public class InvalidLettersManager extends Exception{
    public InvalidLettersManager(Exception ex) {
        super("Invalid letters json file.", ex);
    }
}
