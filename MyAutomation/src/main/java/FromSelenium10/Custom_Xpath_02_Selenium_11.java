package FromSelenium10;

public class Custom_Xpath_02_Selenium_11 {

	public static void main(String[] args) {

		
		
//		x Path Axes
//		Mostly used in webTables
//		following,preceding,parent,child, ancestor
//		ancestor=Highest Parent
//		----------------------------
		
//		--------> classic CrmPro site
		
//		---------> following
		//div[@class='input-group']//following :: input
		//div[@class='input-group']//following :: input[@name='username']
		//div[@class='input-group']//following :: input[@name='password']
		//div[@class='input-group']//following :: input[@class='btn btn-small']
		
		//div[@class='input-group']//following :: input[1]
		//div[@class='input-group']//following :: input[2]
		//div[@class='input-group']//following :: input[3]
		
//	------------------------------------------------------------------------------
//	----->preceding = i.e before
		
		//input[@name="username"]//preceding :: div
		//input[@class="form-control"]//preceding :: div
		
//	--------------------------------------------------------
//	----->parent
		//input[@class="form-control"]//parent :: div
	
//	----->child
		//div[@class="input-group"]//child :: input
		//div[@class='input-group']//child::input[@name='username']
		
//		--------------------------------------------------------
//		----->ancestor
//		ancestor=Highest Parent
		//div[@class='input-group']//ancestor::div[@class='container']
		
//		------------------------------------------------------------------------------
//		reverse x path - traversing backwardly in Dom
		
//		input[@id='username']//..//../..//..//..
	
	}

}
