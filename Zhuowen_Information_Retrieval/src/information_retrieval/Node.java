/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package information_retrieval;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 *
 * @author mengqingwang
 */
public class Node {
    
    char content; // the character in the node  
    boolean isEnd; // whether the end of the words  
    int wordcount;  // the number of words sharing this character  
    LinkedList<Node> childList; // the child list
    Hashtable<String, Information> informationTable;
    
    public Node(char c){  
        childList = new LinkedList<Node>();  
        isEnd = false;  
        content = c;  
        wordcount = 0;
    }  
    
    public Node subNode(char c){  
        if(childList != null){  
            for(Node eachChild : childList){  
                if(eachChild.content == c){  
                     return eachChild;  
                }  
            }  
        }  
        return null;  
   }
    
    public Information updateInfoTable(String source, String address) {
        Information information = find(source);
        if(information != null){
            return increaseInfoFrequency(information);
        }
        else {
            return informationTable.put(source, new Information(1, 0));
        }
    }
    
    public Information increaseInfoFrequency(Information information){
        information.addFrequency();
        return information;
    }
    
    public Information find(String source){
        Information information = informationTable.get(source);
        return information;
    }
    
}
