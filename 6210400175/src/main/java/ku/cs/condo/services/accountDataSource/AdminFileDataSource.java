package ku.cs.condo.services.accountDataSource;

import ku.cs.condo.models.account.AccountList;
import ku.cs.condo.models.account.AdminAccount;
import ku.cs.condo.models.account.AdminList;

import java.io.*;

public class AdminFileDataSource implements AccountsFileDataSource{
    private String fileDirectoryName;
    private String fileName;
    private AdminList admins;

    public AdminFileDataSource(String fileDirectoryName, String fileName){
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
            AdminAccount admin = new AdminAccount(data[0].trim(), data[1].trim());
            admins.add(admin);
        }
        reader.close();
    }

    public AdminList getAdminsData(){
        try{
            admins = new AdminList();
            readData();
        }catch (FileNotFoundException e){
            System.err.println(this.fileName + "not found");
        } catch (IOException e) {
            System.err.println("IOException from reading" + this.fileName);
        }
        return admins;
    }

    public void setData(AccountList admins){
        String filePath = fileDirectoryName + File.separator  + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for(AdminAccount admin : ((AdminList)admins).getAccounts()){
                String line = admin.getUsername() + ","
                        + admin.getPassword();
                writer.append(line);
                writer.newLine();
            }
            writer.close();
        }catch (IOException e){
            System.err.println("Cannot write " + filePath);
        }
    }
}
