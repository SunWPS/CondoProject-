package ku.cs.condo.models.account;

import ku.cs.condo.exception.BothPassIsSameException;
import ku.cs.condo.exception.BothPassNotMatchException;
import ku.cs.condo.exception.WrongCurrentPasswordException;

public class OfficerAccount {
    private String username;
    private String password;
    private String name;
    private String dateAndTime;
    private String status;
    private int tryToLogin;
    private String imagePath;

    public OfficerAccount(String username, String password, String name, String dateAndTime, String status, int tryToLogin, String imagePath) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dateAndTime = dateAndTime;
        this.status = status;
        this.tryToLogin = tryToLogin;
        this.imagePath = imagePath;
    }


    /**
     *  เช็ค ข้อมูลการสร้างรหัสผ่านใหม่
     * @param inputCurrentP     รหัสปัจจุบัน
     * @param inputNewP         รหัสใหม่
     * @param inputNewPAgain    ยืนยันรหัสใหม่
     * @return ส่งค่า 1 ถ้าผ่าน
     */
    public int checkNewPassword(String inputCurrentP, String inputNewP, String inputNewPAgain){
        if(inputCurrentP.equals(inputNewP) && inputCurrentP.equals(password)){
            // new password is same current password
            throw new BothPassIsSameException();
        }
        else if(!inputCurrentP.equals(password) && !inputNewP.equals(inputNewPAgain)){
            // inputCurrentPassword is not same currentPassword And inputNewP is not same inputNewAgain
            return 0;
        }
        else if(!inputCurrentP.equals(password)){
            // wrong current password
            throw new WrongCurrentPasswordException();
        }
        else if(!inputNewP.equals(inputNewPAgain)){
            // new password not same new password again
            throw new BothPassNotMatchException();
        }
        // everything is correct
        return 1;
    }

    public int checkLoginStatus(String username, String password){
        if(this.username.equals(username) && this.password.equals(password)){
            if (status.equals("activate")){
                // activate
                return 1;
            }
            // deactivate
            return -1;
        }
        // incorrect
        return 0;
    }

    public void changeStatus(){
        if(status.equals("activate")) {
            status = "deactivate";
        }
        else{
            status = "activate";
        }
        tryToLogin = 0;
    }

    public boolean equalsUsername(String username){
        return this.username.equals(username);
    }

    public boolean equalsName(String name){ return  this.name.equalsIgnoreCase(name);}


    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public void setTryToLogin(int tryToLogin) {
        this.tryToLogin = tryToLogin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public String getStatus() {
        return status;
    }

    public int getTryToLogin() {
        return tryToLogin;
    }

    public String getImagePath() {
        return imagePath;
    }

}
