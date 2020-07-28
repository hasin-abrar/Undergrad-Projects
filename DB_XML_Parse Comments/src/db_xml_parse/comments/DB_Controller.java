/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db_xml_parse.comments;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antu
 */
public class DB_Controller {
    ODatabaseDocumentTx db;
    public DB_Controller() {
        String creds = "plocal:F:\\Software NEW\\orientdb-community-2.2.20\\orientdb-community-2.2.20\\databases\\DB_Comments";
        db = new ODatabaseDocumentTx (creds);
        db.open("admin","admin");
        System.out.println("Done at opening");
    }
    int _count = 1;
    /*
    public void queryCheck(){
        //Don't
        long lowLimit = 1 , highLimit = 10000, bound = 10000;
        long target = 72665970;
        boolean enough = false;
        while(!enough){
            String sqlQuery = "select * from Comments where UserId = 242"
                    + " and Id between "+lowLimit+" and "+highLimit ;
            List<ODocument> result = db.query(new OSQLSynchQuery<>(sqlQuery));
            
            for(int i=0;i<result.size();i++){
                ODocument tempODocument = result.get(i);
                System.out.println("Creation Date : "+ tempODocument.field("CreationDate") +
                        " Id : "+ tempODocument.field("Id") + " PostId : "+ tempODocument.field("PostId")
                        + " Score : "+tempODocument.field("Score") + " Text : "+ tempODocument.field("Text")
                        + " UserDisplayName : " + tempODocument.field("UserDisplayName")
                        + " UserId : "+tempODocument.field("UserId")
                );
            } 
            
            if(highLimit < target){
                if(highLimit + bound >  target){
                    lowLimit = highLimit;
                    highLimit = target;
                }
                else{
                    lowLimit = highLimit+1;
                    highLimit+= bound;
                }
            }
            else{
                enough = true;
            }
            
//            System.out.println("************************************************ "+ _count++);
        }
    }
    */
    long startTime, completeTime;
    public void dbQuery_1(){
        // Avg of of PostId between Score 1 and 1000 and between id 1 and 1000000 = 10^6 
        startTime = System.currentTimeMillis();
        String sqlQuery = "select avg(PostId) as Average_PostId from Comments where Score between 1 and 100"
                + "and Id between 1 and 1000000 ";
        
        List<ODocument> result = db.query(new OSQLSynchQuery<>(sqlQuery));
        
        ODocument temp = result.get(0);
        System.out.println("Average of PostId : "+temp.field("Average_PostId"));
        
        db.close();
        completeTime = System.currentTimeMillis();
        long completionTime = (completeTime - startTime)/1000;
        System.out.println("Completion Time : " + completionTime + " seconds");
    }
    
    public void dbQuery_2(){
        //Find the id, postid, userid whose score is in the range of 30 and 500, creation date is before "2009-01-12"
        //and id < 1000000
        startTime = System.currentTimeMillis();
        long lowLimit = 1 , highLimit = 10000, bound = 10000;
        long target = 1000000;
        boolean enough = false;
        while(!enough){
            String sqlQuery = "select Id, PostId, UserId from Comments where "
                    + " Id between "+lowLimit+" and "+highLimit 
                    + " and Score between 30 and 500"
                    + " and CreationDate.format('yyyy-M-dd') < \"2009-01-12\" "; 
                
        
            List<ODocument> result = db.query(new OSQLSynchQuery<>(sqlQuery));
            
            for(int i=0;i<result.size();i++){
                System.out.println("Id : "+ result.get(i).field("Id") + " PostId : " + result.get(i).field("PostId")
                         + " UserId : "+ result.get(i).field("UserId")
                );
            }
            if(highLimit < target){
                if(highLimit + bound >  target){
                    lowLimit = highLimit+1;
                    highLimit = target;
                }
                else{
                    lowLimit = highLimit+1;
                    highLimit+= bound;
                }
            }
            else{
                enough = true;
            }
            
        }
        completeTime = System.currentTimeMillis();
        long completionTime = (completeTime - startTime)/1000;
        System.out.println("Completion Time : " + completionTime + " seconds");
    }
    
