package ku.cs.condo.models.storage;

public class Document extends Item{
    private String importantLv;

    public Document(String receiver, String building, String floor, String roomNum, String size,
                    String dateTime, String sender, String imagePath, String status, String officerGet,
                    String officerOut, String residentOut, String dateTimeOut, String importantLv) {
        super(receiver, building, floor, roomNum, size, dateTime, sender, imagePath, status, officerGet,officerOut, residentOut, dateTimeOut);
        super.setType("Document");
        this.importantLv = importantLv;
    }

    public String getInformation(){
        return  super.getInformation() + "," + importantLv;
    }

    public String getImportantLv() {
        return importantLv;
    }
}
