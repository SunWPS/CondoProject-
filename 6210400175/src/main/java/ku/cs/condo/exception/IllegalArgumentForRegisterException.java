package ku.cs.condo.exception;

public class IllegalArgumentForRegisterException extends IllegalArgumentException{
    public IllegalArgumentForRegisterException(){
        super("IllegalArgumentForRegisterException");
    }

    public IllegalArgumentForRegisterException(String message){
        super(message);
    }
}
