/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview.directory;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Antu
 */
public class TableViewFXMLController implements Initializable {
    
    private TreeResource treeResource;
    private TableViewResource tableViewResource;
    private TreeView<FileValue> directoryTree;
    
    @FXML
    AnchorPane anchorLayout;
    @FXML
    private TableColumn<FileValue,String> icon;
    @FXML
    private TableColumn<FileValue,String> name;
    @FXML
    private TableColumn<FileValue,String> size;
    @FXML
    private TableColumn<FileValue,String> dateModified;
    @FXML
    private TableView<FileValue> directoryTable;
    
   
    @FXML
    public  TextField directoryText;
    
    @FXML
    private Button toogleButton;
    
    
    
    @FXML
    private void toogleButtonAction(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) toogleButton.getScene().getWindow();
        //tilesController.showTilesView();
        System.out.println("paisi");
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("TilesViewFXML.fxml"));
            if(root == null) System.out.println("ken vai ken");
            else System.out.println("okads");
        } catch (IOException ex) {
            System.out.println("load e abar");
        }
        Scene tileScene = new Scene(root);
        Stage tileStage = new Stage();
        tileStage.setTitle("File Explorer");
        tileStage.setScene(tileScene);
        tileStage.show();
        System.out.println("dekhai ?");
        
        currentStage.close();
    }
    
    
    public  void updateDirectory(){
        String dirText = ItemsList.directoryField;
        directoryText.setText(dirText);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateDirectory();
        //TableView System
        tableViewResource = new TableViewResource(icon, name, size, dateModified, directoryTable);
        tableViewResource.onDoubleClickActivateTable(directoryText);
        //Tree System
        treeResource = TreeResource.getResourceInstance();
        directoryTree = treeResource.getTreeViewInstance();
        anchorLayout.getChildren().add(directoryTree);
        
        System.out.println("visible "+directoryTree.isVisible()+" disable "+directoryTree.isDisable());
        treeResource.activateClickingTree(tableViewResource, directoryText);
    }    

    
}
