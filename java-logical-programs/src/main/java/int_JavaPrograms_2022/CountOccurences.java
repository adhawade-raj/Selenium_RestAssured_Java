package int_JavaPrograms_2022;

public class CountOccurences {

	public static void main(String[] args) {
		String input = "aaaabbccAAdd";
		  char search = 'a';             // Character to search is 'a'.
		  
		  int count=0;
		  for(int i=0; i<input.length(); i++)
		  {
		      if(input.charAt(i) == search)
		      count++;
		  }
		  
		  System.out.println("The Character '"+search+"' appears "+count+" times.");
		  System.out.println("========================================================");
	
//	Approach 2 
	String input2 = "aaaabbccAAdd";
	
	int input2_Length = input2.length();
	int input2_Removal = input2.replace("a", "").length();	
	int count2 = input2_Length - input2_Removal;
	System.out.println("The count of character " +count2);

	}
}


