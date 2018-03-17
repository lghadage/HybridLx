package TestScripts;

import org.testng.annotations.Test;

import Function_Library.Functional_Library;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterClass;

public class LoginToMyntra extends Functional_Library {
	
	
	@BeforeClass
	public void beforeClass() 	
	{
		funlib.getBrowser("chrome");
		//funlib.getBrowser(prop.getProperty("Browser"));
		driver.get(prop.getProperty("Website"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();

	}

	@Test
	public void VerifyLogin() 
	{

		funlib.userLogin();

	}

	@AfterClass
	public void afterClass() 
	{

		funlib.endTest();
	}

}
