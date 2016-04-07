/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package information_retrieval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author mengqingwang
 */
public class IntersectionAndUnion {
    
    public static void main(String args[]) throws Exception {

        List<String> list1 = new ArrayList<String>(Arrays.asList("A", "B", "C"));
        List<String> list2 = new ArrayList<String>(Arrays.asList("B", "C", "D", "E", "F"));

        System.out.println(new IntersectionAndUnion().intersection(list1, list2));
        System.out.println(new IntersectionAndUnion().union(list1, list2));
    }

    public <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

    public <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
    
    public Set<String> mapIntersection(Map<String, Information> mapA, Map<String, Information> mapB){
        Set<String> intersectSet = new HashSet<String>(mapA.keySet());
        intersectSet.retainAll(mapB.keySet());
        return intersectSet;
    }
    
    public Set<String> mapUnion(Map<String, Information> mapA, Map<String, Information> mapB){
        Set<String> unionSet = new HashSet<String>();
        unionSet.addAll(mapA.keySet());
        unionSet.addAll(mapB.keySet());
        return unionSet;
    }
}
