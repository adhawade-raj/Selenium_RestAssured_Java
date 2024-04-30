package Int_JavaPrograms_2024;

public class String_GetCharPercentage {

	
	public static void getCharPercentage(String str) {
		
		int n = str.length();
		
		int upperCaseCount = 0;
		int lowerCaseCount = 0;
		int digits = 0;
		int specialChar = 0;
		
		for(int i=0; i<n; i++) {
			char ch = str.charAt(i);
			
			if(Character.isUpperCase(ch)) {
				upperCaseCount++;
			}
			
			else if(Character.isLowerCase(ch)) {
				lowerCaseCount++;
			}
			
			else if(Character.isDigit(ch)) {
				digits++;
			}
			
			else {
				specialChar++;
			}
			
			double upperCasePercentage = (upperCaseCount*100)/n;
			double lowerCasePercentage = (lowerCaseCount*100)/n;
			double digitsPercentage = (digits*100)/n;
			double specialCharPercentage = (specialChar*100)/n;
			
			System.out.println("Upper Case Percentage : "+upperCasePercentage);
			System.out.println("Lower Case Percentage : "+lowerCasePercentage);
			System.out.println("Digits Percentage : "+digitsPercentage);
			System.out.println("Special Character Percentage : "+specialCharPercentage);
		}
		
	}
	
	
	
	public static void main(String[] args) {

		getCharPercentage("RajAdhawde%#^&12474");
		
		

	}

}
