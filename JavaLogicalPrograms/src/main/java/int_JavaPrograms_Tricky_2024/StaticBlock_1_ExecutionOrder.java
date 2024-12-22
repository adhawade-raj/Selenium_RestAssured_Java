package int_JavaPrograms_Tricky_2024;

public class StaticBlock_1_ExecutionOrder {

//	What will be excecutedFirst? Main Method or Static block
//	Static Block will be executed first even before Main Method
	
	static {
		System.out.println("Static Block 1");
	}
	
	static {
		System.out.println("Static Block 2");
	}
	
	static {
		System.out.println("Static Block 3");
	}
	
	
	public static void main(String[] args) {
		System.out.println("Main Method");
	}
	
	static {
		System.out.println("Static Block 4");
	}

}
