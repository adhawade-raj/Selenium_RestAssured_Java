package leetcode_blind75_2026.solutions;

public class Part06_MaximumProductSubArray {

    public static void main(String[] args) {
        int num [] = {2,3,-2,4};

        int result = maxProduct(num);
        System.out.println(result);
    }

    public static int maxProduct(int num[]){
            if(num.length==0){
                return 0;
            }
            int min = num[0];
            int max = num[0];
            int result = max;

            for(int i =1; i<num.length; i++){
                int cur = num[i];
                int temp = Math.max(cur, Math.max(max*cur, min*cur));
                min = Math.min(cur, Math.min(max*cur, min*cur));
                max = temp;
                result = Math.max(result, max);
            }
            return result;
    }
}
