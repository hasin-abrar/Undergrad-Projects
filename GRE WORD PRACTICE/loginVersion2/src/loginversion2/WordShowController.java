/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginversion2;

import UserInformation.User;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.stage.Stage;
import wordPackage.GREWord;

/**
 * FXML Controller class
 *
 * @author _bazinga
 */
public class WordShowController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ObservableList<GREWord> data = FXCollections.observableArrayList();
    int flag = 0;
    ArrayList<GREWord> mainList;
    User mainUser;
    GREWord mainWord;
    String prevPage;
    @FXML
    private ListView<GREWord> wordView;
    @FXML
    private Label statusLabel;
    @FXML
    Button backButton;
    @FXML
    Separator lineSeparator;
    
    
    public void listShow(Stage oldStage,GREWord tempWord,ArrayList<GREWord> wordList,User tempUser,String showstatus,String page){
        try {
            mainWord = tempWord;
            mainList = wordList;
            mainUser = tempUser;
            prevPage = page;
            Stage showStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("WordShow.fxml"));
            loader.setController(this);
            Parent root = (Parent)loader.load();
            Scene answerScene = new Scene(root);
            //answerScene.getStylesheets().add("listview.css");
            showStage.setScene(answerScene);
            oldStage.close();
            showStage.show();
            if(showstatus.equals("Master")){
                for(int i=0;i<wordList.size();i++){
                    GREWord temp = wordList.get(i);
                    if(temp.isMasteredWord()) {
                        data.add(temp);
                        flag = 1;
                    }
                }
                GREWord fakeWord = new GREWord();
                fakeWord.setWord("");
                fakeWord.setMeaning("bla");
                if(flag==0) {
                    data.add(fakeWord);
                    String status = tempUser.getName().toUpperCase()+",YOU HAVEN'T MASTERED ANY WORD YET";
                    statusLabel.setText(status);
                }
                else{
                    String status = tempUser.getName().toUpperCase()+",YOU HAVE MASTERED THESE WORDS.PRESS ANY OF THEM TO REVIEW AGAIN";
                    statusLabel.setText(status);
                }
            }else if(showstatus.equals("Review")){
                for(int i=0;i<wordList.size();i++){
                    GREWord temp = wordList.get(i);
                    if(temp.isReviewWord()) {
                        data.add(temp);
                        flag = 1;
                    }
                }
                GREWord fakeWord = new GREWord();
                fakeWord.setWord("");
                fakeWord.setMeaning("bla");
                if(flag==0) {
                    data.add(fakeWord);
                    String status = tempUser.getName().toUpperCase()+",YOU HAVEN'T REVIEWED ANY WORD YET";
                    statusLabel.setText(status);
                }
                else{
                    String status = tempUser.getName().toUpperCase()+",YOU HAVE REVIEWED THESE WORDS.PRESS ANY OF THEM TO REVIEW AGAIN";
                    statusLabel.setText(status);
                }
            }else if(showstatus.equals("Learning")){
                for(int i=0;i<wordList.size();i++){
                    GREWord temp = wordList.get(i);
                    if(temp.isLearningWord()){
                        data.add(temp);
                        flag = 1;
                    }
                }
                GREWord fakeWord = new GREWord();
                fakeWord.setWord("");
                fakeWord.setMeaning("bla");
                if(flag==0) {
                    data.add(fakeWord);
                    String status = tempUser.getName().toUpperCase()+",YOU HAVEN'T LEARNED ANY WORD YET";
                    statusLabel.setText(status);
                }
                else{
                    String status = tempUser.getName().toUpperCase()+",YOU HAVE LEARNED THESE WORDS.PRESS ANY OF THEM TO REVIEW AGAIN";
                    statusLabel.setText(status);
                }
            }
            
        } catch (IOException ex) {
            System.out.println("Wordshow er load e problem "+ex.getMessage());
        }
    }
    @FXML
    public void backClicked(ActionEvent e){
        if(prevPage.equals("Question")){
            Stage oldStage = (Stage) backButton.getScene().getWindow();
            QuestionPageController answr = new QuestionPageController();
            answr.showQuestionPage(oldStage, mainList, mainUser);
        }else{
            Stage oldStage = (Stage) backButton.getScene().getWindow();
            AnswerPageController answr = new AnswerPageController();
            answr.showAnswerPage(oldStage, mainWord, mainList, mainUser);
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        wordView.setItems(data);
        //wordView.setStyle("-fx-background-color: transparent;");
        lineSeparator.setStyle("-fx-background-color: black;");
        //showing what i want
        wordView.setCellFactory(lv -> new ListCell<GREWord>() {
            @Override
            public void updateItem(GREWord item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    String text = item.getWord() ; // get text from item
                    setText(text);
                }
            }
        });
        //getting what i want
        wordView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends GREWord> observable, GREWord oldValue, GREWord newValue) -> {
            System.out.println(oldValue+" Selected item: " + newValue.getMeaning());
            if(newValue.getWord().equals("")){
                Stage oldStage = (Stage) wordView.getScene().getWindow();
                AnswerPageController answr = new AnswerPageController();
                answr.showAnswerPage(oldStage, mainWord, mainList, mainUser);
            }else{
                Stage oldStage = (Stage) wordView.getScene().getWindow();
                AnswerPageController answr = new AnswerPageController();
                answr.showAnswerPage(oldStage, newValue, mainList, mainUser);
            }
            
        });
    }    
    
}
