package ku.cs.condo.models.room;


import ku.cs.condo.exception.WrongAddRoomInfoException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Building {
    ArrayList<Room> rooms;

    public Building() {
        rooms = new ArrayList<Room>();
    }

    public ArrayList<Room> sortRoom(){
        ArrayList<Room> sorted = (ArrayList<Room>)rooms.clone();
        sorted.sort(Comparator.comparing(Room::getRoomNumber));
        sorted.sort(Comparator.comparing(Room::getBuilding));
        return sorted;
    }

    public boolean checkNewRoom(String building, String floor, String roomNumber) {
        String subString = roomNumber.substring(roomNumber.length()-2);
        int numberOfRoom = Integer.parseInt(subString);
        if (!building.equals(roomNumber.substring(0, 1)) || !floor.equals(roomNumber.substring(1, roomNumber.length()-2))
                || numberOfRoom > 10 ||numberOfRoom == 0) {
            throw new WrongAddRoomInfoException("The format is wrong");
        }

        for(Room r : rooms){
            if(r.equalsRoom(building, roomNumber)){
                throw new WrongAddRoomInfoException("This room is already taken");
            }
        }
        // pass
        return true;
    }

    public boolean findResident(String name, String roomNum ){
        for(Room room : rooms){
            if(room.findResident(name, roomNum)){
                return true;
            }
        }
        return false;
    }

    public void add(Room room){rooms.add(room);}

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
