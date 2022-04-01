package pomclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilclasses.Utilclass;

public class Hompage extends Utilclass{
	
	WebDriver driver;
	
	@FindBy(xpath="//div[@class='_28p97w']")
	private WebElement profilename;
	
	@FindBy(xpath="//div[text()='My Profile']/parent::a/parent::li")
	private WebElement myprofiletext;
	
	@FindBy (xpath="(//li[@class='_2NOVgj'])[10]")
	private WebElement logoutTxt;
	
	public Hompage(WebDriver driver)
	{
	  PageFactory.initElements(driver,this);
	  this.driver=driver;
	}
    public void hoverToProfileName()
    {
    	isElementVisible(driver,profilename);
    	moveToElement(driver,profilename);
    }
    public void clickOnMyProfileTxt()
    {
    	isElementVisible(driver,myprofiletext);
    	myprofiletext.click();
    }
    public String getLogoutTxt()
    {
    	return logoutTxt.getText();
    }
    public void movePointer() throws InterruptedException
    {
    	moveByOffset(driver);
    	Thread.sleep(2000);
    	
    }
}
