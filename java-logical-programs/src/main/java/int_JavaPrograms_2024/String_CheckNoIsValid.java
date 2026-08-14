package int_JavaPrograms_2024;

public class String_CheckNoIsValid {

	public static boolean isNumberValid(String number) {
		try {
		Long.parseLong(number);
		System.out.println("This is a valid number :"+number);
		}
		catch(NumberFormatException e) {
			System.out.println("Not a Valid number : "+number);
			return false;
		}
		return true;
		}
	
	public static boolean isValidPhoneNumber(String number) {
// && will check 1st condn.. if it is false then it will not check the rest of the condions
// here && is used beacuse if number is not valid it will check isNumberValid function
//It will directly make it as false andd will go for else part 
//Therefore it is being used as 2 condition
		if(number.length()==10 && isNumberValid(number)) {
			System.out.println("This is a Valid Phone number :"+number);
		}
		else {
			System.out.println("This is not a Valid Phone number :"+number);
		}
		return true;
	}
	
	public static void main(String[] args) {
		isValidPhoneNumber("7276179886");
		isValidPhoneNumber("727617988");

	}

}
