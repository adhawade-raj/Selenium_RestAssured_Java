package int_JavaPrograms_2022;

public class No_Of_WordsInString {

	public static void main(String[] args) {

		String a = "welcome to java";
		int count = 1;
		
		for(int i=0; i<a.length(); i++)
		{
			if(a.charAt(i)== ' ' && a.charAt(i+1)!=' ')
			{
				count++;
			}
		}
		System.out.println("No of words are "+count);
	}

}
