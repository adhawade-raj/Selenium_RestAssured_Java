package int_JavaPrograms_2026;

public class Array_FindSecondHighestNumber {

    public static void main(String[] args) {

        int ar[] = {2, 200, 4, 500, 30};

        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int e : ar) {
            if (e > max) {
                secondMax = max;
                max = e;
            } else if (e > secondMax && e != max) {
                secondMax = e;
            }
        }

        System.out.println("Max element is: " + max);
        System.out.println("Second highest element is: " + secondMax);
    }
}
