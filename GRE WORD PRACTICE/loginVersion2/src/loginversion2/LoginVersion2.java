/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginversion2;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wordPackage.FileDeilimiter;

/**
 *
 * @author _bazinga
 */
public class LoginVersion2 extends Application {
    @FXML
    Stage mystage;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        //loader.setController(this);
        Parent root = loader.load();
        System.out.println("MAIN er start");
        Scene scene = new Scene(root,665,435);
        mystage = stage;
        mystage.setTitle("Login");
        mystage.setScene(scene);
        mystage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //new FileDeilimiter();
        launch(args);
    }

    
    
}
