package ku.cs.condo.models.account;

import ku.cs.condo.exception.NoAccountException;
import ku.cs.condo.exception.WrongCurrentPasswordException;

import java.util.ArrayList;

public class ResidentList implements AccountList {
    ArrayList<ResidentAccount> accounts;
    ResidentAccount currentAccount;

    public ResidentList(){
        accounts = new ArrayList<>();
    }

    @Override
    public int checkInputRePassword(String inputCurrentP, String inputNewP, String inputNewPAgain) {
        return currentAccount.checkNewPassword(inputCurrentP, inputNewP, inputNewPAgain);
    }

    @Override
    public void applyRePassword(String newPassword) {
        currentAccount.setPassword(newPassword);
        for (ResidentAccount account : accounts) {
            if (account.equalsUsername(currentAccount.getUsername())) {
                account.setPassword(newPassword);
            }
        }
    }

    @Override
    public int loginAccount(String inputUsername, String inputPassword){
        for(ResidentAccount acc : accounts){
            if(acc.checkEqualsAccount(inputUsername, inputPassword) == 1){
                currentAccount = acc;
                // complete
                return 1;
            }
            else if(acc.checkEqualsAccount(inputUsername, inputPassword) == 2){
                currentAccount = null;
                // wrong password
                throw new WrongCurrentPasswordException("The password is incorrect. Try again.");
            }
        }
        currentAccount = null;
        // don't have account
        throw new NoAccountException();
    }

    public boolean checkSameUsername(String username){
        for(ResidentAccount account : accounts){
            if(account.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public boolean checkSameName(String name){
        for(ResidentAccount account : accounts){
            if(account.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public void deleteAccount(String roomNum){
        accounts.removeIf(account -> account.getRoomNum().equals(roomNum));
    }

    public void add(ResidentAccount account){accounts.add(account);}

    public ArrayList<ResidentAccount> getAccounts() {
        return accounts;
    }

    public ResidentAccount getCurrentAccount() {
        return currentAccount;
    }
}
