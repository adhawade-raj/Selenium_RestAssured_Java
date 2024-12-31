package playwright;


import com.microsoft.playwright.*;

public class App {
    public static void main(String[] args) {
    	
    	
    	Playwright playwright = Playwright.create();
    	      BrowserType chromium = playwright.chromium();
    	      Browser browser = chromium.launch(new BrowserType.LaunchOptions().setHeadless(false));
    	      BrowserContext context = browser.newContext();
    	      Page page = context.newPage();
    	      page.navigate("https://google.com");
    	      System.out.println(page.title());
//    	      page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));
//    	      browser.close();
    	    
    }
}
