/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_xml_parse.comments;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author Antu
 */
public class DB_XML_ParseComments {

    /**
     * @param args the command line arguments
     */
    
    //****MAIN PROJECT*****
    
    public static void main(String[] args) {
        DB_Controller dbController = new DB_Controller();

        dbController.dbQuery_5();
        System.out.println("Finished");
        /*
        try {	
//            File inputFile = new File("XMLFiles/Comments-01.xml");
//            File folder = new File("F:\\Comments\\Error");
            File folder = new File("F:\\Comments\\1st Part");
            File[] listOfFiles = folder.listFiles();
//            File myFile = new File("word.txt");
//            System.out.println("Attempting to read from file in: "+inputFile.getCanonicalPath());
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            UserHandler userhandler = new UserHandler();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    System.out.println("File " + listOfFiles[i].getName());
//                    File inputFile = new File("F:\\Comments\\Error\\"+listOfFiles[i].getName());
                    File inputFile = new File("F:\\Comments\\1st Part\\"+listOfFiles[i].getName());
                    
                    saxParser.parse(inputFile, userhandler);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
    
}
