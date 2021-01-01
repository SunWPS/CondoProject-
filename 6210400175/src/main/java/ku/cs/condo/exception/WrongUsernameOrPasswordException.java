package ku.cs.condo.exception;

public class WrongUsernameOrPasswordException extends IllegalArgumentException{
    public WrongUsernameOrPasswordException(){
        super("Username or password is incorrect. Try again.");
    }

    public WrongUsernameOrPasswordException(String message){
        super(message);
    }

}
