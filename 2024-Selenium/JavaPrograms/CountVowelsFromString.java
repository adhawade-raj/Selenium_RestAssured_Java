package JavaPrograms;

import java.util.ArrayList;

public class CountVowelsFromString {

	public static void main(String[] args) {
	
		String st = "adhawade";
		String str = st.toLowerCase();
		int count=0;

		for(int i =0; i<str.length(); i++)
		{
			  if (str.charAt(i) == 'a' || str.charAt(i) == 'e'
		                || str.charAt(i) == 'i'
		                || str.charAt(i) == 'o'
		                || str.charAt(i) == 'u') {
		                // count increments if there is vowel in
		                // char[i]
		                count++;
		}
		}
		
	System.out.println("Total no of vowels in string are: " + count);

}
}
