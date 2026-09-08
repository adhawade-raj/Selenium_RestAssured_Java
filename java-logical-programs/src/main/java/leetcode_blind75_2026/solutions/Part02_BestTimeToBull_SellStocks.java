package leetcode_blind75_2026.solutions;

public class Part02_BestTimeToBull_SellStocks {
    public static void main(String[] args) {
        int num[] = {7, 1, 5, 3, 6, 4};
        int profit = maxProfit(num);

        System.out.println("Maximum Profit: " + profit);

    }

    public static int maxProfit(int num[]) {
        int min = num[0];
        int profit = 0;

        for (int i = 0; i < num.length; i++) {
            if (num[i] < min) {
                min = num[i];
            }
            profit = Math.max(profit, num[i] - min);
        }
        return profit;
    }

}

