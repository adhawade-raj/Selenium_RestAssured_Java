package Int_JavaPrograms_Tricky_2024;

public class StaticBlock_3_PrivateConstrutor {

	int age;
	
	private StaticBlock_3_PrivateConstrutor() {
		age =30;
	}
	
//	How can we make the object if we declare constructor as private
	
	public static void main(String[] args) {
//		Approach 1- using static method - calling static method in main method using class name
//		int i = StaticBlock_3_PrivateConstrutor.create();
//		System.out.println(i);

	}
	
//	Approach 1 using Static method we can create object even if constructor is private
//	public static int create() {
//		StaticBlock_3_PrivateConstrutor obj = new StaticBlock_3_PrivateConstrutor();
//		obj.age=40;
//		return obj.age;
//	}
	
//	Approach 2 - Using Static block 
	static {
		StaticBlock_3_PrivateConstrutor obj = new StaticBlock_3_PrivateConstrutor();
		System.out.println(obj.age=40);
		
	}

}
