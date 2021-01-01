package ku.cs.condo.models.account;

import ku.cs.condo.exception.WrongUsernameOrPasswordException;

import java.util.ArrayList;

public class AdminList implements AccountList {
    private ArrayList<AdminAccount> accounts;
    private  AdminAccount currentAccount;

    public AdminList() {accounts = new ArrayList<>();}

    @Override
    public int checkInputRePassword(String inputCurrentP, String inputNewP, String inputNewPAgain){
        return currentAccount.checkNewPassword(inputCurrentP, inputNewP, inputNewPAgain);
    }

    @Override
    public void applyRePassword(String newPassword){
        currentAccount.setPassword(newPassword);
        for (AdminAccount account : accounts) {
            if (account.equalsUsername(currentAccount.getUsername())) {
                account.setPassword(newPassword);
            }
        }
    }

    @Override
    public int loginAccount(String inputUsername, String inputPassword){
        for(AdminAccount acc : accounts){
            if(acc.equalsAccount(inputUsername, inputPassword)){
                currentAccount = acc;
                return 1;
            }
        }
        currentAccount = null;
        // wrong username or password
        throw new WrongUsernameOrPasswordException();
    }

    public void add(AdminAccount account){accounts.add(account);}

    public ArrayList<AdminAccount> getAccounts() {
        return accounts;
    }

    public AdminAccount getCurrentAccount(){
        return  currentAccount;
    }
}
