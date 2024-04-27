package Int_JavaPrograms_2024;

public class AlphaNumericString_CountOfWords {

	public static void main(String[] args)
	{
		String str="Test1Test2Test3Test4Test5";
		String[] str1=str.split("[0-9]");
		int count=0;
		
		for(int i=0;i<str1.length;i++)
		{
			if(str1[i].equals("Test"))
			{
				count++;
			}
			
		}
		
		System.out.print("Count of word is:"+count);
		
	}
}

		

	
