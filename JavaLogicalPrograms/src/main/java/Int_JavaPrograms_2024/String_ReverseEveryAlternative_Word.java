package Int_JavaPrograms_2024;


public class String_ReverseEveryAlternative_Word {

	public static void main(String[] args) {
		String a = "my name is raj";
		String words[] = a.split(" ");
		String rev = "";
		String rev2 ="";
		
//	for each required to print the words in its original Destination
//	if u use 1st for loop o/p = jar si eman ym.. it will be printed like this
//	if u use 1st for 1st word will e reversed and then 2nd and so on.. and o/p =ym eman si jar 
		for(String word:words) {
		for(int i=word.length()-1; i>=0; i--) {
			rev = rev+word.charAt(i);
		}
		rev=rev+" ";
	}
	 System.out.println(rev);
	 
//if u dont use 1st for loop o/p = raj is name my.  it will be printed like this
	 	
			for(int i=words.length-1; i>=0; i--) {
				rev2 = rev2+words[i];
				rev2=rev2+" ";
			}
		 System.out.println(rev2);
}
}
