package JavaPrograms;

public class Swapping_Of_Numbers {

	public static void main(String[] args) {

		int a=10,b=20;
		
		System.out.println("Values before swapping is "+a+" "+b);
		
		int t=a;
		a=b;
		b=t;
		
//		Logic 2 with addition and subtraction
//		a = a+b;  //10+20=30
//		b = a-b;  //30-20=10
//		a = a-b;  //30-10=20
		
		System.out.println("Values after swapping is "+a+" "+b);
	}

}
