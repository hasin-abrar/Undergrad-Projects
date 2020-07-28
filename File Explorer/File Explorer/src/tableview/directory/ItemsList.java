/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview.directory;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author Antu
 */
public class ItemsList {
    public static  ArrayList<FileValue> fileList = new ArrayList<>();
    public static String directoryField;
    
    public static void fillUpList(Path dir){
        fileList.clear();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file: stream) {
                Path filePath = file;
                File tempFile = filePath.toFile();
//                System.out.println(filePath.toString());
                
                String fileName = tempFile.getName();
                long fileSize = 0;
                if(tempFile.isFile()){
                    fileSize = tempFile.length();
                }
                ImageView fileIcon = FileIconSource.getIcon(fileName);
                
                Date lastModified = new Date(tempFile.lastModified());
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String formattedDateString = df.format(lastModified); 
                String createDate = formattedDateString;
                FileValue tempValue = new FileValue(filePath, fileName,fileSize,fileIcon,createDate);
                fileList.add(tempValue);
            }
            System.out.println("ItemsList er vitor");
        } catch (IOException | DirectoryIteratorException x) {
            System.err.println("In ItemList : "+x);
        }
    }

    public ItemsList() {
        Path dir = Paths.get(System.getProperty("user.dir"));
        directoryField = dir.toAbsolutePath().toString();
        System.out.println(" main path :"+dir.toAbsolutePath().toString());
        fillUpList(dir);
    }
}
