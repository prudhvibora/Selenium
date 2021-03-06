package com.CloudApp_Project;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class Base_Class extends Excel_User_Data 
 
{
	public static WebDriver driver;

	@BeforeTest

	public void Openbrowser() throws EncryptedDocumentException, InvalidFormatException, IOException
	{
		String browser = Excel_User_Data.readdata("Sheet1",1,6);
		String URL = Excel_User_Data.readdata("Sheet1",1,2);
		if (browser.equals("Firefox"))
		{
			System.setProperty("webdriver.chrome.driver","C:\\Automation drivers\\chrome driver\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browser.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver","C:\\Automation drivers\\gecko drivers\\geckodriver.exe");
			driver=new FirefoxDriver();
		}

		else 
		{ 
			System.out.println("invalid data");
		}
		driver.get(URL);
	}   

	@BeforeClass

	public void login() throws EncryptedDocumentException, InvalidFormatException, IOException, InterruptedException
	{
		String username = Excel_User_Data.readdata("Sheet1",1,3);
		String password = Excel_User_Data.readdata("Sheet1",1,4);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.id("email")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("commit")).click();
		Thread.sleep(4000);
		String text= driver.findElement(By.xpath("//div[@class= 'flash alert alert-success']")).getText();
		System.out.println(text);
		//Assert.assertTrue(text.equals("Welcome back!"));
	}

	@AfterClass

	public void logout() throws InterruptedException

		//		WebElement w1= driver.findElement(By.xpath("//*[@id= 'dropdownMenuLink']"));
		//		 
		//		Select s= new Select(w1);
		//		
		//		s.selectByVisibleText("Sign out");
	
		//		java.util.List<WebElement> myElements = driver.findElements(By.className("dropdown-menu nav-item"));
		//	       for(WebElement e : myElements) {
		//	         if(e.getText().equals("Sign out")) 
		//	         {
		//	        	 e.click();
		//	             
		//	         }
		{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id= 'dropdownMenuLink']")).click();
		WebElement w = driver.findElement(By.xpath("//a[@data-testid= 'nav-signout-link']"));
		Actions s= new Actions(driver);
		s.click(w);
		s.perform();	
	}

	@AfterTest 

	public void closebrowser()
	{
		driver.close();

	}
}

