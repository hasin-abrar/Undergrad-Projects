/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_xml_parse.comments;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Antu
 */
public class UserHandler extends DefaultHandler {
   boolean bFirstName = false;
   boolean bLastName = false;
   boolean bNickName = false;
   boolean bMarks = false;
   DB_Controller dbController = new DB_Controller();

   @Override
   public void startElement(String uri, 
   String localName, String qName, Attributes attributes)
        throws SAXException {
            if (qName.equalsIgnoreCase("row")) {
                String id = attributes.getValue("Id");
                System.out.println("ID No : " + id);
                String postId = attributes.getValue("PostId");
//                System.out.println("PostId value : "+postId);
                String score = attributes.getValue("Score");
//                System.out.println("Score value : "+score);
                String text = attributes.getValue("Text");
//                System.out.println("Text value : "+text);
                String createDate = attributes.getValue("CreationDate");
//                System.out.println("CreateDate value : "+createDate);
                String userDisplayName = attributes.getValue("UserDisplayName");
//                System.out.println("UserName value : "+userDisplayName);
                String userId = attributes.getValue("UserId");
//                System.out.println("UserId value : "+userId);
                
                dbController.updateDB(id, postId, score, text, createDate, userDisplayName, userId);
            }     
   }
   /*
   @Override
   public void endElement(String uri, 
   String localName, String qName) throws SAXException {
      if (qName.equalsIgnoreCase("student")) {
         System.out.println("End Element :" + qName);
      }
      else if(qName.equalsIgnoreCase("nowwhat")){
          System.out.println("End Element :" + qName);
      }
      
   }

   @Override
   public void characters(char ch[], 
      int start, int length) throws SAXException {
      if (bFirstName) {
         System.out.println("First Name: " 
            + new String(ch, start, length));
         bFirstName = false;
      } else if (bLastName) {
         System.out.println("Last Name: " 
            + new String(ch, start, length));
         bLastName = false;
      } else if (bNickName) {
         System.out.println("Nick Name: " 
            + new String(ch, start, length));
         bNickName = false;
      } else if (bMarks) {
         System.out.println("Marks: " 
            + new String(ch, start, length));
         bMarks = false;
      }
   }
   */
}
