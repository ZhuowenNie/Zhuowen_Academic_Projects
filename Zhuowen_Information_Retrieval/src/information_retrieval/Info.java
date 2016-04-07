/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package information_retrieval;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class Info {
    
    
    private String source;
    private int frequency;
    private int rank;
    
    public Info(String source, int rank, int frequency){
        this.source = source;
        this.frequency = frequency;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    public void addFrequency() {
        this.frequency++;
    }

    public void addRank() {
        this.rank++;
    }
    
    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    @Override
    public String toString() {
        return source;
    }
    
    public Info(){
        
    }
}



