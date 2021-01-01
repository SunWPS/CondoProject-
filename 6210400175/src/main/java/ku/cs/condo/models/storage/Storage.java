package ku.cs.condo.models.storage;

import ku.cs.condo.models.account.ResidentAccount;

import java.util.*;

public class Storage {
    ArrayList<Item> items;

    public Storage() {
        items = new ArrayList<Item>();
    }

    public ArrayList<Item> findItemByResident(ResidentAccount resident, String status){
        ArrayList<Item> foundItem = new ArrayList<>();
        for(Item item : items){
            if(item.getRoomNum().equals(resident.getRoomNum()) && item.getReceiver().equalsIgnoreCase(resident.getName()) && item.getStatus().equals(status)){
                foundItem.add(item);
            }
        }
        foundItem.sort(Comparator.comparing(Item::getDateTime).reversed());
        return foundItem;
    }

    public ArrayList<Item> sortStillItemsByDate(){
        ArrayList<Item> sorted =  (ArrayList<Item>)items.clone();

        // Max -> Min
        sorted.sort(Comparator.comparing(Item::getDateTime).reversed());
        ArrayList<Item> still = new ArrayList<>();
        for(Item item : sorted){
            if(item.checkStatus()){
                still.add(item);
            }
        }
        return still;
    }

    public ArrayList<Item> sortTakeOutItemsByDateOut(){
        ArrayList<Item> sorted =  (ArrayList<Item>)items.clone();

        // Max -> Min
        sorted.sort(Comparator.comparing(Item::getDateTimeOut).reversed());
        ArrayList<Item> takeOut = new ArrayList<>();
        for(Item item : sorted){
            if(!item.checkStatus()){
                takeOut.add(item);
            }
        }
        return takeOut;
    }

    public void add(Item item){items.add(item);}

    public ArrayList<Item> getItems() {
        return items;
    }
}
