package ku.cs.condo.models.account;

import ku.cs.condo.exception.DeactivatedAccountException;
import ku.cs.condo.exception.IllegalArgumentForRegisterException;
import ku.cs.condo.exception.WrongUsernameOrPasswordException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OfficerList implements AccountList {
    private ArrayList<OfficerAccount> accounts;
    private  OfficerAccount currentAccount;

    public OfficerList() {accounts = new ArrayList<>();}

    @Override
    public int checkInputRePassword(String inputCurrentP, String inputNewP, String inputNewPAgain){
        return currentAccount.checkNewPassword(inputCurrentP, inputNewP, inputNewPAgain);
    }

    @Override
    public void applyRePassword(String newPassword){
        currentAccount.setPassword(newPassword);
        for (OfficerAccount account : accounts) {
            if (account.equalsUsername(currentAccount.getUsername())) {
                account.setPassword(newPassword);
            }
        }
    }

    @Override
    public int loginAccount(String inputUsername, String inputPassword){
        for(OfficerAccount oac : accounts){
            if(oac.checkLoginStatus(inputUsername, inputPassword) == 1){
                // activate account
                currentAccount = oac;
                return 1;
            }
            else if(oac.checkLoginStatus(inputUsername, inputPassword) == -1){
                // deactivate account
                currentAccount = oac;
                currentAccount.setTryToLogin(currentAccount.getTryToLogin()+1);
                for (OfficerAccount account : accounts) {
                    if (account.getUsername().equals(currentAccount.getUsername())) {
                        account.setTryToLogin(currentAccount.getTryToLogin());
                        break;
                    }
                }
                currentAccount = null;
                throw new DeactivatedAccountException();
            }
        }
        // wrong username or password
        currentAccount = null;
        throw new WrongUsernameOrPasswordException();
    }

    public ArrayList<OfficerAccount> sortAccount(){
        ArrayList<OfficerAccount> sorted =  (ArrayList<OfficerAccount>)accounts.clone();
        sorted.sort(Comparator.comparing(OfficerAccount::getDateAndTime).reversed());
        return sorted;
    }

    public int checkOfficerAccount(String username, String name){
        for(OfficerAccount a: accounts){
            if(a.equalsUsername(username) && a.equalsName(name)){
                // both same
                return 0;
            }
            else if(a.equalsUsername(username)){
                // This username is already taken
                throw new IllegalArgumentForRegisterException("This username is already taken");
            }
            else if(a.equalsName(name)){
                // This name is already exists
                throw new IllegalArgumentForRegisterException("This name is already exists");
            }
        }
        // pass
        return 1;
    }

    public void updateOfficerAccount(OfficerAccount updatedAccount){
        for(OfficerAccount officer : accounts){
            if(officer.equalsUsername(updatedAccount.getUsername())){
                accounts.remove(officer);
                accounts.add(updatedAccount);
                break;
            }
        }
    }

    public void add(OfficerAccount account){accounts.add(account);}

    public ArrayList<OfficerAccount> getAccounts() {
        return accounts;
    }

    public OfficerAccount getCurrentAccount() {
        return currentAccount;
    }
}
