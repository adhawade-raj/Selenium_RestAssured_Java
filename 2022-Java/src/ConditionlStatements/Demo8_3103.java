package ConditionlStatements;

public class Demo8_3103 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=20, b=30, c=40, d=50;
		
		if(a>b)
		{
		if(a>c)
		{
				if(b>c)	
				{
				if(a>d)
				{
					if(c>d)
					{
				}
					System.out.println("a");
					
				}
				System.out.println("d");
			}
			System.out.println("c");
		}
		System.out.println("d");
		}
		
		else
		{
			if(b>c)
			{
				if(b>d)
				{
					if(c>d)
					{
					if(c>d)
					
					System.out.println("b");
				}
				System.out.println("d");
			}
			System.out.println("c");
		}
		System.out.println("d");
		}
	}
}