    public void dbQuery_3(){
        //Find the Id, UserId and the creation date of the user who got the maximum score 
        startTime = System.currentTimeMillis();
        long lowLimit = 1 , highLimit = 10000, bound = 10000;
        long target = 1000000, maxScore = -1;
        boolean enough = false;
        while(!enough){
            String sqlQuery = "select max(Score) as MaxScore from Comments " +
                    " where Id between "+lowLimit+" and "+highLimit ;
            List<ODocument> result = db.query(new OSQLSynchQuery<>(sqlQuery));
            
            ODocument resDocument = result.get(0);
            maxScore = Long.max(maxScore, resDocument.field("MaxScore"));
            
            
            if(highLimit < target){
                if(highLimit + bound >  target){
                    lowLimit = highLimit + 1;
                    highLimit = target;
                }
                else{
                    lowLimit = highLimit+1;
                    highLimit+= bound;
                }
            }
            else{
                enough = true;
            }
            

        }
        
        lowLimit = 1 ;
        highLimit = 10000;
        bound = 10000;
        target = 1000000;
        enough = false;
        long _check = (System.currentTimeMillis() - startTime)/1000;
        System.out.println("Max Score : "+ maxScore+ " till Now : "+_check+ " seconds");
        while(!enough){
            String query = "select Id, UserId, CreationDate from Comments"
                    +" where Id between "+lowLimit+" and "+highLimit 
                    + " and Score = "+maxScore ;
            List<ODocument> result = db.query(new OSQLSynchQuery<>(query));
            
            for(int i=0;i<result.size();i++){
                System.out.println("Id : "+ result.get(i).field("Id") + " UserId : " + result.get(i).field("UserId")
                        + " CreationDate : "+ result.get(i).field("CreationDate")
                );
            }
            
            if(highLimit < target){
                if(highLimit + bound >  target){
                    lowLimit = highLimit + 1;
                    highLimit = target;
                }
                else{
                    lowLimit = highLimit+1;
                    highLimit+= bound;
                }
            }
            else{
                enough = true;
            }
            
        }
        completeTime = System.currentTimeMillis();
        long completionTime = (completeTime - startTime)/1000;
        System.out.println("Completion Time : " + completionTime + " seconds");
    }
    
    public void dbQuery_4(){
        //Find the number of users that commented in each year in each post. Print Year, PostId, total users that have commented
        //and avg score of those persons. Print the rows in which at least 10 users have commented and having avg socre of at least 50
        startTime = System.currentTimeMillis();
        long lowLimit = 1 , highLimit = 1000000, bound = 10000;
        long target = 1000000;
        boolean enough = false;
        while(!enough){
            String sqlQuery = "select Year ,PostId, Total_Comments, AVG_Score from \n" +
                    "(select CreationDate.format('yyyy') as Year, PostId, count(*) as Total_Comments, avg(Score) as AVG_Score\n" +
                    "from Comments \n" +
                    " where Id between "+lowLimit+" and "+highLimit +
                    " group by Year, PostId\n" +
                    "order by Year\n" +
                    ")\n" +
                    "where Total_Comments >= 10 and AVG_Score >= 50";
                    
        
            List<ODocument> result = db.query(new OSQLSynchQuery<>(sqlQuery));
            
            for(int i=0;i<result.size();i++){
                System.out.println("Year : "+ result.get(i).field("Year") + " PostId : " + result.get(i).field("PostId")
                         +" Total_Comments : "+result.get(i).field("Total_Comments")+ " AVG_Score : "+ result.get(i).field("AVG_Score")
                );
            }
            if(highLimit < target){
                if(highLimit + bound >  target){
                    lowLimit = highLimit + 1;
                    highLimit = target;
                }
                else{
                    lowLimit = highLimit+1;
                    highLimit+= bound;
                }
            }
            else{
                enough = true;
            }
            completeTime = System.currentTimeMillis();
            long completionTime = (completeTime - startTime)/1000;
            System.out.println("Completion Time : " + completionTime + " seconds");
        }
        
    }
    
