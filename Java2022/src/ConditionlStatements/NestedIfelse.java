package ConditionlStatements;

public class NestedIfelse {
public static void main(String[] args) {
	
int a=20;
int b=30;
int c=50;

if(a>b)
{
	if(a>c)
	{
		System.out.println("a is greater");
	}
	else
	{
		System.out.println("c is greater");
	}
}
else
{
	if(b>a)
	{
	System.out.println("b is greater");	
	}
	else
	{
		System.out.println("c is greater");
	}
}

}
}

