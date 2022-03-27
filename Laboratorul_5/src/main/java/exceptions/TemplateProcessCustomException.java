package exceptions;

public class TemplateProcessCustomException extends Exception{
    public TemplateProcessCustomException(Exception ex) {
        super("Template Process Custom Exception", ex);
    }
}
