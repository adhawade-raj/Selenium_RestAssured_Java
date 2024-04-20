package JavaPrograms_2022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class RemoveDuplicates_FromArrayList {

	public static void main(String[] args) {

		ArrayList<Integer> ar = new ArrayList<Integer>(Arrays.asList (1,2,2,4,56));
		LinkedHashSet<Integer> Number = new LinkedHashSet<Integer>(ar);
		ArrayList<Integer> nonDuplicateList =new ArrayList<Integer>(Number);
		System.out.println(nonDuplicateList);
				
		
	}

}
