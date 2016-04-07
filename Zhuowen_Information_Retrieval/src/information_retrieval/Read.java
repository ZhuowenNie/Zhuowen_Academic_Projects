/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package information_retrieval;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mengqingwang
 */
public class Read {
    
   
    private static String[] block = {"a", "b", "c", "d","e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
        "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "about", "above", "above", "across", "after",
        "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also","although","always",
        "am","among", "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone","anything",
        "anyway", "anywhere", "are", "around", "as",  "at", "back","be","became","because","become","becomes",
        "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between",
        "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con", "could",
        "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg",
        "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every",
        "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire",
        "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full",
        "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here",
        "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how",
        "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its",
        "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may",
        "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much",
        "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no",
        "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on",
        "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves",
        "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see",
        "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since",
        "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes",
        "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", 
        "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein",
        "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three",
        "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards",
        "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was",
        "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter",
        "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither",
        "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would",
        "yet", "you", "your", "yours", "yourself", "yourselves", "the"};
    
    protected int M;
    private InfoList[] values;
    private String[] keys;
    protected int prime;
    private int size;
    Map<String, InformationMap> map;
    
    public Read(){
        this.M = 30;
        this.prime = 37;
        this.values = new InfoList[this.M];
        this.keys = new String[this.M]; 
        this.size = 0;
        map = new HashMap<String, InformationMap>();
    }
    
    public void read() {
        File file = new File("/Users/mumu/files/");
        File[] list = file.listFiles();
        Reader reader = null;
        
        try {

            int character;
            for (int i = 0; i < list.length; i++) {
                File f = new File(list[i].getAbsolutePath());
                reader = new InputStreamReader(new FileInputStream(f));
                StringBuffer s = new StringBuffer("");
                if (f.getName().contains(".DS_Store")) {
                    continue;
                }
                while ((character = reader.read()) != -1) {
                    char temp = (char) character;
                    if (('A' <= temp && temp <= 'Z') || 'a' <= temp && temp <= 'z') {
                        s = s.append(temp);
                    } else {
                        String key = (s.toString()).toLowerCase(); // remove .toLowerCase for Case Sensitive result.
//                        boolean filter = false;
                        
                        if (key.length() > 0) {
                            boolean contains = Arrays.asList(block).contains(key);
                            if (!contains) {
                                Stemmer stemmer = new Stemmer();
                                stemmer.add(key.toCharArray(), key.length());
                                stemmer.stem();
                                stemmer.toString();
                                
//                                InfoList infoList = find(key);
//                                if (infoList == null) {
//                                    InfoList newInfoList = new InfoList();
//                                    newInfoList.addInfo(f.getName(), 1, 0);
//                                    add(key, newInfoList);
//                                } else {
//                                    infoList.updateInfoList(f.getName());
//                                }
                                InformationMap infoMap = map.get(key);
                                if(infoMap == null){
                                    InformationMap newInfoMap = new InformationMap();
                                    newInfoMap.addInformation(f.getAbsolutePath(), 1, 0);
                                    map.put(key, newInfoMap);
                                }
                                else{
                                    infoMap.updateInfoMap(f.getAbsolutePath());
                                }
                            }

//                                    InfoList infoList = find(key);
//                                    if (infoList == null) {
//                                        InfoList newInfoList = new InfoList();
//                                        newInfoList.addInfo(1, f.getName());
//					add(key, newInfoList);
//                                    } else {
//                                        infoList.updateInfoList(f.getName());
//                                    }
                        }
                        s = new StringBuffer("");
                    }
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    
    public int hash(String key){
        return (key.hashCode() & 0x7fffffff) % M; 
    }
    
    public void add(String key, InfoList value){
        if(key == null || key.trim() == ""){
            return;
        }
        if(size >= M/2){
            expandMap();
        }
            int i;
            for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
                if (keys[i].equals(key)) {
                    break;
                }
            }
            if (keys[i] == null) {
                size++;
                keys[i] = key;
                values[i] = value;
            }
        
        System.out.println(key + "\t\t");
        ArrayList<Info> list = value.getInfoList();
        for(int j=0; j<list.size(); j++){
            System.out.println(list.get(j).getSource() + "\t" + list.get(j).getFrequency() + "\t" + list.get(j).getRank());
        }
        System.out.println("index is: " + i);
        System.out.print("\n");
    }
    
    public InfoList find(String key){
        for (int i = hash(key); keys[i] != null; i = (i+1) % M)
            if (key.equals(keys[i])){
                return values[i];
            }
        return null;
    }
    
    private void expandMap(){
        int previousSize = this.M;
        this.M = previousSize * 2;
        InfoList[] previousValues = this.values;
        String[] previousKeys = this.keys;
        this.values = new InfoList[previousSize *2];
        this.keys = new String[previousSize *2];
        this.size = 0;
        // System.out.println("--Expand--------------");
        for(int i = 0; i < previousSize; i++){
            if(previousKeys[i] != null){
                add(previousKeys[i], previousValues[i]);
            }
        }
        // System.out.println("--Expanded-------------");
    }
    
}
