/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverMain;

import UserInformation.User;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import wordPackage.GREWord;

/**
 *
 * @author _bazinga
 */
public class serverFileSource implements Runnable{
    public static void main(String[] args) {
        new serverFileSource();
    }

    public serverFileSource() {
        new Thread(this).start();
    }
    
    @Override
    public void run() {
        ServerSocket welcomeSocket = null;
        
        try {
            try {
                welcomeSocket = new ServerSocket(33333);
                System.out.println("Error here?");
            } catch (IOException ex) {
                System.out.println("Error wecomeScoket serverFileSOurce");
            }
            
            while(true)
            {
                Socket connectionSocket = new Socket() ;
                try {
                    
                    connectionSocket = welcomeSocket.accept();
                    
                    WorkerThread wt = new WorkerThread(connectionSocket);
                    Thread t = new Thread(wt);
                    t.start();
                    
                    
                } catch (IOException ex) {
                    System.out.println(".acept e Server FileaSOurce ager tay caught");
                }
            }
        }catch (Exception e){
            System.out.println("main try in serverFile source");
        }
    }
        
        
        public class WorkerThread implements Runnable
    {
        private Socket connectionSocket;
    
        WorkerThread(Socket connectionSocket)
        {
            this.connectionSocket = connectionSocket ;
        }
    
        @Override
        public void run()
        {
            System.out.println("Entry");
            String client;
            String clientPass;
            ArrayList<GREWord> userList = null;
            User newUser= null;   
            ObjectOutputStream oos = null;
            ObjectInputStream ois = null;
            try {
                oos = new ObjectOutputStream(connectionSocket.getOutputStream());
                ois = new ObjectInputStream(connectionSocket.getInputStream());
            } catch (IOException ex) {
                //Logger.getLogger(serverFileSource.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("error");
            }
            while(true)
            {
                
                try{
                    System.out.println("ekhane asi");
                    client = (String) ois.readObject();
                    System.out.println("ekhnae nai");
                    String[] chck = client.split(":");
                    String clientName = chck[0];
                    String checkStatus = chck[1];
                    System.out.println("name : "+clientName+" status : "+checkStatus);
                    if(checkStatus.equals("check")){
                        System.out.println("client name : "+clientName);
                        String directory = "D:\\Programming\\JAVA2\\loginVersion2\\LoginInformation\\";
                        File tempUser = new File(directory+clientName+".txt");
                        if(tempUser.exists()){
                            try(ObjectInputStream fileStream = new ObjectInputStream(new FileInputStream(directory + clientName+".txt"))) {
                                clientPass = (String)fileStream.readObject();
                                System.out.println("client Pass : "+clientPass);
                                oos.writeObject(clientPass);
                                System.out.println("Wrote ok");
                            }catch (FileNotFoundException ex) {
                                System.out.println("bar bar print na please :|");
                                oos.writeObject("#NOSUCHNAME#"); // :: dia lekha
                                //oos.close();
                            }
                        }else{
                            oos.writeObject("#NOSUCHNAME#");
                            System.out.println("ok?");
                        }
                    }else if(checkStatus.equals("ok")){ 
                        System.out.println("success login");
                        String way = "D:\\Programming\\JAVA2\\loginVersion2\\UserSituationInformation\\"; 
                        try(ObjectInputStream userData = new ObjectInputStream(new FileInputStream(way+clientName+".txt"))){
                            newUser =(User) userData.readObject();
                        }catch(Exception e){
                            System.out.println("Exception in severFileSource fileRead e "+e.getMessage());
                        }
                        oos.writeObject(newUser);
                        oos.flush();
                        System.out.println("Success here too");
                        way = "D:\\Programming\\JAVA2\\loginVersion2\\ArrayListInformation\\";
                        try(ObjectInputStream listData = new ObjectInputStream(new FileInputStream(way+clientName+".txt"))){
                            userList =(ArrayList<GREWord>) listData.readObject();
                        }catch(Exception e){
                            System.out.println("Exception in severFileSource arraylist fileRead e "+e.getMessage());
                        }
                        oos.writeObject(userList);
                        oos.flush();
                        System.out.println("ok else");
                    }else if(checkStatus.equals("FILE")){
                        String directory = "D:\\Programming\\JAVA2\\loginVersion2\\LoginInformation\\";
                        File temppUser = new File(directory+clientName+".txt");
                        if(temppUser.exists()){
                            oos.writeObject("Username already exist");
                            oos.flush();
                        }else{
                            temppUser.createNewFile();
                            oos.writeObject("Give Password");
                            oos.flush();
                            String userPassword = (String)ois.readObject();
                            try(ObjectOutputStream fileOut = new ObjectOutputStream(new FileOutputStream(temppUser))){
                                fileOut.writeObject(userPassword);
                                fileOut.flush();
                            }catch(Exception e){
                                System.out.println("Exception in writing signup username file in server");
                            }
                            
                            User mainUser = (User)ois.readObject();
                            String way = "D:\\Programming\\JAVA2\\loginVersion2\\UserSituationInformation\\";
                            System.out.println("Client : "+clientName+" user : "+mainUser.getName());
                            File mainUserFile = new File(way+mainUser.getName()+".txt");
                            mainUserFile.createNewFile();
                            try(ObjectOutputStream mainUserStream = new ObjectOutputStream(new FileOutputStream(mainUserFile))){
                                mainUserStream.writeObject(mainUser);
                                mainUserStream.flush();
                            }catch(Exception e){
                                System.out.println("Exception in mainuserstream in serverfile");
                            }
                            userList = (ArrayList<GREWord> )ois.readObject();
                            way = "D:\\Programming\\JAVA2\\loginVersion2\\ArrayListInformation\\";
                            File ListFile = new File(way+mainUser.getName()+".txt");
                            ListFile.createNewFile();
                            try(ObjectOutputStream listStream = new ObjectOutputStream(new FileOutputStream(ListFile))){
                                listStream.writeObject(userList);
                                listStream.flush();
                            }catch(Exception e){
                                System.out.println("Exception in oos arraylist in answerPageController");
                            }
                            System.out.println("Say done");
                        }
                    }else if(checkStatus.equals("UPDATE")){
                        User mainUser = (User) ois.readObject();
                        String way = "D:\\Programming\\JAVA2\\loginVersion2\\UserSituationInformation\\"; 
                        File userFile = new File(way+mainUser.getName()+".txt");
                        try(ObjectOutputStream mainUserStream = new ObjectOutputStream(new FileOutputStream(userFile))){
                            mainUserStream.writeObject(mainUser);
                            mainUserStream.flush();
                        }catch(Exception e){
                            System.out.println("Exception in mainUserStream in user serverfile");
                        }
                        userList = (ArrayList<GREWord> )ois.readObject();
                        way = "D:\\Programming\\JAVA2\\loginVersion2\\ArrayListInformation\\"; 
                        File user2File = new File(way+mainUser.getName()+".txt");
                        try(ObjectOutputStream listStream = new ObjectOutputStream(new FileOutputStream(user2File))){
                            listStream.writeObject(userList);
                            listStream.flush();
                        }catch(Exception e){
                            System.out.println("Exception in mainUserStream in arraylist serverfile");
                        }
                        
                    }else if(checkStatus.equals("STOP")){
                        System.out.println("Everything is done");
                        break;
                    }
                    
                    
                    oos.flush();
//                    oos.close();
//                    ois.close(); // MAY NEED TO COMMENT OUT
                    
                }catch(IOException | ClassNotFoundException e){
                    System.out.println("oos e error/client vagse");
                    break;
                   
                } 
                
            } 
        }
    }
}
