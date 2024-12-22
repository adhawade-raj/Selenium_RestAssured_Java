package int_JavaPrograms_Tricky_2024;

import java.util.StringJoiner;

public class UseOfStringJoiner {

	public static void main(String[] args) {
	
		
		StringJoiner sj = new StringJoiner(",");
		sj.add("Raj");
		sj.add("Ajay");
		sj.add("Jay");
		System.out.println(sj);
//		o/p = [Raj,Ajay,Jay]

	}

}
