/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginversion2;

import UserInformation.User;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import wordPackage.FileDeilimiter;
import static wordPackage.FileDeilimiter.findMax;
//import static wordPackage.FileDeilimiter.wordList;
import wordPackage.GREWord;

/**
 * FXML Controller class
 *
 * @author _bazinga
 */
public class QuestionPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ArrayList<GREWord> wordsList;// = FileDeilimiter.wordList;
    FileDeilimiter sourceFile;
    GREWord mainWord; //egula sob reference kintu ektu buijha GENNNNNNNNNNNJAM
    AnswerPageController answerController ;
    User tempUser;
    
    @FXML
    Button logoutButton;
    @FXML
    Label masterLabel;
    @FXML
    Label learningLabel;
    @FXML
    Label reviewLabel;
    @FXML
    Label statusLabel;
    @FXML
    Label worDLabel;
    @FXML
    Button meaningButton;
    @FXML
    ProgressBar masterBar;
    @FXML
    ProgressIndicator masterIndicator; //eita kaj nao korte pare as object create kortesi kina bujhtesi na
    @FXML
    ProgressBar reviewBar;
    @FXML
    ProgressIndicator reviewIndicator;
    @FXML
    ProgressBar learningBar;
    @FXML
    ProgressIndicator learningIndicator;
    
    ObjectInputStream inFromServer = null;
    ObjectOutputStream outToServer = null;
    
    public Socket clientSocket ;
    public QuestionPageController() {
        try {
            clientSocket = new Socket("localhost", 33333);
            outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            inFromServer = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            System.out.println("errror in constructor");
        }
    }
    
    @FXML
    public void seeMeaningClick(ActionEvent e){
        answerController= new AnswerPageController();
        Stage stage = (Stage) meaningButton.getScene().getWindow();
        if(wordsList==null) System.out.println("NULL IN QuestionpageController");
        else answerController.showAnswerPage(stage, mainWord,wordsList,tempUser);
    }
    @FXML
    public void logoutClick(ActionEvent e){
        FXMLDocumentController temp = new FXMLDocumentController();
        Stage oldStage = (Stage) logoutButton.getScene().getWindow();
        if(tempUser == null) System.out.println("null in answerpage Logout button");
        else writeFile(tempUser,wordsList);
        temp.loginScreen(oldStage);
    }
    
    @FXML
    public void masterbarClicked(javafx.scene.input.MouseEvent e){
        System.out.println("clicked");
        WordShowController tempShow  = new WordShowController();
        Stage stage = (Stage)masterBar.getScene().getWindow();
        tempShow.listShow(stage,mainWord,wordsList,tempUser,"Master","Question");
    }
    @FXML
    public void reviewbarClicked(javafx.scene.input.MouseEvent e){
        System.out.println("clicked");
        WordShowController tempShow  = new WordShowController();
        Stage stage = (Stage)reviewBar.getScene().getWindow();
        tempShow.listShow(stage,mainWord,wordsList,tempUser,"Review","Question");
    }
    @FXML
    public void learningbarClicked(javafx.scene.input.MouseEvent e){
        System.out.println("clicked");
        WordShowController tempShow  = new WordShowController();
        Stage stage = (Stage)learningBar.getScene().getWindow();
        tempShow.listShow(stage,mainWord,wordsList,tempUser,"Learning","Question");
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
        if(mainUser== null) System.out.println("null in questionPage writeFile");
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
        }*/
    }
    public void showQuestionPage(Stage oldStage,ArrayList<GREWord> mainList,User mainUser){
        try {
            //knownBar = pg;
            //knowIndicator = pi;
            this.wordsList = mainList;
            this.tempUser = mainUser;
            
            if(mainList==null) System.out.println("null in showQuestionPage()");
            Stage QuestionStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("QuestionPage.fxml"));
            loader.setController(this);
            Parent root = (Parent) loader.load();
            Scene answerScene = new Scene(root);
            QuestionStage.setScene(answerScene);
            oldStage.close();
            QuestionStage.show();
            writeFile(mainUser,wordsList);
          //  mainWord = new GREWord();
            
            mainWord = questionFactory(wordsList);
            System.out.println("No error should it be in Question page controller "+mainWord.getWord());
            if(worDLabel==null){
                System.out.println("shouldn't be null");
            }
            else{
                worDLabel.setText("ok");
                System.out.println("ok?");
                worDLabel.setText(mainWord.getWord()); //previous stage closed ,set label ,then show
                System.out.println("Error? in Question page controller");

            }
            statusLabel.setText(mainWord.getStatus());
            showMasterBar();
            showLearningBar();
            showReviewBar();
        } catch (IOException ex) {
            //Logger.getLogger(QuestionPageController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("showQuestionPage e problem "+ex.getMessage());
            System.exit(0);
        }
    }
    public void showMasterBar(){
        int listSize = wordsList.size(); //agei reference peye jabe so null khaoa uchit na
        double progress =(double) tempUser.getMaster()/listSize; //change here if necessary
        masterBar.setProgress(progress);
        masterBar.setStyle("-fx-accent: red");
        masterIndicator.setProgress(progress);
        masterIndicator.setStyle("-fx-accent: green");
    }
    private void showReviewBar() {
        int listSize = wordsList.size(); //agei reference peye jabe so null khaoa uchit na
        double progress =(double) tempUser.getReview()/listSize; //change here if necessary
        reviewBar.setProgress(progress);
        reviewBar.setStyle("-fx-accent: green");
        reviewIndicator.setProgress(progress);
        reviewIndicator.setStyle("-fx-accent: brown");
    }

    private void showLearningBar() {
        int listSize = wordsList.size(); //agei reference peye jabe so null khaoa uchit na
        double progress =(double) tempUser.getLearning()/listSize; //change here if necessary
        learningBar.setProgress(progress);
        learningBar.setStyle("-fx-accent: brown");
        learningIndicator.setProgress(progress);
        learningIndicator.setStyle("-fx-accent: red");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("***********THROUGH RANDOM CALLS***********");
        masterLabel.setText("You have mastered "+tempUser.getMaster()+" words out of "+wordsList.size()+" words.");
        reviewLabel.setText("You are reviewing "+tempUser.getReview()+" words out of "+wordsList.size()+" words.");
        learningLabel.setText("You are learning "+tempUser.getLearning()+" words out of "+wordsList.size()+" words.");
        //for(int i=0;i<30;i++){
        //questionFactory();
        
        
        /*mainWord = questionFactory();
            System.out.println("No error should it be in Question page controller "+mainWord.getWord());
            if(worDLabel==null){
                System.out.println("shouldn't be null");
            }
            else{
                worDLabel.setText("ok");
                System.out.println("ok?");
                worDLabel.setText(mainWord.getWord()); //previous stage closed ,set label ,then show
                System.out.println("Error? in Question page controller");

            }
          */  
        //}
        
        
    }    
    public GREWord questionFactory(ArrayList<GREWord> wordList){
        Random rand = new Random();
        int a = rand.nextInt(wordList.size());
        int b = rand.nextInt(wordList.size());
        int c = rand.nextInt(wordList.size());
        GREWord showWord = findMax(wordList.get(a), findMax(wordList.get(c), wordList.get(b)));
        System.out.println("IN QUESTIOn factory "+showWord.getWord()+" "+showWord.getMeaning()+" "+showWord.getSynonym()+" "+showWord.getPriority());
        //wordLabel.setText(showWord.getWord());
        //showWord.decreasePriority();
        return showWord; //eikhane wordlist er reference e to pathaitesi, naki ?
    }
    
}
