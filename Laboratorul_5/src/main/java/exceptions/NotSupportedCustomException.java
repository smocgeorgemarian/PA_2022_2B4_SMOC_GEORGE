package exceptions;

public class NotSupportedCustomException extends Exception{
    public NotSupportedCustomException() {
        super("Not suported data type for extracting metadata");
    }
}