    public void dbQuery_5(){
        //Find the maximum score of a user in every post where postid is even. Print the UserId, PostId, and maximum score. Make sure
        // null value is not printed and display the output in ascending order of userid. Display only the rows who have max score of 
        // at least 30
        startTime = System.currentTimeMillis();
        long lowLimit = 1 , highLimit = 1000000, bound = 100000;
        long target = 1000000;
        boolean enough = false;
        while(!enough){
            String sqlQuery = "select UserId, PostId, Max_Score from\n" +
                    "( select UserId, PostId, max(Score) as Max_Score\n" +
                    "from Comments\n" +
                    " where Id between "+lowLimit+" and "+highLimit+
                    " and UserId is not NULL\n" +
                    "and PostId % 2 = 0\n" +
                    "group by UserId,PostId\n" +
                    ")\n" +
                    "where Max_Score > 30" 
                    + " order by UserId" ;
        
            List<ODocument> result = db.query(new OSQLSynchQuery<>(sqlQuery));
            
            for(int i=0;i<result.size();i++){
                System.out.println("UserId : "+ result.get(i).field("UserId") +
                         " PostId : "+ result.get(i).field("PostId") + " MaxScore : "+ result.get(i).field("Max_Score")
                );
            }
            if(highLimit < target){
                if(highLimit + bound >  target){
                    lowLimit = highLimit + 1;
                    highLimit = target;
                }
                else{
                    lowLimit = highLimit+1;
                    highLimit+= bound;
                }
            }
            else{
                enough = true;
            }
            
        }
        completeTime = System.currentTimeMillis();
        long completionTime = (completeTime - startTime)/1000;
        System.out.println("Completion Time : " + completionTime + " seconds");
    }
    
    /*
    public void dbQuery(){
        try{
            String sqlQuery = "select count(*) as Total from Comments where Id BETWEEN 1 and 100000 ";
//            String sqlQuery = "select count(Distinct(Score)) as Total from Comments";
            List<ODocument> result = db.query(new OSQLSynchQuery<>(sqlQuery));
            ODocument temp = result.get(0);
            System.out.println("Lets see : "+temp.field("Total"));
            
            for(int i=0;i<result.size();i++){
//                System.out.println("Lets see : "+result.get(i).toString());
                ODocument tempODocument = result.get(i);
                System.out.println("Creation Date : "+ tempODocument.field("CreationDate") +
                        " Id : "+ tempODocument.field("Id") + " PostId : "+ tempODocument.field("PostId")
                        + " Score : "+tempODocument.field("Score") + " Text : "+ tempODocument.field("Text")
                        + " UserDisplayName : " + tempODocument.field("UserDisplayName")
                        + " UserId : "+tempODocument.field("UserId")
                );
            }

            //System.out.println("Ok");
            
            
            String s_myClass = "Comments";
            
            ORecordIteratorClass<ODocument> i_myClass = db.browseClass(s_myClass);

            if(i_myClass.hasNext())
            { 

                 for(ODocument d: i_myClass)
                 {
                      System.out.println(d.toString());
                 }
            }
            
        }finally {
            db.close();
        }
    }
    */
    public void updateDB(String id, String postId, String score, String text, String createDate, String userDispName, String userId){
        
        ODocument comment = new ODocument("Comments");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd'T'hh:mm:ss.SSS");
//        String dateInString = "31-08-1982T10:20:56.897";
//        String dateInString = createDate;
        
        Date dateTime = null;
        try {
            dateTime = sdf.parse(createDate);
        } catch (ParseException ex) {
            System.out.println("Error in Formatting date");
            ex.printStackTrace();
        }
        Long cId = Long.parseLong(id);
        Long cPostId = Long.parseLong(postId);
        Long cScore = Long.parseLong(score);
        Long cUserId = null;
        if(userId != null){
            cUserId = Long.parseLong(userId);
        }
        
        db.begin();
        
        comment.field("Id", cId);
        comment.field("PostId", cPostId);
        comment.field("Score", cScore);
        comment.field("Text", text);
        comment.field("CreationDate", dateTime);
        comment.field("UserDisplayName", userDispName);
        comment.field("UserId", cUserId);
        
        comment.save();
        
        db.commit();
    }
    
}
