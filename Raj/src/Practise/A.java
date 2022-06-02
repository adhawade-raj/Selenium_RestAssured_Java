package Practise;

abstract class A
{
	void m1()
	{
		System.out.println("m1-A");
	}
	abstract void m2();
	abstract void m3();
}

   abstract class b extends A
{
	void m2()
	{
		System.out.println("m2-B");
	}
	void m3()
	{
		System.out.println("m3-B");
	}
	void m4()
	{
		System.out.println("m4-B");
	}
}
class test
{
	public static void main(String[] args)
	{
		A a=new b();
		a.m1();
		a.m2();
		a.m3();
		
	}
}