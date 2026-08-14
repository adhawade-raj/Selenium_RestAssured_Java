package int_JavaPrograms_2024;

public class CheckBinaryNumber {

	public static void main(String[] args) {
	
		isBinaryNumber(1011);
		isBinaryNumber(2011);
		isBinNum(10111);
		isBinNum(10151);
	
	}

	
//	Approach 1
	public static void isBinaryNumber(int number) {
		boolean isBinary = true;
		int copyNumber = number;
		while(copyNumber!=0) {
			int rem = copyNumber%10;
			
			if(rem>1) {
				isBinary=false;
				break;
			}
			else {
				copyNumber = copyNumber/10;
			}
		}
		
		if(isBinary) {
			System.out.println(number+ " : is a Binary Number");
		}
		else {
			System.out.println(number+ " : is not a Binary Number");
		}
	}
	
	
//	Approach 2 : using String conversion of int num
	
	public static void isBinNum(int num) {
		if(String.valueOf(num).matches("[0-1]+")) {
			System.out.println(num+ " : is a Binary Number");
		}
		else {
			System.out.println(num+ " : is not a Binary Number");	
		}
	}
	
}
