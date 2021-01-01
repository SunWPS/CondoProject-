package ku.cs.condo.exception;

public class NoAccountException extends IllegalArgumentException{
    public NoAccountException(){
        super("We don't have this account.");
    }

    public NoAccountException(String message){
        super(message);
    }
}
