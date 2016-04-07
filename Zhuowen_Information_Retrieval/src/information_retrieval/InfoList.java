/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package information_retrieval;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author admin
 */
public class InfoList {
    
    private ArrayList<Info> infoList;
    
    public InfoList(){
        infoList = new ArrayList<>();
    }

    public ArrayList<Info> getInfoList() {
        return infoList;
    }

    public void setInfoList(ArrayList<Info> infoList) {
        this.infoList = infoList;
    }
    
    public Info addInfo(String source, int frequency, int rank){
        Info info = new Info(source, frequency, rank);
        infoList.add(info);
        return info;
    }
    
    public Info updateInfoList(String source) {
        Info info = find(source);
        if(info != null){
            return increaseInfoFrequency(info);
        }
        else {
            return addInfo(source, 1, 0);
        }
    }
    
    public Info find(String source){
        for(Info info : infoList){
            if(info.getSource().equals(source)){
                return info;
            }
        }
        return null;
    }
    
    public Info increaseInfoRank(Info info){
        info.addRank();
        return info;
    }
    
    public Info increaseInfoFrequency(Info info){
        info.addFrequency();
        return info;
    }
    
    public ArrayList<Info> sortInfoList(){
        
        return infoList;
    }
    
    public void sortInfo(ArrayList<Info> list, int start, int end){
        if(start < end){
            int pivotindex = partition(list, start, end);
            sortInfo(list, start, pivotindex - 1);
            sortInfo(list, pivotindex + 1, end);
        }
    }
    
    public int partition(ArrayList<Info> list, int start, int end){
        int pivot = list.get(end).getFrequency();
        int pindex = start;
        
        for(int i = start; i < end; i++){
            if(list.get(i).getFrequency() >= pivot){
                Info temp = list.get(i);
                list.get(i).equals(list.get(pindex));
                list.get(pindex).equals(temp);
                pindex++;
            }
        }
        Info temp = list.get(end);
        list.get(end).equals(list.get(pindex));
        list.get(pindex).equals(temp);
        return pindex;
    }
}
