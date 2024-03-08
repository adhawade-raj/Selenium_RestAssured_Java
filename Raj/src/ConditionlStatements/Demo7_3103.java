package ConditionlStatements;

public class Demo7_3103 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
int a=10, b=20, c=30;

if(a<b)
{
	if(b<c)
	{
		System.out.println("a is lesser than b");
	}
	else
	{
	System.out.println("b is less than c");
}
}
else
{
	if(a<c)
	{
		System.out.println("a is less than c");
		
	}
	else
	{
		System.out.println("c is leser than a");
	}
}
}
}

