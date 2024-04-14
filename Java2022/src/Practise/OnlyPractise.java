package Practise;
import static java.lang.System.out;

 class OnlyPractise {
	 OnlyPractise getRef1()
	{
		 OnlyPractise a=new OnlyPractise();
		 return a;
	}
	  OnlyPractise getRef2()
		{
			 OnlyPractise a=new OnlyPractise();
			 return this;
		}
public static void main(String[] args)
{
	OnlyPractise a=new OnlyPractise();
	out.println(a.getRef1());
	out.println(a.getRef1());
	out.println(a.getRef1());
	System.out.println();
	
	out.println(a.getRef2());
	out.println(a.getRef2());
	out.println(a.getRef2());
	}
 }	

	
	
 
 
