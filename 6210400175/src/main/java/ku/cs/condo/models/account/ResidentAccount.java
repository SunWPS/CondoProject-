package ku.cs.condo.models.account;

import ku.cs.condo.exception.BothPassIsSameException;
import ku.cs.condo.exception.BothPassNotMatchException;
import ku.cs.condo.exception.WrongCurrentPasswordException;

public class ResidentAccount {
    private String username;
    private String password;
    private String name;
    private String roomNum;

    public ResidentAccount(String username, String password, String name, String roomNum) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.roomNum = roomNum;
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

    public boolean equalsUsername(String username){
        return this.username.equals(username);
    }

    public int checkEqualsAccount(String username, String password){
        if(this.username.equals(username)){
            if(this.password.equals(password)){return 1;}
            return 2;
        }
        return 3;
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

    public String getRoomNum() {
        return roomNum;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
