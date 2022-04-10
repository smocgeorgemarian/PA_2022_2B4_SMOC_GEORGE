package exceptions;

public class InvalidDictionary extends Exception{
    public InvalidDictionary(Exception ex) {
        super("Invalid dictionary file.", ex);
    }
}