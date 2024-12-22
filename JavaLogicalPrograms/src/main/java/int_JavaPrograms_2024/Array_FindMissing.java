package int_JavaPrograms_2024;

public class Array_FindMissing {

	public static void main(String[] args) {
	
		int num[] = {1,2,3,5};
		int missingNum = findMissingNumber(num,5);
		System.out.println("________Approach 1________");
		System.out.println(missingNum);
		System.out.println("________Approach 2________");
		
		
		int a[] = {1,2,4,5};
		int sum1 = 0;
		int sum2=0;
		int sum3=0;
		for(int i=0; i<a.length; i++)
		{
			sum1 = sum1+a[i];
		}
		
//		for(int e: a)
//		{
//			sum3 = sum3+e;
//		}
		System.out.println("Sum of elements is "+sum1);
		
		for(int j=1; j<=5; j++)
		{
			sum2 = sum2+j;
		}
		System.out.println("Sum of elements is "+sum2);
		System.out.println("missing elements is "+(sum2-sum1));
		

	}
	
	/**
	 * Provide total count in argument e.g if u want find missing no from 1 to 100
	 * then ur array should have only one missing, and u have to provide int argument as 100
	 * with this functionn u can only find one missing no
	 * total*((total+1)/2)) = formula
	 * @param num
	 * @param totalCount
	 * @return
	 */
	public static int findMissingNumber(int num[], int totalCount) {
		
		int expSum = totalCount*((totalCount+1)/2);
		int actualSum = 0;
		for(int e: num) {
			actualSum = actualSum+e;
		}
		return expSum-actualSum;
	}
	
	
	
}
