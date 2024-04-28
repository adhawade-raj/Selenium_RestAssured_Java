package Testpacakge1;

import java.util.Scanner;

public class DemoSwitchCase {

	public static void main(String[] args) {
	
		
		Scanner sc=new Scanner(System.in);
	
			char ch='2';
		
			
			switch(ch)
			{
			case '1' : System.out.println("moday");
			break;
			
			case '2' : System.out.println("tue");
			break;
			
			case '3' : System.out.println("wed");
			break;
			
			case '4' : System.out.println("thus");
			break;
			
			case '5' : System.out.println("fri");
			break;
			
			case '6' : System.out.println("sat");
			break;
			
			case '7' : System.out.println("sun");
			break;
			
			default : System.out.println("invalid input");
			
			}
			
		}
	}

	


