package Int_JavaPrograms_Tricky_2024;


//		What will be the o/p? Will static block get execute
//		Ans = No it will not get executed.. only X value will get printed
 	class Test {
		
		static {
			System.out.println("This is test");
		}
		public static final int x =20;
	}
	

 public class StaticBlock_5_PseudoCode{
	 public static void main(String[] args) {
			
		 System.out.println(Test.x);
		 
	// To execute static block in above scenario we need to create obj
		System.out.println("STATIC BLOCK EXECUTION IS BELOW - AFTER OBJECT CREATION");
		System.out.println(new Test().x);
	}
}
