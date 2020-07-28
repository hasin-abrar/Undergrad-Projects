/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginversion2;

import UserInformation.User;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
import javafx.scene.control.Separator;
import javafx.stage.Stage;
import wordPackage.GREWord;

/**
 * FXML Controller class
 *
 * @author _bazinga
 */
public class AnswerPageController implements Initializable {

    /**
     * Initializes the controller class.
     * @param oldStage
     */
    QuestionPageController questoinController ;
    ArrayList<GREWord> wordList;
    User tempUser;
    GREWord mainWord;
    @FXML
    Separator lineSeparator;
    @FXML
    Button logoutButton;
    @FXML
    Label meaningLabel;
    @FXML
    Label reviewLabel;
    @FXML
    Label learningLabel;
    @FXML
    Label masterLabel;
    @FXML
    ProgressBar masterBar;
    @FXML
    ProgressIndicator masterIndicator;
    @FXML
    ProgressBar reviewBar;
    @FXML
    ProgressIndicator reviewIndicator;
    @FXML
    ProgressBar learningBar;
    @FXML
    ProgressIndicator learningIndicator;
    @FXML
    Label synonymLabel;
    
    ObjectInputStream inFromServer = null;
    ObjectOutputStream outToServer = null;
    
    public Socket clientSocket ;

    public AnswerPageController() {
        try {
            clientSocket = new Socket("localhost", 33333);
            outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            inFromServer = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            System.out.println("errror in constructor");
        }
    }
    @FXML
    public void masterdClick(ActionEvent tok){
        System.out.println("clicked");
        //(Stage oldStage, GREWord word,ArrayList<GREWord> mainList,User mainUser)
        
    }
    
    @FXML
    public void masterbarClicked(javafx.scene.input.MouseEvent e){
        System.out.println("clicked");
        WordShowController tempShow  = new WordShowController();
        Stage stage = (Stage)masterBar.getScene().getWindow();
        tempShow.listShow(stage,mainWord,wordList,tempUser,"Master","Answer");
    }
    @FXML
    public void reviewbarClicked(javafx.scene.input.MouseEvent e){
        System.out.println("clicked");
        WordShowController tempShow  = new WordShowController();
        Stage stage = (Stage)reviewBar.getScene().getWindow();
        tempShow.listShow(stage,mainWord,wordList,tempUser,"Review","Answer");
    }
    @FXML
    public void learningbarClicked(javafx.scene.input.MouseEvent e){
        System.out.println("clicked");
        WordShowController tempShow  = new WordShowController();
        Stage stage = (Stage)learningBar.getScene().getWindow();
        tempShow.listShow(stage,mainWord,wordList,tempUser,"Learning","Answer");
    }
    
    @FXML
    public void knownWord(ActionEvent ae){
        questoinController= new QuestionPageController();
        
        Stage stage = (Stage)masterBar.getScene().getWindow(); //word er priority
        if(!mainWord.isMasteredWord()){ //master word na so master banailam
            mainWord.decreasePriority();
            mainWord.setLearningWord(false);
            mainWord.setMasteredWord(true);
            tempUser.setMaster(tempUser.getMaster()+1);
        }
        else{ // already mastered so reviewing
            mainWord.decreasePriority();
            if(!mainWord.isReviewWord()){
                mainWord.setReviewWord(true);
                mainWord.setLearningWord(false);
                tempUser.setReview(tempUser.getReview()+1);
            }
        }
        showMasterBar();
        showLearningBar();
        showReviewBar();
//        FXMLDocumentController temp = new FXMLDocumentController();
//        temp.showPriority();
        questoinController.showQuestionPage(stage, wordList, tempUser);
        
    }
    @FXML
    public void seePriority(ActionEvent e){
        for(int i=0;i<wordList.size();i++){
            GREWord temp = wordList.get(i);
            System.out.println("Word : "+temp.getWord()+" Priority : "+temp.getPriority());
        }
    }
    @FXML
    public void unKnownWord(ActionEvent ae){
        questoinController= new QuestionPageController();
        
        Stage stage = (Stage)masterBar.getScene().getWindow(); //word er priority
        
        if(!mainWord.isLearningWord()){ 
            mainWord.setLearningWord(true);
            tempUser.setLearning(tempUser.getLearning()+1);
            //mainWord.setMasteredWord(false);
        }
        else{
            if(!mainWord.isReviewWord()){
                //mainWord.setMasteredWord(false);
                mainWord.setReviewWord(true);
                tempUser.setReview(tempUser.getReview()+1);
            }
        }
//        mainWord.setLearningWord(true);
//        tempUser.setLearning(tempUser.getLearning()+1);
        showMasterBar();
        showLearningBar();
        showReviewBar();
//        FXMLDocumentController temp = new FXMLDocumentController();
//        temp.showPriority();
        questoinController.showQuestionPage(stage, wordList, tempUser);
        
    }
    @FXML
    public void logoutClick(ActionEvent e){
        FXMLDocumentController temp = new FXMLDocumentController();
        Stage oldStage = (Stage) logoutButton.getScene().getWindow();
        if(tempUser == null) System.out.println("null in answerpage Logout button");
        else writeFile(tempUser,wordList);
        temp.loginScreen(oldStage);
    }
    public void showAnswerPage(Stage oldStage, GREWord word,ArrayList<GREWord> mainList,User mainUser){
        try {
            
            mainWord = word;
            wordList = mainList;
            tempUser = mainUser;
            Stage answerStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AnswerPage.fxml"));
            loader.setController(this);
            System.out.println("this  e problem");
            Parent root = (Parent)loader.load();
            System.out.println("load e problm");
            Scene answerScene = new Scene(root);
            answerStage.setScene(answerScene);
            
            oldStage.close();
            answerStage.show();
            System.out.println("meaning ???");
            
            writeFile(mainUser,mainList);
            String Meaning = "Meaning : "+mainWord.getMeaning();
            String Synonym = "Synonym : "+mainWord.getSynonym();
            meaningLabel.setText(Meaning); //previous stage closed ,set label ,then show
            synonymLabel.setText(Synonym);
            System.out.println("meaining e ");
            showMasterBar();
            showLearningBar();
            showReviewBar();
            mainWord.setNewWord(false);
        } catch (IOException ex) {
            //Logger.getLogger(AnswerPageController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error in Answer page (ShowAnswerPage()) "+ex.getMessage());
            System.exit(0);
        }
    }
    
