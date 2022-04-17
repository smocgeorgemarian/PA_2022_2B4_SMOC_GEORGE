package exceptions;


public class SleepException extends Exception{
    public SleepException(Exception ex) {
        super("Sleep error.", ex);
    }
}