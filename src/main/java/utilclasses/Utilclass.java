package utilclasses;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utilclass {
	WebDriver driver;
	
  public static void  moveToElement(WebDriver driver,WebElement element)
  {
	  Actions act=new Actions(driver);
	  act.moveToElement(element).pause(2000).build().perform();
  }
  public static void moveByOffset(WebDriver driver)
  {
	  Actions act=new Actions(driver);
	  act.moveByOffset(200, 0).release().build().perform();
  }
  public static boolean isElementVisible(WebDriver driver,WebElement element)
  {
	  WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	  return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
  }
  public static  List<String> getMultipleDataFromXcel(int firstRow,int lastRow) throws EncryptedDocumentException, IOException
  {
	  FileInputStream file=new FileInputStream("E:\\Testing notes\\Tesingexcel\\testng.xlsx");
	  List<String> datalist=new ArrayList<String>();
	  Sheet sheet=WorkbookFactory.create(file).getSheet("Sheet1");
	  for(int i=firstRow;i<=lastRow;i++)
	  {
		  try
		  {
			  
			  String Stringdata=sheet.getRow(i).getCell(1).getStringCellValue();
			  datalist.add(Stringdata);
			  
		  }catch(Exception e)
		  {
			  long intdata=(long)sheet.getRow(i).getCell(1).getNumericCellValue();
			  String string=String.valueOf(intdata);
			  datalist.add(string);
		  }
		 
	  }
	  return datalist;
	
  }
  public String screenCpature(WebDriver driver) throws IOException {
		
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("MM-dd-mm-hh-ss").format(date);
		
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("screenShot" + modifiedDate +".png");
		String path = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return path;
		}
  public String getConfigData(String key) throws IOException
  {
	  FileInputStream file=new FileInputStream("configure\\config.properties");
	  Properties prop=new Properties();
	  prop.load(file);
	  return prop.getProperty(key);
  }
  
  
}
