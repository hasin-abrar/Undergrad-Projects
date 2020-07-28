/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview.directory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Antu
 */
public class TilesViewFXMLController implements Initializable {
    private TreeResource treeResource;
    private TilesViewResource tileViewResource;
    private TreeView<FileValue> treeView;
    
    @FXML
    AnchorPane anchorLayout;
    @FXML
    public  TextField directoryText;
    
    @FXML
    private ScrollPane directoryScrollPane;
    @FXML 
    private TilePane directoryTilePane;
    @FXML
    private VBox containerBox;
    @FXML
    private Button toogleButton;
    
    @FXML
    private ImageView tempImage;
    @FXML
    private MenuItem tableItem;
    
    @FXML
    private void toogleButtonAction(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) toogleButton.getScene().getWindow();
        //tilesController.showTilesView();
        System.out.println("paisi");
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("tableViewFXML.fxml"));
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
    public void updateDirectory(){
        String dirText = ItemsList.directoryField;
        directoryText.setText(dirText);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateDirectory();
        //TileView System
        tileViewResource = new TilesViewResource(directoryScrollPane, directoryTilePane, containerBox, tempImage);
        tileViewResource.populateTileView(directoryText);
        System.out.println("asis ?");
        //Tree System
        treeResource = TreeResource.getResourceInstance();
        treeView = treeResource.getTreeViewInstance();
        treeResource.activateClickingTree(tileViewResource, directoryText);
        anchorLayout.getChildren().add(treeView);
    }    
    
}
