/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author _bazinga
 */
public class FileDeilimiter implements Serializable{

    /**
     * @param args the command line arguments
     */
    public  ArrayList<GREWord> wordList = new ArrayList<>(); //DIAMOND Interface ?
    public ArrayList<GREWord> getWordList(){
        return this.wordList;
    }
    //public static void main(String[] args) throws IOException
    public FileDeilimiter(){
        try {
            // TODO code application logic here
            File checkFile = new File("D:\\Programming\\JAVA2\\FileDeilimiter\\File\\GREWORD.txt");
            checkFile.createNewFile();
            /* PrintWriter out = new PrintWriter(checkFile);
            out.write("This\nis");
            out.println();
            out.write("pera");
            out.close(); */
            BufferedReader br = new BufferedReader(new FileReader(checkFile));
            String line = null;
            
            int p =0;
            while((line = br.readLine()) != null){
                
                String[] full = line.split("#");
                for(String block : full){
                    GREWord mainWord = new GREWord(); //Declaring here notice kore nahole overwrite hoya jay arraylist e
//                    String g = "";
                    String[] word = block.split(";",2);
                    System.out.println("W[0] "+word[0]+" w[1] "+word[1]);
                    String g = word[1];
                    String[] meaning = g.split(":",2);
                    String synonym = meaning[1];
                    System.out.println(word[0]+" "+meaning[0]+" "+synonym);
                    mainWord.setWord(word[0]);
                    System.out.println("ok");
                    mainWord.setMeaning(meaning[0]);
                    mainWord.setSynonym(synonym);
                    wordList.add(mainWord);
                }
            }
            br.close();
            System.out.println("Size : "+ wordList.size());
            for(int i=0;i<wordList.size();i++){
                GREWord temp =  wordList.get(i); //temp er reference neoa ta kheal kore//WordList age create kora chilo new
                System.out.println(temp.getWord()+" "+temp.getMeaning()+" "+temp.getSynonym()); //dia ejonno
            }                                                                               //problem hoy nai
            /*
            //**********making random call*******
            System.out.println("***********THROUGH RANDOM CALLS***********");
            for(int i=0;i<30;i++){
                Random rand = new Random();
                int a = rand.nextInt(wordList.size());
                int b = rand.nextInt(wordList.size());
                int c = rand.nextInt(wordList.size());
                GREWord showWord = findMax(wordList.get(a), findMax(wordList.get(c), wordList.get(b)));
                System.out.println(showWord.getWord()+" "+showWord.getMeaning()+" "+showWord.getSynonym()+" "+showWord.getPriority());
                showWord.decreasePriority();
            }
            */
        } catch (IOException ex) {
            //Logger.getLogger(FileDeilimiter.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("FileDelimeter e ERROR");
            System.exit(0);
        }
        
    }
    public static GREWord findMax(GREWord a, GREWord b){
        if(a.getPriority() > b.getPriority()){
            return a;
        }
        return b;
    }
}
