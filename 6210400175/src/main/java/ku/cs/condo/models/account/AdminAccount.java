package ku.cs.condo.models.account;

import ku.cs.condo.exception.BothPassIsSameException;
import ku.cs.condo.exception.BothPassNotMatchException;
import ku.cs.condo.exception.WrongCurrentPasswordException;

public class AdminAccount {
    private String username;
    private String password;

    public AdminAccount(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean equalsAccount(String username, String password){
        return this.username.equals(username) && this.password.equals(password);
    }

    public boolean equalsUsername(String username){
        return this.username.equals(username);
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
