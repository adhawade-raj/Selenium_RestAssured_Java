package JavaPractise_InterviewPrograms;

public class StringPalndrome1 {

	public static void main(String[] args) {

	String a= "nayan1";
	int b= a.length();
	
	String rev="";
	
	for(int i=(b-1); i>=0; i--)
	{
		rev= rev+a.charAt(i);
	}
	if(a.equals(rev))
	{
		System.out.println("Palandrome");
	}
	else
	{
		System.out.println("Not Palndrome");
	}
		
		
	}

}
