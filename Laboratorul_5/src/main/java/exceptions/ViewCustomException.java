package exceptions;

import java.io.IOException;

public class ViewCustomException extends IOException {
    public ViewCustomException(Exception ex) {
        super("Invalid Catalog Exception", ex);
    }
}