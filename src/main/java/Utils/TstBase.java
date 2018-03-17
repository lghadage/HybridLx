package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TstBase {
	//public static final Logger logger = Logger.getLogger(TstBase.class.getName());

	public WebDriver driver;
	public Properties prop;
	public File f1;
	public FileInputStream fis;
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;

	/*static
	{
		Calendar calender= Calendar.getInstance();
		SimpleDateFormat formater= new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent=new ExtentReports(System.getProperty("user.dir")+"\\src\\main\\java\\Reports\\test"+formater.format(calender.getTime())+".html",false);
	}
*/
	public void getBrowser(String browser){
		if(System.getProperty("os.name").contains("Windows")){
			if(browser.equalsIgnoreCase("firefox")){
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
				driver=new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
				DesiredCapabilities capablities = DesiredCapabilities.chrome();
				//driver=new RemoteWebDriver(new URL(NodeURL),capablities);
				driver = new ChromeDriver(capablities);
				driver.manage().window().maximize();
			}
			else if(browser.equalsIgnoreCase("ie"))
			{
				System.setProperty("Webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
				DesiredCapabilities capablities=DesiredCapabilities.internetExplorer();
				capablities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				capablities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,"Url");
				capablities.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
				capablities.setCapability("requireWindowFocus",true);
				driver = new InternetExplorerDriver(capablities);
			}
		}

		else if(System.getProperty("os.name").equals("mac")){
			if(browser.equalsIgnoreCase("firefox")){
				System.out.println(System.getProperty("user.dir"));
				System.setProperty("WebDriver.geko.driver", "..\\src\\main\\java\\Drivers\\geckodriver-v0.19.0-arm7hf\\geckodriver.exe");
				driver=new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("WebDriver.chrome.driver", "..\\src\\main\\java\\Drivers\\chromedriver_win32\\chromedriver.exe");
				driver=new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("ie"))
			{
				System.setProperty("Webdriver.ie.driver", "..\\src\\main\\java\\Drivers\\IEDriverServer_x64_3.9.0\\IEDriverServer.exe");
				DesiredCapabilities capablities=DesiredCapabilities.internetExplorer();
				capablities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				capablities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL,"Url");
				capablities.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
				capablities.setCapability("requireWindowFocus",true);
				driver = new InternetExplorerDriver(capablities);
			}
		}
	}

	public void loadPropertiesFile() throws IOException{
		//String log4jConfigpath="log4j.properties";
		//PropertyConfigurator.configure(log4jConfigpath);	
		prop=new Properties();
		f1=new File(System.getProperty("user.dir") +"/src/main/java/Config_Files/Config.properties");
		fis= new FileInputStream(f1);
		prop.load(fis);
		//logger.info("Loading Config.properties file");


		f1=new File(System.getProperty("user.dir") +"/src/main/java/Object_Repository/ObjectRepository.Properties");
		fis= new FileInputStream(f1);
		prop.load(fis);
		//logger.info("Loading ObjectRepository.Properties file");

	}

	public WebElement getWebElement(String mstrConfigStr){

		WebElement element=null;
		String[] ObjectProperty=mstrConfigStr.split("|");
		String PropertyName=ObjectProperty[0];
		String PropertyValue=ObjectProperty[1];

		if(PropertyName.equalsIgnoreCase("name")){

			element=driver.findElement(By.name(PropertyValue));
			return element;	
		}

		if(PropertyName.equalsIgnoreCase("id")){
			element=driver.findElement(By.id(PropertyValue));
			return element;	
		}

		if(PropertyName.equalsIgnoreCase("classname")){
			element=driver.findElement(By.className(PropertyValue));
			return element;	
		}


		if(PropertyName.equalsIgnoreCase("linkText")){
			element=driver.findElement(By.linkText(PropertyValue));
			return element;	
		}


		if(PropertyName.equalsIgnoreCase("partiallinkText")){
			element=driver.findElement(By.partialLinkText(PropertyValue));
			return element;	
		}


		if(PropertyName.equalsIgnoreCase("tagName")){
			element=driver.findElement(By.tagName(PropertyValue));
			return element;	
		}


		if(PropertyName.equalsIgnoreCase("xpath")){
			element=driver.findElement(By.xpath(PropertyValue));
			return element;	
		}


		if(PropertyName.equalsIgnoreCase("cssSelector")){
			element=driver.findElement(By.cssSelector(PropertyValue));
			return element;	
		}
		return null;
	}

	@AfterMethod
	public void takeScreenshot(ITestResult result)
	{
		if(ITestResult.FAILURE==result.getStatus()){
			try{
				//Calendar calendar = Calendar.getInstance();
				//SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
				TakesScreenshot	ts= ((TakesScreenshot)driver);
				File source=ts.getScreenshotAs(OutputType.FILE);
				File DestFolder= new File(System.getProperty("user.dir")+"/src/main/java/Screenshots"+result.getName()+".png");
				FileUtils.copyFile(source, DestFolder);

			} catch (Exception e) 
			{
				System.out.println("Exception while taking screenshot "+e.getMessage());
			}
		}
	}



	@AfterClass(alwaysRun=true)
	public void endTest()
	{
		driver.quit();
		//extent.endTest(arg0);
		extent.flush();

	}



	public static void main (String[] args)
	{
		TstBase obj=new TstBase();		
		obj.getBrowser("chrome");
	}
}