    public void writeFile(User mainUser,ArrayList<GREWord> tempList){
        try {
            String qstnPage = mainUser.getName()+":UPDATE";
            outToServer.writeObject(qstnPage);
            outToServer.flush();
            outToServer.writeObject(mainUser);
            outToServer.flush();
            outToServer.writeObject(tempList);
            outToServer.flush();
            String offThread = "client:STOP";
            outToServer.writeObject(offThread);
            outToServer.flush();
        } catch (IOException ex) {
            System.out.println("Error in writing dile in qstnpageCOntroller");
        }
        /*
        if(mainUser== null) System.out.println("null in answerpage writeFile");
        String way = "D:\\Programming\\JAVA2\\loginVersion2\\UserSituationInformation\\"; 
        File userFile = new File(way+mainUser.getName()+".txt");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))){
            oos.writeObject(mainUser);
            oos.flush();
        }catch(Exception e){
            System.out.println("Exception in oos in answerPageController");
        }
        
        way = "D:\\Programming\\JAVA2\\loginVersion2\\ArrayListInformation\\"; 
        File user2File = new File(way+mainUser.getName()+".txt");
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(user2File))){
            oos.writeObject(tempList);
            oos.flush();
        }catch(Exception e){
            System.out.println("Exception in oos arraylist in answerPageController");
        }
        */
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        masterLabel.setText("You have mastered "+tempUser.getMaster()+" words out of "+wordList.size()+" words.");
        reviewLabel.setText("You are reviewing "+tempUser.getReview()+" words out of "+wordList.size()+" words.");
        learningLabel.setText("You are learning "+tempUser.getLearning()+" words out of "+wordList.size()+" words.");
        lineSeparator.setStyle("-fx-background-color: black;");
         
    }    
    
    private void showMasterBar(){
        int listSize = wordList.size(); //agei reference peye jabe so null khaoa uchit na
        double progress =(double) tempUser.getMaster()/listSize; //change here if necessary
        masterBar.setProgress(progress);
        masterBar.setStyle("-fx-accent: red");
        masterIndicator.setProgress(progress);
        masterIndicator.setStyle("-fx-accent: green");
    }
    private void showReviewBar() {
        int listSize = wordList.size(); //agei reference peye jabe so null khaoa uchit na
        double progress =(double) tempUser.getReview()/listSize; //change here if necessary
        reviewBar.setProgress(progress);
        reviewBar.setStyle("-fx-accent: green");
        reviewIndicator.setProgress(progress);
        reviewIndicator.setStyle("-fx-accent: brown");
    }

    private void showLearningBar() {
        int listSize = wordList.size(); //agei reference peye jabe so null khaoa uchit na
        double progress =(double) tempUser.getLearning()/listSize; //change here if necessary
        learningBar.setProgress(progress);
        learningBar.setStyle("-fx-accent: brown");
        learningIndicator.setProgress(progress);
        learningIndicator.setStyle("-fx-accent: red");
    }
    
}
