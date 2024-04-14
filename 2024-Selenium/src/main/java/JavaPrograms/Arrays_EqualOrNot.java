package JavaPrograms;

import java.util.Arrays;

public class Arrays_EqualOrNot {

	public static void main(String[] args) {
		
		int a[] = {1,2,3,4,5};
		int b[] = {1,2,3,4,5};
		
		boolean status = Arrays.equals(a, b);
		
		if(status == true)
		{
			System.out.println("array are equal");
		}
		else {
			System.out.println("arrays are not equal");
		}

	}

}
