/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview.directory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Antu
 */
public class TilesViewResource {
    private  ArrayList<FileValue> folderArray;
    
    private ScrollPane directoryScrollPane;
    private TilePane directoryTilePane;
    private VBox containerBox;
    private ImageView tempImage;

    public TilesViewResource(ScrollPane directoryScrollPane, TilePane directoryTilePane, VBox containerBox, ImageView tempImage) {
        this.directoryScrollPane = directoryScrollPane;
        this.directoryTilePane = directoryTilePane;
        this.containerBox = containerBox;
        this.tempImage = tempImage;
        folderArray = ItemsList.fileList;
    }
    public void sortTable(){
         Collections.sort(folderArray, new Comparator<FileValue>() {
             @Override
             public int compare(FileValue o1, FileValue o2) {
                return (new Integer(o1.getFloderCount()).compareTo(new Integer(o2.getFloderCount())));
             }
            });
    }
    public void populateTileView(TextField dirField) {
        directoryTilePane.getChildren().clear();
        sortTable();
        for(int i=0;i<folderArray.size();i++){
            FileValue temp = folderArray.get(i);
            VBox iconBox = new VBox(5);
            iconBox.setPrefWidth(70);
            ImageView iconView = temp.getFileIcon();
            iconView.setFitHeight(40);
            iconView.setFitWidth(40);
            iconView.setPreserveRatio(true);
            Label captionLabel = new Label(temp.getFileName());
            iconBox.setAlignment(Pos.CENTER);
            iconBox.getChildren().addAll(iconView,captionLabel);
            
            ObservableList<Node> tileList = directoryTilePane.getChildren();
            
            tileList.add(new Group(iconBox));
            
            
            iconBox.setOnMouseClicked((MouseEvent event) -> {
                if(event.getClickCount() == 2)
                {
                        try{
                            String dir = temp.getFilePath().toAbsolutePath().toString();
                            ItemsList.directoryField = dir;
                            dirField.setText(dir);
                            Path tempPath = temp.getFilePath();
                            ItemsList.fillUpList(tempPath);
                            populateTileView(dirField);
                        }catch(Exception e){
                            System.out.println("No such File");
                        }
                    
                }
            });
        }
        directoryTilePane.setOrientation(Orientation.HORIZONTAL);
    }

}
