/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package information_retrieval;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import static javafx.scene.input.KeyCode.V;

/**
 *
 * @author mengqingwang
 */
public class InformationMap {
    
    private Map<String,Information> infoMap;
    private TreeMap<String,Information> sortedMap;
    
    public InformationMap(){
        
        infoMap = new HashMap<String,Information>();
        ValueComparator bvc =  new ValueComparator(infoMap);
        sortedMap = new TreeMap<String,Information>(bvc);
        sortedMap.putAll(infoMap);
    }

    public Map<String, Information> getInfoMap() {
        return infoMap;
    }

    public void setInfoMap(Map<String, Information> infoMap) {
        this.infoMap = infoMap;
    }

    
    public TreeMap<String, Information> getSortedMap() {
        return sortedMap;
    }

    public void setSortedMap(TreeMap<String, Information> sortedMap) {
        this.sortedMap = sortedMap;
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
    
    public class ValueComparator implements Comparator<String> {

        Map<String, Information> base;
        

        public ValueComparator(Map<String, Information> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.    
        @Override
        public int compare(String a, String b) {
            if (base.get(a).getHitRate() >= base.get(b).getHitRate()) {
                if (base.get(a).getFrequency() >= base.get(b).getFrequency()) {
                    return -1;
                } else {
                    return 1;
                }
            }else{
                return 1;
            }
              // returning 0 would merge keys
        }
    }
}


