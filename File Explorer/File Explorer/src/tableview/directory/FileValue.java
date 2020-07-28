/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview.directory;

import java.io.File;
import java.nio.file.Path;
import javafx.scene.image.ImageView;

/**
 *
 * @author Antu
 */
public class FileValue {
    Path filePath;
    String fileName;

    long fileSize;
    ImageView fileIcon; 
    String lastModified;

    public FileValue(Path filePath, String fileName, long fileSize, ImageView fileIcon, String createDate) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileIcon = fileIcon;
        this.lastModified = createDate;
    }
    
    @Override
    public String toString() {
        return fileName;
    }
    public int getFloderCount(){
        File tempFile = filePath.toFile();
        int folderCount = 0;
        File[] files = tempFile.listFiles();
        if(files != null){
            for (File file : files) {
                folderCount++;
            }
        }
        return folderCount;
    }
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setFileIcon(ImageView fileIcon) {
        this.fileIcon = fileIcon;
    }

    public void setCreateDate(String createDate) {
        this.lastModified = createDate;
    }

    public long getFileSize() {
        return fileSize;
    }

    public ImageView getFileIcon() {
        return fileIcon;
    }

    public String getCreateDate() {
        return lastModified;
    }
    
    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Path getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }
    
}
