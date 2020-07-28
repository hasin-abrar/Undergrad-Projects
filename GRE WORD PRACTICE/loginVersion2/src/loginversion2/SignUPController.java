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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wordPackage.FileDeilimiter;
import wordPackage.GREWord;

/**
 * FXML Controller class
 *
 * @author _bazinga
 */
public class SignUPController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    ArrayList<GREWord> wordsList;// = FileDeilimiter.wordList;
    FileDeilimiter sourceFile;
    User firstUser;
    @FXML
    private TextField user ;
    @FXML
    private PasswordField pass;
    @FXML
    private PasswordField confirmPass;
    @FXML
    private TextField email;
    @FXML
    private Button signUpButton;
    @FXML
    private Button alreadyButton;
    @FXML
    private Label response;
    
    ObjectInputStream inFromServer = null;
    ObjectOutputStream outToServer = null;
    
    public Socket clientSocket ;
    
    public SignUPController() {
        try {
            clientSocket = new Socket("localhost", 33333);
            outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            inFromServer = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            System.out.println("errror in constructor");
        }
    }
    
    
    
    @FXML
    public void signUp(ActionEvent ae){
        if(user.getText().isEmpty() || pass.getText().isEmpty() || confirmPass.getText().isEmpty() ){
            response.setText("Please fill up the recommended boxes");
        }
        else{
            if(pass.getText().equals(confirmPass.getText())){
                try {
                    String signUp = user.getText()+":FILE";
                    outToServer.writeObject(signUp);
                    System.out.println("writebyte ok");
                    outToServer.flush();
                    String isExisting =(String) inFromServer.readObject();
                    if(isExisting.equals("Username already exist")){
                        response.setText("Username already exist");
                    }else{
                        String userPass = pass.getText();
                        outToServer.writeObject(userPass);
                        outToServer.flush();
                        firstUser = new User(wordsList.size());
                        firstUser.setName(user.getText());
                        firstUser.setLearning(0);
                        firstUser.setMaster(0);
                        firstUser.setReview(0);
                        System.out.println("User creating ok");
                        outToServer.writeObject(firstUser);
                        outToServer.flush();
                        System.out.println("ok ples");
                        outToServer.writeObject(wordsList);
                        outToServer.flush();
                        String offThread = "client:STOP";
                        outToServer.writeObject(offThread);
                        outToServer.flush();
                        System.out.println("ok?");
                        Stage old = (Stage) signUpButton.getScene().getWindow();
                        MAINController main = new MAINController();
                        main.showHome(old,wordsList,this.firstUser);
                    }
                }catch (Exception ex) {
                    System.out.println("Exception in signUpcontroller");
                }
                /*
                    //********lagbe na
                    String directory = "D:\\Programming\\JAVA2\\loginVersion2\\LoginInformation\\";
                    File newUser = new File(directory+user.getText()+".txt");
                    if(newUser.exists()){
//                        response.setText("Username already exist");
                    }
                    else {
                        try {
                            newUser.createNewFile();
                            //**Entry pass
                            String userPass = pass.getText();//////
                            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newUser))){
                                oos.writeObject(userPass);
                                oos.flush();
                            }catch(Exception e){
                                System.out.println("Exception in writing signup username file");
                            }
                            MAINController main = new MAINController();
                            Stage old = (Stage) signUpButton.getScene().getWindow();
                            firstUser = new User(wordsList.size());
                            firstUser.setName(user.getText());
                            firstUser.setLearning(0);
                            firstUser.setMaster(0);
                            firstUser.setReview(0);
                            //*****writing source file for a user******
                            writeFile(firstUser, wordsList);
/*
String way = "D:\\Programming\\JAVA2\\loginVersion2\\UserSituationInformation\\";
File userFile = new File(way+user.getText()+".txt");
userFile.createNewFile();
try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))){
oos.writeObject(firstUser);
oos.flush();
}catch(Exception e){
System.out.println("Exception in oos in signUpCOntroller");
}
System.out.println("Writinig successful");
main.showHome(old,wordsList,this.firstUser);
                        } catch (IOException ex) {
                            response.setText("Error at opening new ID");
                            System.out.println("Error");
                            //Logger.getLogger(SignUPController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }  */
            }else{
                response.setText("Passwords did not match");
            }
        }
    }
    public void writeFile(User mainUser,ArrayList<GREWord> tempList){
        try{
            if(mainUser== null) System.out.println("null in answerpage writeFile");
            String way = "D:\\Programming\\JAVA2\\loginVersion2\\UserSituationInformation\\";
            File userFile = new File(way+mainUser.getName()+".txt");
            userFile.createNewFile();
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))){
                oos.writeObject(mainUser);
                oos.flush();
            }catch(Exception e){
                System.out.println("Exception in oos in answerPageController");
            }
            
            way = "D:\\Programming\\JAVA2\\loginVersion2\\ArrayListInformation\\";
            File user2File = new File(way+mainUser.getName()+".txt");
            user2File.createNewFile();
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(user2File))){
                oos.writeObject(tempList);
                oos.flush();
            }catch(Exception e){
                System.out.println("Exception in oos arraylist in answerPageController");
            }
            
        }catch(IOException ex){
            System.out.println("File create e problem in SignUpController writeFile()");
            //Logger.getLogger(SignUPController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void signUpScreen(Stage old){
        
        Parent root = null;
        Stage stage = new Stage();
        try {
            root = FXMLLoader.load(getClass().getResource("signUP.fxml"));
        } catch (IOException ex) {
            System.out.println("Error in signupScreen");
            System.exit(0);
            //Logger.getLogger(SignUPController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        old.close();
        stage.show();
    }
    public void signInscreen(ActionEvent ae){
        Stage stage = (Stage)alreadyButton.getScene().getWindow();
        FXMLDocumentController signIn = new FXMLDocumentController();
        signIn.loginScreen(stage);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //For passing references to Individuals || DIRECT copy from sign IN page
        sourceFile = new FileDeilimiter();
        wordsList = sourceFile.getWordList(); //sobar jonno source theke raw copy neoa pore notun kore edit bosano
    }    
    
}
