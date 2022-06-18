package JavaPractise_InterviewPrograms;

public class ForLoop7_DownSidePyramid1 {

	public static void main(String[] args) {

		for(int i=0; i<5; i++)
		{
			for(int j=0; j<i; j++)
			{
				System.out.print(" ");
			}
			for(int k=0; k<(5-i); k++)
			{
				System.out.print("*" +" ");
			}
			System.out.println();
		}
	}

}
