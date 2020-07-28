/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginversion2;

import UserInformation.User;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import wordPackage.FileDeilimiter;
import wordPackage.GREWord;

/**
 *
 * @author _bazinga
 */
public class FXMLDocumentController implements Initializable,Runnable {
    //public QuestionPageController questionController;
    ArrayList<GREWord> wordsList;// = FileDeilimiter.wordList;
    FileDeilimiter sourceFile;
    User newUser ;
    @FXML
    private Button exitButton;
    @FXML
    private Label confirm;
    @FXML
    private Button login;
    @FXML
    private Button signUp;
    @FXML
    private TextField username;
    @FXML
    private TextField passTextField;
    @FXML
    private PasswordField pass;
    @FXML
    private MAINController main;
    @FXML
    private CheckBox showpassCheckBox;
    ProgressBar pg ;
    ProgressIndicator pi;
    ObjectInputStream inFromServer = null;
    ObjectOutputStream outToServer = null;
    
    public Socket clientSocket ; //eita constructor e pathaya throws bad dite chai
    
    public FXMLDocumentController(){
        try {
            clientSocket = new Socket("localhost", 33333);
            outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            inFromServer = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            System.out.println("errror in constructor");
        }
    }
    @FXML
    private void exitButtonClicked(ActionEvent e){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void onLoginClick(ActionEvent e){
        String receivedPass=null;
        String user=null;
        String paSS=null;
        try {
            System.out.println("Entry");
            user = username.getText();
            paSS = pass.getText(); //**********NULL er jonno kahini kora pore

            String checkLogin = user+":check";
            outToServer.writeObject(checkLogin);
            System.out.println("writebyte ok");
            outToServer.flush();
            receivedPass =(String) inFromServer.readObject();
        } catch (Exception ex) {
            System.out.println("Error in writing input in FXmlDocumentController");
        }    

            System.out.println("received pass : "+receivedPass);
            if(receivedPass!=null && !receivedPass.equals("#NOSUCHNAME#")){
                if(!paSS.equals(null) && paSS.equals(receivedPass)){
                    Stage myStage = (Stage)login.getScene().getWindow();
                    System.out.println("Ok"); //Alhamdulillah
                    String successLogin = user+":ok";
                    try {
                        outToServer.writeObject(successLogin);
                        System.out.println("ois+oos");
                        newUser = new User(wordsList.size());
                        System.out.println("ettuk ok");
                        newUser = (User)inFromServer.readObject();
                        System.out.println("please");
                        wordsList = (ArrayList<GREWord>)inFromServer.readObject();
                        System.out.println("okkk?? "+wordsList.size());
                        //Work of this page is done so The running thread in server will be made off 
                        String offThread = "client:STOP";
                        outToServer.writeObject(offThread);
                        outToServer.flush();
                    } catch (Exception ex) {
                        System.out.println("inFromServer e kahni arraylist");
                    }

                    System.out.println("Successful?");
                    main = new MAINController();
                    main.showHome(myStage,wordsList,newUser);
                }
                else{
                    System.out.println("Pass mile nai");
                    confirm.setText("Incorrect usename or password");
                    confirm.setTextFill(Color.rgb(21, 117, 84));
                    pass.clear();
                }
            }else{
                confirm.setText("No such username"); //may need to change
                confirm.setTextFill(Color.rgb(21, 117, 84));
                pass.clear();
            }
        
            
        
    }
    
    @FXML //clicked Login
    private void handleButtonAction(ActionEvent event) {
        //questionController = new QuestionPageController();
        System.out.println(username.getText()+" "+ pass.getText());
        //ekhan theke client er ouputstream e write korbe (function call ?? )
        //******file-Check******
        String directory = "D:\\Programming\\JAVA2\\loginVersion2\\LoginInformation\\";
        try(BufferedReader br = new BufferedReader(new FileReader(directory + username.getText()+".txt" ))) {
           
            System.out.println("file paisi");
            String password ;// = pass.getText();
            
            try{
                if((password = br.readLine())!= null && password.equals(pass.getText())){ //server er moddhe
                    Stage myStage = (Stage)login.getScene().getWindow();
                    
                    if(wordsList==null) System.out.println("null in documentController");
                    newUser = new User(wordsList.size()); //Comment out this and see what happens(Should do*)
                    //*******User er file theke read kora lagbe********
                    String way = "D:\\Programming\\JAVA2\\loginVersion2\\UserSituationInformation\\"; 
                    try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(way+username.getText()+".txt"))){
                        newUser =(User) ois.readObject();
                    }catch(Exception e){
                        System.out.println("Exception in documentController fileRead e "+e.getMessage());
                    }
                    way = "D:\\Programming\\JAVA2\\loginVersion2\\ArrayListInformation\\";
                    try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(way+username.getText()+".txt"))){
                        wordsList =(ArrayList<GREWord>) ois.readObject();
                    }catch(Exception e){
                        System.out.println("Exception in documentController arraylist fileRead e "+e.getMessage());
                    }
                    main.showHome(myStage,wordsList,newUser);
                    

                }
                else{
                    System.out.println("pass mile nai document Controller");
                    confirm.setText("Incorrect usename or password");
                    confirm.setTextFill(Color.rgb(21, 117, 84));
                    pass.clear();
                }
            }catch(IOException e){
                confirm.setText("Incorrect usename or password");
                confirm.setTextFill(Color.rgb(21, 117, 84));
                pass.clear();
                System.out.println("document Controller Vitorer try "+e.getMessage());
            }
            
            
        } catch (FileNotFoundException ex) {
            pass.clear();
            confirm.setText("Incorrect usename or password");
            confirm.setTextFill(Color.rgb(21, 117, 84));
            System.out.println("File paoa jay nai document Controller");
            
        } catch (IOException ex) {
            pass.clear();
            System.out.println("IOexceptn docuumnt cntrllrs");
           
        }
    }
    @FXML
    private void signUpbutton(ActionEvent e){
       // System.out.println("Dilasd");
        Stage stage = (Stage) signUp.getScene().getWindow();
        SignUPController signup = new SignUPController();
        signup.signUpScreen(stage);
    }
    //***just for checking purpose if got the real reference
    public void showPriority(){
        if(wordsList==null) System.out.println("null in showprioroty()");
        else{
            for(int i=0;i<wordsList.size();i++){
                GREWord temp = wordsList.get(i);
                System.out.println("Word : "+temp.getWord()+" Priority : "+temp.getPriority());
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        passTextField.setVisible(false);
        passTextField.setManaged(false);
        passTextField.managedProperty().bind(showpassCheckBox.selectedProperty());
        passTextField.visibleProperty().bind(showpassCheckBox.selectedProperty());
        pass.managedProperty().bind(showpassCheckBox.selectedProperty().not());
        pass.visibleProperty().bind(showpassCheckBox.selectedProperty().not());
        passTextField.textProperty().bindBidirectional(pass.textProperty());
       
        //For passing references to Individuals 
        sourceFile = new FileDeilimiter();
        wordsList = sourceFile.getWordList(); //sobar jonno source theke raw copy neoa pore notun kore edit bosano
        if(wordsList==null) System.out.println("null in initializer");
        else System.out.println("No null in action in FXMLdocumentController in initialize");
        //new Thread(this).start();
    }    
    public void loginScreen(Stage old){
        Stage loginStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        //loader.setController(this);
        System.out.println("loginScreen porjonto");
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("root load e problem in Document controller loginscreen");
        }
        Scene scene = new Scene(root);
        loginStage.setScene(scene);
        old.close();
        loginStage.show();
        
    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        int i=0;
        while(true){
            try {
                showPriority();
                i++;
                Thread.sleep(5000);
                if(i==10) {
                    System.out.println("breaking..");
                    break;
                }
            } catch (InterruptedException ex) {
                //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Run e problem document controller");
            }
        }
    }
    
}
