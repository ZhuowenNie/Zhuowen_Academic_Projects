/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package information_retrieval;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mengqingwang
 */
public class InfoMap {
    
    private Map<String, Information> infoMap;
    
    public InfoMap(){
        infoMap = new HashMap<String, Information>(); 
    }

    public Map<String, Information> getInfoMap() {
        return infoMap;
    }

    public void setInfoMap(Map<String, Information> infoMap) {
        this.infoMap = infoMap;
    }
    
    public Information addInformation(String source, int frequency, int hitRate){
        Information information = new Information(frequency, hitRate);
        infoMap.put(source, information);
        return information;
    }
    
    public Information updateInfoMap(String source) {
        Information information = find(source);
        if(information != null){
            return increaseInfoFrequency(information);
        }
        else {
            return addInformation(source, 1, 0);
        }
    }
    
    public Information find(String source){
        Information information = infoMap.get(source);
        return information;
    }
    
    public Information increaseInfoRate(Information information){
        information.addHitRate();
        return information;
    }
    
    public Information increaseInfoFrequency(Information information){
        information.addFrequency();
        return information;
    }
}
