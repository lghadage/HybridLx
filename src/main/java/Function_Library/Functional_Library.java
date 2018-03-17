package Function_Library;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import Utils.TstBase;

public class Functional_Library extends TstBase {
		
	public Functional_Library funlib;
	
	
	public void userLogin(){
		String hoverLoginObj=prop.getProperty(prop.getProperty("UserIcon"));
		
		WebElement hoverMs= funlib.getWebElement(hoverLoginObj);
		Actions actions = new Actions(driver);
		actions.moveToElement(hoverMs).build().perform();
		
		String LoginClick=prop.getProperty("LoginButton");
		WebElement loginButton=funlib.getWebElement(LoginClick);
		loginButton.click();
		
		String userName=prop.getProperty("LoginUser");
		WebElement uName=funlib.getWebElement(userName);
		uName.sendKeys(prop.getProperty("Username"));
		
		String password=prop.getProperty("LoginPassword");
		WebElement Pass=funlib.getWebElement(password);
		uName.sendKeys(prop.getProperty("Password"));
		
		String LoginButton=prop.getProperty("ButtonLogin");
		WebElement LogButt=funlib.getWebElement(LoginButton);
		LogButt.click();	
	}

}
