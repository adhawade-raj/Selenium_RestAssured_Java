package int_JavaPrograms_2024;

public class String_AlphaNumeric_AdditionOfNumbers {

	public static void main(String[] args) {
		
		String str="Test1Test2Test3Test4Test5";
		char[] charArray= str.toCharArray();
		int sum =0;
		
		for(int i=0;i<charArray.length;i++) {
			if(Character.isDigit(charArray[i]))
			sum = sum+Integer.parseInt(String.valueOf(charArray[i]));
			
//			here we have converted string to int
//			in bracket we have converted characters into string
		}
		System.out.println(sum);
	}

}
