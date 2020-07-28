/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview.directory;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;

/**
 *
 * @author Antu
 */
public class TreeResource{
    private static TreeResource treeResource = new TreeResource();
    @FXML
    private TreeView<FileValue> directoryTree;
    
    private TreeResource(){
        directoryTree = new TreeView<>();
        directoryTree.setLayoutX(10);
        directoryTree.setLayoutY(83);
        directoryTree.setPrefHeight(367);
        directoryTree.setPrefWidth(210);
        mainTree();
    }
    
    public static TreeResource getResourceInstance (){
        return treeResource;
    }
    public TreeView<FileValue> getTreeViewInstance(){
        return directoryTree;
    }
    private void mainTree(){
        String hostName = "Antu";
        try{
            hostName=InetAddress.getLocalHost().getHostName();
        }catch(UnknownHostException e){
            System.out.println("In HostName");
        };
        
        FileValue mainHost = new FileValue(new File(hostName).toPath(), hostName, 0, null, null);
        
        TreeItem<FileValue> rootNode=new TreeItem<>(mainHost);
        
        Iterable<Path> directories=FileSystems.getDefault().getRootDirectories();
        
//        int _count =0;
       
        for(Path path:directories){
//            _count++;
//            if(_count >=4){
                System.out.println("Loop : "+path.toString());
                FileValue childHost = new FileValue(path,path.toString(), 0, null, null);
                createTree(childHost, rootNode);
//            }
        }
        rootNode.setExpanded(true);
        directoryTree.setRoot(rootNode);
    }
    
    public void activateClickingTree(TableViewResource tableResource, TextField dirField){
        directoryTree.getSelectionModel().selectedItemProperty()
                .addListener((observable,oldValue,newValue)->{
            FileValue tempValue = newValue.getValue();
            System.out.println("Name : "+tempValue.getFileName());
            String dir = tempValue.getFilePath().toAbsolutePath().toString(); 
            System.out.println("in activateClick, now selected : "+dir);
            ItemsList.directoryField = dir;
            dirField.setText(dir);
            Path tempPath = tempValue.getFilePath();
            ItemsList.fillUpList(tempPath);
            tableResource.populateTableView();
            System.out.println("All done");
        });
    }
    
    public void activateClickingTree(TilesViewResource tileResource, TextField dirField){
        directoryTree.getSelectionModel().selectedItemProperty()
                .addListener((observable,oldValue,newValue)->{
            FileValue tempValue = newValue.getValue();
            System.out.println("TILES Name : "+tempValue.getFileName());
            String dir = tempValue.getFilePath().toAbsolutePath().toString();
            ItemsList.directoryField = dir;
            dirField.setText(dir);
            
            Path tempPath = tempValue.getFilePath();
            ItemsList.fillUpList(tempPath);
            tileResource.populateTileView(dirField);
            System.out.println("All done");
        });
    }
    
    private TreeItem<FileValue> createTree(FileValue dir, TreeItem<FileValue> parent) {
        TreeItem<FileValue> root = new TreeItem<>(dir);
        root.setExpanded(false);
        File directory = dir.getFilePath().toFile();
        
        File[] files = directory.listFiles();
        if(files != null){
            for (File file : files) {
                if (file.isDirectory()) {
                    createTree(getFileValue(file),root);
                } else {
                    //System.out.println("     file:" + file.getCanonicalPath());
                    root.getChildren().add(new TreeItem<>(getFileValue(file)));
                }

            }
        }
        parent.getChildren().add(root);
        /*
        if(parent==null){
            directoryTree.setRoot(root); //redundant
        } else {
            parent.getChildren().add(root);
        }
        */
        return root;
    }    
    
    private FileValue getFileValue(File file){
        //Path dir = Paths.get(file.getAbsolutePath()); //How'bout using Absolute Path
        Path dir = file.toPath();
        String fileName = file.getName();
//        long fileSize = 0;
//        ImageView fileIcon = null;
//        String createDate = null;
        FileValue currentDir = new FileValue(dir, fileName,0,null,null);
        
        return currentDir;
    }

    
}
