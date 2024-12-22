package FromSelenium_19;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ListWithStreamAndFilter {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
		By links = By.tagName("a");
		
		List<WebElement> LinkList = driver.findElements(links);
		
		System.out.println(LinkList.size());
		
		List<String>CollectionList= LinkList.stream()
				.filter(ele -> !ele.getText().equals(""))
				.map(ele ->ele.getText())
				.collect(Collectors.toList());
		
		System.out.println(CollectionList);
		
		CollectionList.stream().forEach(ele -> System.out.println(ele));
		
//		CollectionList.parallelStream().forEach(ele -> System.out.println(ele));
	}

}
