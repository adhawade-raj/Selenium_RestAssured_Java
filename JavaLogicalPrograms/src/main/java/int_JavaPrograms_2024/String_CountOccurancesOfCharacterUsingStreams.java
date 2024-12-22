package int_JavaPrograms_2024;

public class String_CountOccurancesOfCharacterUsingStreams {

	public static void main(String[] args) {
	
		long count = getcharCount("adhawade", 'a');
		System.out.println(count);

	}
	
	public static long getcharCount(String str, char c) {
	
		return str.chars().filter(e -> (char)e==c).count();
		
	}

}
