package leetcode_blind75_2026.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Part01_TwoSums {



     public static void main(String[] args) {
         int num [] = {2, 7, 11, 15};
         int target = 9;

         int[] result = twoSums(num,target);
             System.out.println(Arrays.toString(result));
     }

    public static int[] twoSums(int num[], int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<num.length; i++){
            int diff = target - num[i];

            if(map.containsKey(diff)) {
                return new int[]{map.get(diff), i};
            }
                map.put(num[i], i);
            }
        return null;
    }
}
