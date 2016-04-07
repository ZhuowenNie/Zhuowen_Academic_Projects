/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package information_retrieval;

/**
 *
 * @author mengqingwang
 */
public class Information {
    

    private int frequency;
    private int hitRate;
    
    public Information(int frequency, int hitRate){
        this.frequency = frequency;
        this.hitRate = hitRate;
    }
    
    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getHitRate() {
        return hitRate;
    }

    public void setHitRate(int hitRate) {
        this.hitRate = hitRate;
    }

    public void addFrequency() {
        this.frequency++;
    }
    
    public void addHitRate() {
        this.hitRate++;
    }
   
    
}
