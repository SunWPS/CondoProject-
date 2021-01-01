package ku.cs.condo.exception;

import java.util.SplittableRandom;

public class DeactivatedAccountException extends IllegalArgumentException{
    public DeactivatedAccountException(){
        super("Your account is deactivated. Please contact Admin.");
    }

    public DeactivatedAccountException(String message){
        super(message);
    }
}
