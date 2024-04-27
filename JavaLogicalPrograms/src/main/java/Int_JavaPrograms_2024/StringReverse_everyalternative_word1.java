package Int_JavaPrograms_2024;

import javax.print.attribute.standard.Destination;

public class StringReverse_everyalternative_word1 {

	public static void main(String[] args) {
		String a = "my name is raj";
		String words[] = a.split(" ");
		String rev = "";
		
//		for each required to print the words in its original Destination
//		if u dont use 1st for loop o/p = jar si eman ym..  it will be printed like this
//		if u use 1st for 1st word will e reversed and then 2nd and so on.. and o/p =ym eman si jar 
		for(String word:words) {
		for(int i=word.length()-1; i>=0; i--) {
			rev = rev+word.charAt(i);
		}
		rev=rev+" ";
	}
	 System.out.print(rev);
}
}
