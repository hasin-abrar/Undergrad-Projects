/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInformation;

import java.io.Serializable;

/**
 *
 * @author _bazinga
 */
public class User implements Serializable{
    private String name;
    private int master,review,learning;
    private int listSize;  //ektu buijha
    
    public User(int size) {
        this.listSize = size;
    }

    
    
    public void setName(String name) {
        this.name = name;
    }

    public void setMaster(int master) {
        if(master<=listSize)this.master = master;
    }

    public void setReview(int review) {
        if(review<=listSize)this.review = review;
    }

    public void setLearning(int learning) {
        if(learning<=listSize)this.learning = learning;
    }

    public String getName() {
        return name;
    }

    public int getMaster() {
        return master;
    }

    public int getReview() {
        return review;
    }

    public int getLearning() {
        return learning;
    }
}
