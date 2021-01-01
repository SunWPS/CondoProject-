package ku.cs.condo.services.etcService;

import ku.cs.condo.models.room.Building;
import ku.cs.condo.models.room.Room;

import java.io.*;

public class BuildingFileDataSource {
    private String fileDirectoryName;
    private String fileName;
    private Building building;

    public BuildingFileDataSource(String fileDirectoryName, String fileName){
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if(!file.exists()){
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e){
                System.out.println("Can't create " + filePath );
            }
        }
    }

    private void readData() throws  IOException{
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while((line = reader.readLine()) != null){
            String[] data = line.split(",");
            if(data[3].trim().equals("Simplex")){
                Room room = new Room(data[0].trim(), data[1].trim(), data[2].trim(), data[4].trim(),
                        data[5].equals("NaN") ? "": data[5].trim());
                building.add(room);
            }
            else if(data[3].trim().equals("Duplex")) {
                Room room = new Room(data[0].trim(), data[1].trim(), data[2].trim(), data[4].trim(),
                        data[5].equals("NaN") ? "": data[5].trim(), data[6].equals("NaN") ? "": data[6].trim());
                building.add(room);
            }
        }
        reader.close();
    }

    public Building getBuildingData(){
        try{
            building = new Building();
            readData();
        }catch (FileNotFoundException e){
            System.err.println(this.fileName + "not found");
        } catch (IOException e) {
            System.err.println("IOException from reading" + this.fileName);
        }
        return building;
    }

    public void setBuildingData(Building building){
        String filePath = fileDirectoryName + File.separator  + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(Room room : building.getRooms()){
                String line = room.getBuilding() + ","
                        + room.getFloor() + ","
                        + room.getRoomNumber() + ","
                        + room.getType() + ","
                        + room.getStatus() + ",";
                if(room.getRoomOwner().equals("")){
                    line += "NaN";
                }
                else{ line += room.getRoomOwner();}

                if(room.getType().equals("Duplex")){
                    if(room.getCoOwner().equals("")){ line += "," + "NaN"; }
                    else{ line += "," + room.getCoOwner();}
                }

                writer.append(line);
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            System.err.println("Cannot write " + filePath);
        }
    }

    public void appendRoom(String inputBuilding, String inputFloor, String inputRoomNumber, String inputType){
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            String line = inputBuilding + ","
                    + inputFloor + ","
                    + inputRoomNumber + ","
                    + inputType + ","
                    + "Available" + ","
                    + "NaN";
            if(inputType.equals("Duplex")){
                line += "," + "NaN";
            }

            writer.append(line);
            writer.newLine();
            writer.close();
        }catch (IOException e){
            System.err.println("Cannot write " + filePath);
        }
    }
}
