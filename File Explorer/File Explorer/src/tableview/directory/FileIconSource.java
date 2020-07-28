/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview.directory;


import static java.awt.SystemColor.info;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Antu
 */
public class FileIconSource {
    
    public static ImageView getIcon(String imageName){
        Image fileIcon = null;
        try {
            fileIcon = getFileIcon(imageName);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ImageView imageView = new ImageView(fileIcon);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        imageView.setPreserveRatio(true);
//        imageView.setFitWidth(15);
//        imageView.setFitHeight(15);
        return imageView;
    }    

    static HashMap<String, Image> mapOfFileExtToSmallIcon = new HashMap<String, Image>();
    
    private static String getFileExt(String fname) {
        String ext = ".";
        int p=fname.lastIndexOf('.');
        if (p>=0) ext = fname.substring(p);
        return ext.toLowerCase();
    }

    private static javax.swing.Icon getJSwingIconFromFileSystem(File file) {
        FileSystemView view = FileSystemView.getFileSystemView();
        javax.swing.Icon icon = view.getSystemIcon(file);
        return icon;
    }

    private static Image getFileIcon(String fname) throws IOException {
        final String ext = getFileExt(fname);
        Image fileIcon = mapOfFileExtToSmallIcon.get(ext);
        if (fileIcon == null) {
            javax.swing.Icon jswingIcon = null; 
            File file = new File(fname);
            if (file.exists()) {
                jswingIcon = getJSwingIconFromFileSystem(file);
            }
            else{
                File tempFile = null;
                tempFile = File.createTempFile("icon", ext);
                jswingIcon = getJSwingIconFromFileSystem(tempFile);
                if (tempFile != null) tempFile.delete();
            }
            
            if (jswingIcon != null) {
                fileIcon = jswingIconToImage(jswingIcon);
                mapOfFileExtToSmallIcon.put(ext, fileIcon);
            }
        }
        return fileIcon;
    }

    private static Image jswingIconToImage(javax.swing.Icon jswingIcon) {
        BufferedImage bufferedImage = new BufferedImage(jswingIcon.getIconWidth(), jswingIcon.getIconHeight(),
        BufferedImage.TYPE_INT_ARGB);
        jswingIcon.paintIcon(null, bufferedImage.getGraphics(), 0, 0);
        return SwingFXUtils.toFXImage(bufferedImage, null);
    }
}
