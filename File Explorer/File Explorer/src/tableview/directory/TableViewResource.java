/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview.directory;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Antu
 */
public class TableViewResource {
    private TableColumn<FileValue,String> icon,name,size,dateModified;
    private TableView<FileValue> directoryTable;
    
    private ObservableList<FileValue> showableList = FXCollections.observableArrayList();
    private  ArrayList<FileValue> folderArray;

    public TableViewResource(TableColumn<FileValue, String> icon, TableColumn<FileValue, String> name, TableColumn<FileValue, String> size, TableColumn<FileValue, String> dateModified, TableView<FileValue> directoryTable) {
        this.icon = icon;
        this.name = name;
        this.size = size;
        this.dateModified = dateModified;
        this.directoryTable = directoryTable;
        folderArray = ItemsList.fileList;
        setCellFactory();
        populateTableView();
        directoryTable.setItems(showableList);
    }
    private void setCellFactory(){
        icon.setCellValueFactory(new PropertyValueFactory("fileIcon")); 
        name.setCellValueFactory(new PropertyValueFactory("fileName"));
        size.setCellValueFactory(new PropertyValueFactory("fileSize"));
        dateModified.setCellValueFactory(new PropertyValueFactory("createDate"));
    }
    
    public void sortTable(){
         Collections.sort(folderArray, new Comparator<FileValue>() {
             @Override
             public int compare(FileValue o1, FileValue o2) {
                return (new Integer(o1.getFloderCount()).compareTo(new Integer(o2.getFloderCount())));
             }
            });
    }
    
    public void populateTableView() {
        directoryTable.getItems().clear();
        System.out.println("size : "+ folderArray.size());
        sortTable();
        for(int i=0;i<folderArray.size();i++){
            FileValue temp = folderArray.get(i);
            ImageView iconView = temp.getFileIcon();
            iconView.setFitHeight(20);
            iconView.setFitWidth(20);
            showableList.add(temp);
        }
        
        directoryTable.setItems(showableList);
    }
    
    public void onDoubleClickActivateTable(TextField dirField){
        directoryTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    if(directoryTable.getSelectionModel().getSelectedItem() != null){
                        try{
                            FileValue currentItem = (FileValue) directoryTable.getSelectionModel().getSelectedItem();
                            String directory = currentItem.getFilePath().toAbsolutePath().toString();
                            ItemsList.directoryField = directory;
                            dirField.setText(directory);
                            Path tempPath = currentItem.getFilePath();
                            ItemsList.fillUpList(tempPath);
                            populateTableView();
                        }catch(Exception e){
                            System.out.println("No such File");
                        }
                    }
                }
            }
        });
    }
}
