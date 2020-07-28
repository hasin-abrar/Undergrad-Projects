/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginversion2;

import UserInformation.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import wordPackage.GREWord;

/**
 * FXML Controller class
 *
 * @author _bazinga
 */
public class MAINController implements Initializable {

    /**
     * Initializes the controller class.
     * @throws java.io.IOException
     */
//    QuestionPageController questionController;
//    ProgressBar masterBar  ;
//    ProgressIndicator masterIndicator;
    ArrayList<GREWord> wordList;
    User tempUser;
    @FXML
    Label userNameLabel;
    @FXML
    Button logout;
    @FXML
    Button exit;
    @FXML
    Stage myStage;
    @FXML
    Button enterButton;
    public void showHome(Stage old,ArrayList<GREWord> mainList,User mainUser) {
        
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MAIN.fxml"));
        loader.setController(this); //*******fxml theke baad|| kaj korse ^^
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(MAINController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("main controller root load e prblm");
        }
        if(mainList==null) System.out.println("null in main->showHOme()");
        this.tempUser = mainUser;
        this.wordList = mainList;
        Scene scene = new Scene(root);
        myStage = new Stage();
        myStage.setScene(scene);
        String userName = tempUser.getName().toUpperCase();
        userNameLabel.setText("HI "+userName);
        /*
        int listSize = wordList.size(); //agei reference peye jabe so null khaoa uchit na
        double progress =(double) tempUser.getMaster()/listSize; //change here if necessary
        masterBar.setProgress(progress);
        masterIndicator.setProgress(progress); 
        */
        old.close();
        myStage.show();
        
    }
    @FXML
    public void logout(ActionEvent e){
        Stage stage = (Stage) logout.getScene().getWindow();
        FXMLDocumentController loginPage = new FXMLDocumentController();
        loginPage.loginScreen(stage);
    }
    @FXML
    public void enterQuestionPage(ActionEvent e){
        QuestionPageController questionController = new QuestionPageController();
        Stage stage = new Stage();
        stage = (Stage) enterButton.getScene().getWindow();
        if(wordList==null) System.out.println("null in enterQuestionPage()");
        questionController.showQuestionPage(stage,wordList,tempUser);
    }
    @FXML
    public void quit(){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //this.wordList = new ArrayList<>();
        enterButton.setStyle("-fx-background-color: black;");
    }    
    
}
