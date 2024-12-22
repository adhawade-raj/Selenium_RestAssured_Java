package int_JavaPrograms_2022;

public class Spaces_RemovalFromString {

	public static void main(String[] args) {

		String str = "Raj Adhawade";
		System.out.println("Given String is"+str);
		String NewStr = str.replaceAll(" ", "");
		System.out.println(" "+NewStr);
		System.out.println("==========================");
		
		String str2 = "Raj Dnyaneshwar Adhawade";
		System.out.println("Given String is "+str2);
		String NewStr2 = str2.replaceAll("\\s", "");
		System.out.println(" "+NewStr2);
		
		
	}

}
