package InterviewPrograms;

public class Largest_Of_ThreeNumbers {

	public static void main(String[] args) {

		int a=10,b=40,c=30;
		
		if(a>b && a>c)
		{
			System.out.println("A is largest");
		}
		else if(b>c && b>a)
		{
			System.out.println("B is largest");
		}
		else {
			System.out.println("C is largest");
		}
	}

}
