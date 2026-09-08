package leetcode_blind75_2026.solutions;

import java.util.*;

public class Part03_ContainsDuplicate {

    public static void main(String[] args) {
        int num[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        boolean result = containsDuplicate(num);
        System.out.println("Contains Duplicate: " + result);


        int num2[] = {1, 2, 3, 4, 6, 7,10,11,11, 8, 9, 10, 1};
        boolean result2 = containsDuplicate(num2);
        System.out.println("Contains Duplicate: " + result2);
        System.out.println("-----------------Contains Duplicate -----------------");
        findDuplicate(num);
    }

    public static boolean containsDuplicate(int num[]) {

        Set<Integer> set = new HashSet<>();
        for(int i =0; i<num.length; i++){
            if(set.contains(num[i])){
                return true;
            }
            else{
                set.add(num[i]);
            }
        }
        return false;
    }


    public static void findDuplicate(int num[]) {
       HashMap<Integer, Integer> map = new HashMap<>();
       for(int e: num){
           if(map.containsKey(e)){
               map.put(e, map.get(e)+1);
           }
           else{
               map.put(e, 1);
           }
       }

       /** Approach 1*/
        System.out.println("Approach 1" +map);

        /** Approach 2*/
       System.out.println("Approach 2" +Arrays.toString(map.entrySet().toArray()));



    }
}
