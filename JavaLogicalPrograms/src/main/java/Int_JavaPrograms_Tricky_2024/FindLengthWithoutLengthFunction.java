package Int_JavaPrograms_Tricky_2024;


public class FindLengthWithoutLengthFunction {

	public static void main(String[] args) {
	
		String str = "adhawade";
		System.out.println(str.lastIndexOf(""));
		
//		here length is not function - it is an variable to find the length of array
		System.out.println(str.toCharArray().length);

		
		int count=0;
		for(char c: str.toCharArray()) {
			count++;
		}
		System.out.println(count);
	}

}
