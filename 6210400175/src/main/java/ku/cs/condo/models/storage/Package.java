package ku.cs.condo.models.storage;

public class Package extends Item{
    private String postOffice;
    private String trackingNumber;

    public Package(String receiver, String building, String floor, String roomNum, String size, String dateTime,
                   String sender, String imagePath, String status, String officerGet, String officerOut, String residentOut, String dateTimeOut,
                   String postOffice, String trackingNumber) {
        super(receiver, building, floor, roomNum, size, dateTime, sender, imagePath, status, officerGet, officerOut, residentOut, dateTimeOut);
        super.setType("Package");
        this.postOffice = postOffice;
        this.trackingNumber = trackingNumber;
    }

    public String getInformation(){
        return super.getInformation() +  "," + postOffice + "," + trackingNumber;
    }

    public String getPostOffice() {
        return postOffice;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }
}
