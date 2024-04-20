package JavaPrograms_2024;

public class ExceptionArrayIndexOutOfBound {

	public static void main(String[] args) {
		
//	 1. For Data Retrieval within Range
	int a[] = {1,2,3,4,5};
	for(int i=0; i<a.length; i++) {
		System.out.println(a[i]);
	}
	
//	2.For Data retrieval out of Range
//	Array size 5 and retrieving 5th index element as index starts from zero and on 4th index value is present
//	So We got exception for 5th element
	for(int i=0; i<6; i++) {
		System.out.println(a[i]);
//Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
	}

	}

}
