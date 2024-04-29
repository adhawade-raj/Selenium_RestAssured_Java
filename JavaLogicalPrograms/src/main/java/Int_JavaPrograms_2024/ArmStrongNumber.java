package Int_JavaPrograms_2024;

public class ArmStrongNumber {

	
//	ArmStrongNumber = 153 = (1^3)+(5^3)+(3^3)
//	1+125+27=153  therefore  = it is not equal to 153 so it is a armstrong no
	public static boolean isArmStrongNum(int num) {
		
		
		if(num<0) {
			return false;
		}
		
		if(num>=0 && num<=9) {
			return true;
		}
		int power = powerNum(num);
		int copyNum=num;
		int sum=0;
		
		while(copyNum!=0) {
			int lastDigit = copyNum%10;
			int factor = 1;
			
			for(int i=0; i<power; i++) {
				factor = factor *lastDigit;
			}
			sum = sum+factor;
			copyNum = copyNum/10;
		}
		
		if(sum==num) {
			return true;
			}
		return false;
}
		
		 static int powerNum(int num) {
			int count =0;
			while(num!=0) {
				num = num/10;
				count++;
			}
			return count;
		}
	
	
	
	
	public static void main(String[] args) {
		
		System.out.println(isArmStrongNum(123));
	}

}
