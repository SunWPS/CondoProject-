package ku.cs.condo.models.account;

public interface AccountList {

     int checkInputRePassword(String inputCurrentP, String inputNewP, String inputNewPAgain);
     void applyRePassword(String newPassword);
     int loginAccount(String inputUsername, String inputPassword);

}
