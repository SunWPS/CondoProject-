package ku.cs.condo.models.storage;

public class Item {
    private String type;
    private String receiver ;
    private String building;
    private String floor;
    private String roomNum;
    private String size;
    private String dateTime;
    private String sender;
    private String imagePath;
    private String status;
    private String officerGet;

    private String officerOut;
    private String residentOut;
    private String dateTimeOut;

    public Item( String receiver, String building, String floor, String roomNum, String size, String dateTime, String sender,
                 String imagePath, String status, String officerGet, String officerOut, String residentOut, String dateTimeOut) {
        this.type = "Letter";
        this.receiver = receiver;
        this.building = building;
        this.floor = floor;
        this.roomNum = roomNum;
        this.size = size;
        this.dateTime = dateTime;
        this.sender = sender;
        this.imagePath = imagePath;
        this.officerGet = officerGet;
        this.status = status;
        this.officerOut = officerOut;
        this.residentOut = residentOut;
        this.dateTimeOut =  dateTimeOut;
    }

   public boolean checkStatus(){
        return status.equals("still");
   }

   public void changeStatus(String officerOut, String residentOut, String dateTimeOut){
        this.officerOut = officerOut;
        this.residentOut = residentOut;
        this.dateTimeOut = dateTimeOut;
        status = "Take Out";
   }

   public String getInformation(){
       return  type + "," + receiver + "," + building + "," + floor + ","
               + roomNum + "," +  size + "," +  dateTime + ","
               +  sender + "," +  imagePath + "," +  status + ","
               +  officerGet + "," +  officerOut + "," +  residentOut
               + "," +  dateTimeOut;
   }

    // get for show in table view and full information
    public String getType() {
        return type;
    }

    public String getFloor() {
        return floor;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getBuilding() {
        return building;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public String getSize() {
        return size;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getSender() {
        return sender;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getStatus() {
        return status;
    }

    public String getOfficerGet() {
        return officerGet;
    }

    public String getOfficerOut() {
        return officerOut;
    }

    public String getResidentOut() {
        return residentOut;
    }

    public String getDateTimeOut() {
        return dateTimeOut;
    }

    public void setType(String type) {
        this.type = type;
    }

}
