package ku.cs.condo.services.etcService;

import ku.cs.condo.models.storage.Document;
import ku.cs.condo.models.storage.Item;
import ku.cs.condo.models.storage.Package;
import ku.cs.condo.models.storage.Storage;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StorageFileDataSource {
    private String fileDirectoryName;
    private String fileName;
    private Storage storage;

    public StorageFileDataSource(String fileDirectoryName, String fileName){
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
            if(data[0].trim().equals("Letter")){
                Item item = new Item(data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(),
                                     data[5].trim(), data[6].trim(), data[7].trim(),  data[8].trim(),  data[9].trim(),
                                     data[10].trim(),  data[11].trim(),  data[12].trim(),  data[13].trim());
                storage.add(item);
            }
            else if(data[0].trim().equals("Document")){
                Item item = new Document(data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(),
                                         data[5].trim(), data[6].trim(), data[7].trim(), data[8].trim(), data[9].trim(),
                                         data[10].trim(), data[11].trim(), data[12].trim(), data[13].trim(), data[14].trim());
                storage.add(item);
            }
            else if(data[0].trim().equals("Package")){
                Item item = new Package(data[1].trim(), data[2].trim(), data[3].trim(), data[4].trim(),
                                        data[5].trim(), data[6].trim(), data[7].trim(), data[8].trim(), data[9].trim(),
                                        data[10].trim(), data[11].trim(), data[12].trim(), data[13].trim(),
                                        data[14].trim(), data[15].trim());
                storage.add(item);
            }
        }
        reader.close();
    }

    public Storage getStorageData(){
        try{
            storage = new Storage();
            readData();
        }catch (FileNotFoundException e){
            System.err.println(this.fileName + "not found");
        } catch (IOException e) {
            System.err.println("IOException from reading" + this.fileName);
        }
        return storage;
    }

    public void setStorageData(Storage storage){
        String filePath = fileDirectoryName + File.separator  + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(Item item : storage.getItems()){
                String line = item.getInformation();

                writer.append(line);
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            System.err.println("Cannot write " + filePath);
        }
    }

    public void appendItem(String name, String building, String floor, String roomNum, String size,
                           String sender, String imagePath, String officerGet){
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String line = "Letter" + ","
                    + name + ","
                    + building + ","
                    + floor + ","
                    + roomNum + ","
                    + size + ","
                    + dateFormat.format(date) + ","
                    + sender + ","
                    + imagePath + ","
                    + "still" + ","
                    + officerGet + ","
                    + "NaN" + ","
                    + "NaN" + ","
                    + "NaN";

            writer.append(line);
            writer.newLine();
            writer.close();
        }catch (IOException e){
            System.err.println("Cannot write " + filePath);
        }
    }

    public void appendItem(String name, String building, String floor, String roomNum, String size,
                           String sender, String imagePath, String officerGet, String importantLV){
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String line = "Document" + ","
                    + name + ","
                    + building + ","
                    + floor + ","
                    + roomNum + ","
                    + size + ","
                    + dateFormat.format(date) + ","
                    + sender + ","
                    + imagePath + ","
                    + "still" + ","
                    + officerGet + ","
                    + "NaN" + ","
                    + "NaN" + ","
                    + "NaN" + ","
                    + importantLV;

            writer.append(line);
            writer.newLine();
            writer.close();
        }catch (IOException e){
            System.err.println("Cannot write " + filePath);
        }
    }

    public void appendItem(String name, String building, String floor, String roomNum, String size,
                           String sender, String imagePath, String officerGet, String postOffice, String trackingNumber){
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String line = "Package" + ","
                    + name + ","
                    + building + ","
                    + floor + ","
                    + roomNum + ","
                    + size + ","
                    + dateFormat.format(date) + ","
                    + sender + ","
                    + imagePath + ","
                    + "still" + ","
                    + officerGet + ","
                    + "NaN" + ","
                    + "NaN" + ","
                    + "NaN" + ","
                    + postOffice + ","
                    + trackingNumber;

            writer.append(line);
            writer.newLine();
            writer.close();
        }catch (IOException e){
            System.err.println("Cannot write " + filePath);
        }
    }

}
