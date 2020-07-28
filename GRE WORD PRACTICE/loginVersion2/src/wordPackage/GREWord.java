/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordPackage;

import java.io.Serializable;

/**
 *
 * @author _bazinga
 */
public class GREWord implements Serializable{
//    String Word,Meaning,Synonym;
    public String Word = "";
    public String Meaning = "";
    public String Synonym = "";
    int priority = 4;
    boolean newWord = true;
    boolean masteredWord = false;
    boolean learningWord = false;
    boolean reviewWord = false;

    public void setReviewWord(boolean reviewWord) {
        this.reviewWord = reviewWord;
    }

    public boolean isReviewWord() {
        return reviewWord;
    }

    public boolean isNewWord() {
        return newWord;
    }

    public boolean isMasteredWord() {
        return masteredWord;
    }

    public boolean isLearningWord() {
        return learningWord;
    }
    public void setNewWord(boolean newWord) {
        this.newWord = newWord;
    }

    public void setMasteredWord(boolean masteredWord) {
        this.masteredWord = masteredWord;
    }

    public void setLearningWord(boolean learningWord) {
        this.learningWord = learningWord;
    }
    
    /*public GREWord() {
        Word = new String();
        Meaning = new String();
        Synonym = new String();
    } //Direct = "" likhe deoa
    */
    public String getStatus(){
        if(newWord) return "NEW WORD";
        if(learningWord) return "LEARNING";
        return "MASTERED";
    }
    public void setWord(String word){
        this.Word = word;
    }
    public String getWord(){
        return Word ;
    }
    public void setMeaning(String meaning){
        this.Meaning = meaning;
    }
    public String getMeaning(){
        return Meaning ;
    }
    public void setSynonym(String synonym){
        this.Synonym = synonym;
    }
    public String getSynonym(){
        return Synonym ;
    }
    public void decreasePriority(){
        if(this.priority==0) return;
        this.priority--;
    }
    public void increasePriority(){
        if(this.priority==4) return; // 3 ba 4 hole return kora
        this.priority++;
    }
    public int getPriority(){
        return priority;
    }
    public void setPriority(int x){
        this.priority = x;
    }
}
