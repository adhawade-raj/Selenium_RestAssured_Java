package leetcode_blind75_2026.solutions;

public class Part05_MaximumSubArray {

public static void main(String[] args) {
        int num [] = {-2,1,-3,4,-1,2,1,-5,4};

        int result = maxSubArray(num);
        System.out.println(result);
    }

    public static int maxSubArray(int num[]){
        int curentSum = 0;
        int maxSum = num[0];

        for(int i=0; i<num.length; i++){
            if(curentSum < 0){
                curentSum = 0;
            }
            curentSum = curentSum + num[i];
            maxSum = Math.max(curentSum,  maxSum);
        }

        return maxSum;
    }
}
