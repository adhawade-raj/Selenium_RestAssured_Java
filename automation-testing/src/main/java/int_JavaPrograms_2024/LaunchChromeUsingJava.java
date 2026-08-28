package int_JavaPrograms_2024;

import java.io.IOException;

public class LaunchChromeUsingJava {

	public static void main(String[] args) throws IOException {
		
		Runtime runtime =  Runtime.getRuntime();
		
		String s[] = new String[] {"FilePath/chrome.exe","www.google.com"};
		Process pr =runtime.exec(s);
		pr.destroy(); //To kill the session
	}

}
