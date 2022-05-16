package exception;

public class NoUserLoggedIn extends Exception{
    public NoUserLoggedIn() {
        super("No user logged in");
    }
}
