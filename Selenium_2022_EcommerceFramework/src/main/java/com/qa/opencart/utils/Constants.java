package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String LOGINPAGE_PAGE_TITLE="";
	public static final String LOGIN_PAGE_URL_FRACTION="http://opencart.antropy.co.uk/index.php?route=account/login";
	public static final int DEFAULT_TIME_OUT = 10;
	public static final String ACCOUNT_PAGE_TITLE = "Antropy Demo Store";
	public static final String ACCOUNT_PAGE_HEADER = "Antropy Demo Store";
	
	public static List<String> getExpectedAccSecList()
	{
		List<String> exSpecList = new ArrayList<String>();
		exSpecList.add("My Account");
		exSpecList.add("My Orders");
		exSpecList.add("NewsLetter");
		
		return exSpecList;
	}
	public static final int IMAC_IMAGE_COUNT = 3;
	public static final int MACKBOOKPRO_IMAGE_COUNT=4;
	public static final int MACKBOOKAIR_IMAGE_COUNT=4;
	public static final CharSequence REGISTER_SUCCESS_MESSG = null;
	
}