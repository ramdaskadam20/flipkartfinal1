package pomclasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilclasses.Utilclass;

public class Loginpage extends Utilclass{
WebDriver driver;
	
	@FindBy (xpath="(//input[@type='text'])[2]")
	private WebElement emailId;
	
	@FindBy (xpath="//input[@type='password']")
	private WebElement password;
	
	@FindBy (xpath="(//*[@type='submit'])[2]")
	private WebElement submitButton;
	
	public Loginpage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		this.driver=driver;
		
	}
	public void enterEmailId() throws IOException
	{
		//emailId.sendKeys("ramdaskdm2012@gmail.com");
		emailId.sendKeys(getConfigData("UN"));
	}
	public void enterPassword() throws IOException
	{
		//password.sendKeys("Ramdas@20");
		password.sendKeys(getConfigData("pass"));
	}
	public void clickSubmitbutton() throws InterruptedException
	{
		submitButton.click();
		Thread.sleep(2000);
	}
}
