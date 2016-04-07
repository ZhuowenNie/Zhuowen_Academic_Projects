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

/**
 *
 * @author mengqingwang
 */
public class InsertWords {
    
    private static String[] block = {"a", "and", "the", "or", "but", "he", "she", "me", "with", "use", "of", "in", "out", "from", "for",
                                    "this", "that", "there", "their", "those", "his", "her", "mine", "that", "our", "us", "may", "have", 
                                    "however", "may", "while", "without", "which", "whether", "mean", "had", "has", "other", "word", "words", 
                                    };
    protected int M;
    private InfoList[] values;
    private String[] keys;
    protected int prime;
    private int size;
    //private Trie trie;
    
    public InsertWords(){
    //    this.trie = new Trie();
        this.M = 30;
        this.prime = 37;
        this.values = new InfoList[this.M];
        this.keys = new String[this.M]; 
        this.size = 0;
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
                        boolean filter = false;
                        if (key.length() > 0) {
                            for (int j = 0; j < block.length; j++) {
                                if (key.equals(block[j])) {
                                    filter = true;
                                    break;
                                }
                            }
                            if (!filter) {
                                //trie.insert(key, f.getName());
                            }

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
            System.out.println(list.get(j).getSource() + "\t" + list.get(j).getRank());
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
