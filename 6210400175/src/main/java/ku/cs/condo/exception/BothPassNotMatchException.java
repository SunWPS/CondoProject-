package ku.cs.condo.exception;

public class BothPassNotMatchException extends IllegalArgumentException{
    public BothPassNotMatchException(){
        super("Your input doesn't match new password");
    }
    
    public BothPassNotMatchException(String message){
        super(message);
    }
}
