package Int_JavaPrograms_2024;

public class String_CountFrequencyOfSingleCharacters {

	public static void main(String[] args) {
		
		String a ="109880009000073652";
		char b ='0';
		int count = 0;
		
		for(int i=0; i<a.length(); i++) {
			if(b==a.charAt(i)) {
				count++;
			}
		}
		System.out.println(count);

	}

}
