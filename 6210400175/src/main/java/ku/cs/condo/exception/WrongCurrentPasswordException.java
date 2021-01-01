package ku.cs.condo.exception;

public class WrongCurrentPasswordException extends  IllegalArgumentException{
    public WrongCurrentPasswordException() {
        super("Sorry, Your password is incorrect");
    }

    public WrongCurrentPasswordException(String message) {
        super(message);
    }
}
