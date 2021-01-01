package ku.cs.condo.services.accountDataSource;

import ku.cs.condo.models.account.AccountList;
import ku.cs.condo.models.account.ResidentAccount;
import ku.cs.condo.models.account.ResidentList;

import java.io.*;

public class ResidentFileDataSource implements AccountsFileDataSource{
    private String fileDirectoryName;
    private String fileName;
    private ResidentList residents;

    public ResidentFileDataSource(String fileDirectoryName, String fileName){
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
            ResidentAccount resident = new ResidentAccount(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim());
            residents.add(resident);
        }
        reader.close();
    }

    public ResidentList getResidentsData(){
        try{
            residents = new ResidentList();
            readData();
        }catch (FileNotFoundException e){
            System.err.println(this.fileName + "not found");
        } catch (IOException e) {
            System.err.println("IOException from reading" + this.fileName);
        }
        return residents;
    }

    @Override
    public void setData(AccountList residents){
        String filePath = fileDirectoryName + File.separator  + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(ResidentAccount resident : ((ResidentList)residents).getAccounts()){
                String line = resident.getUsername() + ","
                        + resident.getPassword() + ","
                        + resident.getName() + ","
                        + resident.getRoomNum();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            System.err.println("Cannot write " + filePath);
        }
    }

    public void appendResident(String userName, String password, String name, String roomNum){
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            String line = userName + ","
                    + password + ","
                    + name + ","
                    + roomNum;
            writer.append(line);
            writer.newLine();
            writer.close();
        }catch (IOException e){
            System.err.println("Cannot write " + filePath);
        }
    }
}
