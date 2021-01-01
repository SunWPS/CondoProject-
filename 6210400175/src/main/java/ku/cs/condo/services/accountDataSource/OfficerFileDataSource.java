package ku.cs.condo.services.accountDataSource;

import ku.cs.condo.models.account.AccountList;
import ku.cs.condo.models.account.OfficerAccount;
import ku.cs.condo.models.account.OfficerList;

import java.io.*;

public class OfficerFileDataSource implements AccountsFileDataSource{
    private String fileDirectoryName;
    private String fileName;
    private OfficerList officers;

    public OfficerFileDataSource(String fileDirectoryName, String fileName){
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
            OfficerAccount officer = new OfficerAccount(data[0].trim(), data[1].trim(), data[2].trim(), data[3].trim(),
                                                        data[4].trim(), Integer.parseInt(data[5].trim()), data[6].trim());
            officers.add(officer);
        }
        reader.close();
    }

    public OfficerList getOfficersData(){
        try{
            officers = new OfficerList();
            readData();
        }catch (FileNotFoundException e){
            System.err.println(this.fileName + "not found");
        } catch (IOException e) {
            System.err.println("IOException from reading" + this.fileName);
        }
        return officers;
    }

    public void setData(AccountList officers){
        String filePath = fileDirectoryName + File.separator  + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(OfficerAccount officer: ((OfficerList)officers).getAccounts()){
                String line = officer.getUsername() + ","
                        + officer.getPassword() + ","
                        + officer.getName() + ","
                        + officer.getDateAndTime() + ","
                        + officer.getStatus() + ","
                        + officer.getTryToLogin() + ","
                        + officer.getImagePath();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            System.err.println("Cannot write " + filePath);
        }
    }

    public void appendOfficer(String inputUsername, String inputPassword, String inputName, String inputImagePath){
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            String line = inputUsername + ","
                    + inputPassword + ","
                    + inputName + ","
                    +  "0000/00/00 00:00:00" + ","
                    +  "activate" + ","
                    + "0" + ","
                    + inputImagePath;
            writer.append(line);
            writer.newLine();
            writer.close();
        }catch (IOException e){
            System.err.println("Cannot write " + filePath);
        }
    }
}
