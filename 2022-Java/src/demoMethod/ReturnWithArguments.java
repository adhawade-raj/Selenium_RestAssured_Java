package demoMethod;

public class ReturnWithArguments {
	
	int addition(int a,int b)
	{
		int c=a+b;
		System.out.println("addition is "+c);
		return c;
	}

	public static void main(String[] args) {
		
		ReturnWithArguments obj = new ReturnWithArguments();
		int x=obj.addition(10,20);
		int z=obj.addition(5, 5);
		
		
	}

}
