package ku.cs.condo.exception;

public class WrongAddRoomInfoException extends IllegalArgumentException{
    public WrongAddRoomInfoException(){
        super("WrongAddRoomInfoException");
    }

    public WrongAddRoomInfoException(String message){
        super(message);
    }
}
