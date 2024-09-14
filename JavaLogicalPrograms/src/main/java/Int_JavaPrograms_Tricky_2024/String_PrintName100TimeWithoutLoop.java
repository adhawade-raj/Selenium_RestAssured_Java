package Int_JavaPrograms_Tricky_2024;

public class String_PrintName100TimeWithoutLoop {

	public static void main(String[] args) {
		
		String str = "Raj";
		String a ="i";
		a=a.replaceAll("i", "iiiiiiiiii"); //10
		a=a.replaceAll("i", "iiiiiiiiii"); //10
		a=a.replaceAll("i", "iiiiiiiiii"); //10*10=100
		
		a=a.replaceAll("i",str + "\n");
		System.out.println(a);

	}

}
