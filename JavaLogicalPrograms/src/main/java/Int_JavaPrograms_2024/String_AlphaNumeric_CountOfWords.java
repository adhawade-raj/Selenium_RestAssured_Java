package Int_JavaPrograms_2024;

public class String_AlphaNumeric_CountOfWords {

	public static void main(String[] args)
	{
		String str="Test1Test2Test3Test4Test5";
		String[] str1=str.split("[0-9]");
		//Converted to String array so that we can retrieve text
		//After removing numbers from string
		int count=0;
		for(int i=0;i<str1.length;i++)
		{
			if(str1[i].equals("Test"))
			{
				count++;
			}
		}
		System.out.println("Count of word is:"+count);
		
	}
}

		

	
