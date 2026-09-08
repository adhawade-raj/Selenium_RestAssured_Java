package leetcode_blind75_2026.solutions;

public class Part04_ProductOFArrayExceptSelf {


    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4};
        int[] result = productExceptSelf(nums);
        System.out.print(result + " ");
        System.out.println("Product of Array Except Self: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    public static int [] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Calculate the prefix product
        int prefixProduct = 1;
        for (int i = 0; i < n; i++) {
            result[i] = prefixProduct;
            prefixProduct *= nums[i];
        }

        // Calculate the suffix product and multiply with the prefix product
        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }

        return result;
    }
}
