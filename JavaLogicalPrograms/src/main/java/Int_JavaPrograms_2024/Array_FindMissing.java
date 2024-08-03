package Int_JavaPrograms_2024;

public class Array_FindMissing {

	public static void main(String[] args) {
	
		int num[] = {1,2,3,5};
		int missingNum = findMissingNumber(num,5);
		System.out.println(missingNum);
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
