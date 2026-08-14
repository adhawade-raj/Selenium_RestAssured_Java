package int_JavaPrograms_Tricky_2024;


public class FindLengthWithoutLengthFunction {

	public static void main(String[] args) {
	
		String str = "adhawade";
		System.out.println("___Approach_1____");
		System.out.println(str.lastIndexOf(""));
		
		System.out.println("___Approach_2____");
//		here length is not function - it is an variable to find the length of array
		System.out.println(str.toCharArray().length);

		System.out.println("___Approach_3____");
		int count=0;
		for(char c: str.toCharArray()) {
			System.out.print(c);
			count++;
		}
		System.out.println();
		System.out.println(count);
	}

}
