package InterviewPrograms;

public class Spaces_RemovalFromString {

	public static void main(String[] args) {

		String str = "Raj Adhawade";
		
		System.out.println("Given String is"+str);
		
		String NewStr = str.replaceAll(" ", "");
		System.out.println(" "+NewStr);
		
		
	}

}
