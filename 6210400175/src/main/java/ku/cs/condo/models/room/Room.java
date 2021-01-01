package ku.cs.condo.models.room;

public class Room {
    private String building;
    private String floor;
    private String roomNumber;
    private String type;
    private String status;
    private String roomOwner;
    private String coOwner;

    public Room(String building, String floor, String roomNumber, String status, String roomOwner) {
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.type = "Simplex";
        this.status = status;
        this.roomOwner = roomOwner;
        this.coOwner = "";
    }

    public Room(String building, String floor, String roomNumber, String status, String roomOwner, String coOwner){
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.type = "Duplex";
        this.status = status;
        this.roomOwner = roomOwner;
        this.coOwner = coOwner;
    }

    public boolean findResident(String name, String roomNumber){
        return (getRoomOwner().equalsIgnoreCase(name) || coOwner.equalsIgnoreCase(name)) && this.roomNumber.equals(roomNumber);
    }

    public boolean equalsRoom(String building, String roomNumber){
        return this.building.equals(building) && this.roomNumber.equals(roomNumber);
    }

    public void checkOut(){
        status = "Available";
        roomOwner = "";
        if(type.equals("Duplex")) {
            coOwner = "";
        }
    }

    public String getBuilding() {
        return building;
    }

    public String getFloor() {
        return floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getRoomOwner() {
        return roomOwner;
    }

    public String getCoOwner() {
        return coOwner;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRoomOwner(String roomOwner) {
        this.roomOwner = roomOwner;
    }

    public void setCoOwner(String coOwner) {
        this.coOwner = coOwner;
    }
}
