package Int_JavaPrograms_2024;

import java.util.Scanner;

public class ScannerFunction1 {

//		i/p:10
//    o/p:1,2,3,4,5,6,7,8,9,10
	
//	we need to use try with resource(Try block)..
//	closes the Scanner and allows Java to reclaim the Scanner's memory. 
//	You cannot re-use a Scanner so you should get rid of it as soon as you exhaust its input.
	
	public static void main(String[] args) {
		
		try (Scanner obj = new Scanner(System.in)) {
			System.out.println("Enter the number");
			int number=obj.nextInt();
			
			for(int i=0;i<=number;i++)
			{
				System.out.println(i);
			}
		}
	}

}
