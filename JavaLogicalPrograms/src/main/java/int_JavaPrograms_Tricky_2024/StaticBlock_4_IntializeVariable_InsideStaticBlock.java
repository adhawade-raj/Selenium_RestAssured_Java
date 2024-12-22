package int_JavaPrograms_Tricky_2024;

public class StaticBlock_4_IntializeVariable_InsideStaticBlock {

//	Can we initialize variable in static block?
//	1. Non Static variable can not be accessed directly in static block
//	2. We need to create object inside Static block and from ref variable we can initialize variable
//	3. Static variables can directly be initialized without obj creation
	
	int age;
	static String name;
	
	public static void main(String[] args) {
	
	}
	
	static{
StaticBlock_4_IntializeVariable_InsideStaticBlock obj = new StaticBlock_4_IntializeVariable_InsideStaticBlock();
		obj.age = 25;
		name = "Raj";
		
		System.out.println(obj.age+ " "+name);
	}

}
