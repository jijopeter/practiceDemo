package datadriventest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class datadriventestng {
	WebDriver driver;
	@BeforeClass
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\eclipse-workspace\\JavaLearning\\server\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	@Test(dataProvider="LoginData")
	public void logintest(String user,String pass,String exp)
	{
		System.out.println(user + pass +exp);
		driver.get("https://admin-demo.nopcommerce.com/login");
		WebElement txtEmail=driver.findElement(By.xpath("//input[@id='Email']"));
		txtEmail.clear();
		txtEmail.sendKeys(user);
		WebElement txtpassword=driver.findElement(By.xpath("//input[@id='Password']"));
		txtpassword.clear();
		txtpassword.sendKeys(pass);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		String exp_title="Dashboard / nopCommerce administration";
		String act_title=driver.getTitle();
		if(exp.equals("valid"))
		{
			if(exp_title.equals(act_title))
			{
				driver.findElement(By.linkText("Logout")).click();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}else if(exp.equals("invalid"))
		{
			if(exp_title.equals(act_title))
			{
				driver.findElement(By.linkText("Logout")).click();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		} 
	}
	@DataProvider(name="LoginData")
	public String[][] getdata() throws IOException
	{
		/*String logindata[][]= {
				{"admin@yourstore.com","admin","valid"},
				{"admin@yourstore.com","admi","invalid"},
				{"adm@yourstore.com","admin","invalid"},
				{"adm@yourstore.com","admi","invalid"}
		};*/
		//get the data from excel
		String path=".\\datafiles\\loginData.xlsx";
		XLUtility xlutil=new XLUtility(path);
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);
		String loginData[][]=new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				loginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
	}
	@AfterClass
	void teardown()
	{
		driver.close();
	}

}
