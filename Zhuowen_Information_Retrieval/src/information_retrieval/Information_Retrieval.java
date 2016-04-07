package information_retrieval;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author mengqingwang
 */
public class Information_Retrieval {

    public static void main(String[] args) {
        Read read = new Read();
        read.read();
        for (Map.Entry<String, InformationMap> entry : read.map.entrySet()) {
            String key = entry.getKey();
            InformationMap value = entry.getValue();
            System.out.println("key:" + "\t" + key);
  
            Map<String, Information> infomap = value.getInfoMap();
            ValueComparator bvc =  new ValueComparator(infomap);
            TreeMap<String,Information> sortedMap = new TreeMap<String,Information>(bvc);
            
            sortedMap.putAll(infomap);
            
            for (Map.Entry<String, Information> infoentry : sortedMap.entrySet()) {
                String infokey = infoentry.getKey();
                Information infovalue = infoentry.getValue();
                System.out.println("file:" + "\t" + infokey + "\t" + "frequency:" + "\t" + infovalue.getFrequency() + "\t" + "hitrate:" + infovalue.getHitRate() + "\n");
            }
        }
    }
    
    public static class ValueComparator implements Comparator<String> {

        Map<String, Information> base;
        

        public ValueComparator(Map<String, Information> base) {
            this.base = base;
        }

        // Note: this comparator imposes orderings that are inconsistent with equals.    
        @Override
        public int compare(String a, String b) {
            if (base.get(a).getHitRate() > base.get(b).getHitRate()) {
                return -1;
            }
            else if(base.get(a).getHitRate() == base.get(b).getHitRate()){
                
                if (base.get(a).getFrequency() >= base.get(b).getFrequency()) {
                    return -1;
                } 
                else {
                    return 1;
                }
            }
            else{
                return 1;
            }
              // returning 0 would merge keys
        }

    }
}
