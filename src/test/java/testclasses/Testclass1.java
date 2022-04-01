package testclasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import baseclasses.Baseclass;
import pomclasses.Hompage;
import pomclasses.Loginpage;
import pomclasses.Profilepage;

public class Testclass1 {
      WebDriver driver;
      Loginpage lp;
      Hompage hp;
      Profilepage pp;
      
  	ExtentReports reports;
  	ExtentTest test;
   
     @Parameters("browser")
     @BeforeClass
     public void beforeClass(String a)
     
     {
    	reports = new ExtentReports("ExtentReports.html",true);
 		test = reports.startTest("AddNewAddress");
    	 driver=Baseclass.getWebDriver(a);
    	 
     }
     
     @BeforeMethod
     public void beforeMethod()
     {
    	 lp=new Loginpage(driver);
    	 hp=new Hompage(driver);
    	 pp=new Profilepage(driver);
    	 
     }
      
	@Test
	public void verifyUsercanLogin() throws InterruptedException, IOException
	{
	 lp.enterEmailId();
	 lp.enterPassword();
	 lp.clickSubmitbutton();
	 hp.hoverToProfileName();
	 String txt=hp.getLogoutTxt();
	 Assert.assertEquals(txt,"Logout");
	 
	 test.log(LogStatus.PASS, "Log in Test Passed");
	}
	
	@DataProvider(name="dataset")
	   public String[][] dataToset()
	  {
		String[][] data= {{"Ganesh","9765113397","413102","pune"},{"ramdas","8888913398","413202","nashik"}};
		return data;
		
		
	}
	

	@Test (priority=1,dataProvider="dataset")
	public void verifyUserCanAddNewAddress(String name,String mobno,String pincode,String locality) throws EncryptedDocumentException, IOException, InterruptedException
	{
		hp.hoverToProfileName();
		hp.clickOnMyProfileTxt();
		pp.clickOnManageAddress();
		pp.clickOnAddNewAddress();
		
		List<String> datalist=new ArrayList<String>();
		datalist.add(name);
		datalist.add(mobno);
		datalist.add(pincode);
		datalist.add(locality);
		
		
		int oldCount = pp.getAddressCount();
		pp.getDataForAddress(datalist);
		Thread.sleep(2000);
        pp.enterAddressLine1();
    	Thread.sleep(2000);
		pp.clickOnSaveAddressButton();
		Thread.sleep(2000);
		int newCount = pp.getAddressCount();			
		
		Assert.assertEquals(newCount, oldCount+1);
		
		test.log(LogStatus.PASS, "Added new Address");
		
	}
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL, test.addScreenCapture(pp.screenCpature(driver)), "Taken Screenshot");
		}
	}
	
	@AfterClass
	public void afterClass()
	{
		reports.endTest(test);
		reports.flush();
		
		//driver.quit();
		//driver.quit();
		
		//driver.quit();
		//driver.quit();
		
		
		
		
		
		
	}
	
}
