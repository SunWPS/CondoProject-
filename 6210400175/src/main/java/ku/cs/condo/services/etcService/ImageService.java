package ku.cs.condo.services.etcService;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ImageService {

    public String getImage(String dirName, String imageName){
        File jarDir = null;
        File codeDir = null;
        File f = new File(imageName);
        try {
            jarDir = new File(this.getClass().getProtectionDomain().getCodeSource()
                                .getLocation().toURI().getPath());
            codeDir = jarDir.getParentFile();
            String path = codeDir.toString() + File.separator + dirName + File.separator + f.getName();
            File uploadFile = new File(path);
            return uploadFile.toURI().toString();

        }catch (URISyntaxException e){
            e.printStackTrace();
        }
        return null;
    }

    public String copyImage(String dirName, File original){
        File file = new File(dirName);
        if(!file.exists()){
            file.mkdirs();
        }
        File jarDir = null;
        File codeDir = null;
        try {
            jarDir = new File(this.getClass().getProtectionDomain().getCodeSource()
                    .getLocation().toURI().getPath());
            codeDir = jarDir.getParentFile();
            String path = codeDir.toString() + File.separator + dirName + File.separator + original.getName();
            String copyPath = check(path);

            File copy = new File(copyPath);
            try {
                Files.copy(original.toPath(), copy.toPath());
                return copy.getName();
            } catch (IOException e) {
                System.out.println("Can't copy");
            }
        } catch (URISyntaxException e){
            e.printStackTrace();
        }
        return "";
    }

    public String check(String path){
        File file = new File(path);
        if (!file.exists()) {
            return path;
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss");
            Date date = new Date();
            if (path.contains(".jpg")) {
                path = path.replace(".jpg", dateFormat.format(date)) + ".jpg";
            }
            else if(path.contains(".jpeg")){
                path = path.replace(".jpeg", dateFormat.format(date)) + ".jpeg";
            }
            else if(path.contains(".png")){
                path = path.replace(".png", dateFormat.format(date)) + ".png";
            }
            return check(path);
        }
    }
}
