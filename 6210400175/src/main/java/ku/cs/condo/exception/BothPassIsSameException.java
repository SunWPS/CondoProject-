package ku.cs.condo.exception;

public class BothPassIsSameException extends IllegalArgumentException{
    public BothPassIsSameException() {
        super("Your new password and current password is same");
    }

    public BothPassIsSameException(String message){
        super(message);
    }
}
