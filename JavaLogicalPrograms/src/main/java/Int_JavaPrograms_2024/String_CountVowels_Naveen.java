package Int_JavaPrograms_2024;

public class String_CountVowels_Naveen {

	public static void main(String[] args) {

		String str = "adhawade";
		int count=0;
		for(int i=0; i<str.length(); i++) {
			if(isVowel(str.charAt(i))) {
				count++;
				System.out.println("Vowel : " +str.charAt(i));
			}
		}
		System.out.println("Total Count of vowel is "+count);

	}
	
	public static boolean isVowel(char t) {
		 return t=='a' || t =='i' || t=='e' || t =='o' || t=='u' ||
				 t=='A' || t =='I' || t=='E' || t =='O' || t=='U' ;
	}

}
