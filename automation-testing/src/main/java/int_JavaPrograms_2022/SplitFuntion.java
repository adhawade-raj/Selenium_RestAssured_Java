package int_JavaPrograms_2022;

public class SplitFuntion {

	public static void main(String[] args) {
		String z= "a, b, c";
		String n[] = z.split(",");
	
		for(int i=0; i<n.length; i++)
		{
			System.out.print(n[i]+" ");
		}
	}

}
