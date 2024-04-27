package Int_JavaPrograms_Tricky_2024;

public class StaticBlockExecutionWithFinalKeyword {

	public static void main(String[] args) {
		
		System.out.println(Test.x);
		System.out.println("=========================");
		System.out.println(Test2.x);

	}
	
	class Test{
		public static final int x = 100;
		static {
			System.out.println("This is Static block.. It will not executed with Final Keyword");
		}
	}
	
	/**
	 * Final Keyword is not used here
	 */
	class Test2{
		public static int x = 100;
		static {
			System.out.println("This is Static block.. It will be executed without Final Keyword");
		}
	}

}
