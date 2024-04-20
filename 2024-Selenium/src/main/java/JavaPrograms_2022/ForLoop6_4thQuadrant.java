package JavaPrograms_2022;

public class ForLoop6_4thQuadrant {

	public static void main(String[] args) {

		for(int i=0; i<5; i++)
		{
			for(int j=0; j<=(4-i); j++)
			{
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
