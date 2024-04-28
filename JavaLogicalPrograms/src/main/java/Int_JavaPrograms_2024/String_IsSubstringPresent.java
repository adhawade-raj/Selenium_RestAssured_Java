package Int_JavaPrograms_2024;

public class String_IsSubstringPresent {

	
	public static boolean isSubStringPresent(String main, String subString) {
		return main.contains(subString);
	}
	
//	"(.*)" = this indicates String starts with anything and ends with anything
	public static boolean isSubStringPresent1(String main, String subString) {
		return main.matches("(.*)"+subString+"(.*)");
	}
	
	public static void main(String[] args) {
		
		System.out.println(isSubStringPresent("NaveenAutomationLabs", "Labs"));
	}

}
