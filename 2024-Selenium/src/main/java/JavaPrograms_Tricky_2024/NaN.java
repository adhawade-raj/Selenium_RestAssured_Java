package JavaPrograms_Tricky_2024;

public class NaN {

	public static void main(String[] args) {
		
		System.out.println(0.0/0.0);
		System.out.println(Math.sqrt(-1));
		System.out.println(Float.NaN);
//		output = NaN
		
//		Comparison of Nan will print False
		System.out.println(Float.NaN == Float.NaN);
	}

}
