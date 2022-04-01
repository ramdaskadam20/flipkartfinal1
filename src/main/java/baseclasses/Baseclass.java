package baseclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class Baseclass {
	
	public static WebDriver getWebDriver(String a)
	{
		
		
		if(a.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
	
		return driver;
		}
		else
		{
			System.setProperty("webdriver.gecko.driver","C:\\Program Files\\geckodriver.exe");
			WebDriver driver=new FirefoxDriver();
			driver.get("https://www.flipkart.com/");
			driver.manage().window().maximize();
			
			return driver;
			
		}
		
		
		//system
		//system
	}
}
