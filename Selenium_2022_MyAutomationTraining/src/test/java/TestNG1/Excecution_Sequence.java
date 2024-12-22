package TestNG1;

import org.testng.annotations.Test;

public class Excecution_Sequence {
//	Case1 ------->       test3
//	                     test2
//	                     test1
	
//	@Test(priority=3)
//	public void test1()
//	{
//		System.out.println("test1");
//	}
//	@Test(priority=2)
//	public void test2()
//	{
//		System.out.println("test2");
//	}
//	@Test(priority=1)
//	public void test3()
//	{
//		System.out.println("test3");
//	}
	
//	Case2 ------->
//	@Test(priority=3)
//	public void test1()
//	{
//		System.out.println("test1");
//	}
//	@Test(priority=2)
//	public void test2()
//	{
//		System.out.println("test2");
//	}
//	@Test(priority=1)
//	public void test3()
//	{
//		System.out.println("test3");
//	}
//	@Test
//	public void test4()
//	{
//		System.out.println("test4");
//	}
//	@Test
//	public void test5()
//	{
//		System.out.println("test5");
//	}
	
//-----------------------------------------------------------------------------------------------------------
//	Case2 ------->Non-Priority cases first executed then Priority Based
//	test4
//	test5
//	test3
//	test2
//	test1
	
//	Case3 ------->
//	@Test(priority=3)
//	public void test1()
//	{
//		System.out.println("test1");
//	}
//	@Test(priority=2)
//	public void test2()
//	{
//		System.out.println("test2");
//	}
//	@Test(priority=1)
//	public void test3()
//	{
//		System.out.println("test3");
//	}
//	@Test(priority=1)
//	public void test4()
//	{
//		System.out.println("test4");
//	}
//	@Test(priority=2)
//	public void test5()
//	{
//		System.out.println("test5");
//	}
//	Case3 -------> Even when having Same Priority No, Again testng will excute based on alphabetical order
//	test3
//	test4
//	test2
//	test5
//	test1
//-----------------------------------------------------------------------------------------------------------
//	Case4 ------->
//	@Test(priority=3)
//	public void test1()
//	{
//		System.out.println("test1");
//	}
//	@Test(priority=2)
//	public void test2()
//	{
//		System.out.println("test2");
//	}
//	@Test(priority=-1)
//	public void test3()
//	{
//		System.out.println("test3");
//	}
//	@Test(priority=1)
//	public void test4()
//	{
//		System.out.println("test4");
//	}
//	@Test(priority=2)
//	public void test5()
//	{
//		System.out.println("test5");
//	}
//	Case5 -------> Negative No Comes First, Therefore it will be executed first
//	test3
//	test4
//	test2
//	test5
//	test1
	
//	-----------------------------------------------------------------------------------------------------------
//	Case6 ------->
	@Test(priority=3)
	public void test1()
	{
		System.out.println("test1");
	}
	@Test(priority=0)
	public void test2()
	{
		System.out.println("test2");
	}
	@Test(priority=-1)
	public void test3()
	{
		System.out.println("test3");
	}
	@Test(priority=1)
	public void test4()
	{
		System.out.println("test4");
	}
	@Test(priority=2)
	public void test5()
	{
		System.out.println("test5");
	}
//	Case6 ------->Before zero.. Negative No Comes First,Therefore it will be executed first
//	test3
//	test2
//	test4
//	test5
//	test1
}
