package Modules ;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import XeroScripts.ReusableMethods;



public class LoginModule extends ReusableMethods {
	
	public  WebDriver loginToXero(WebDriver driver) throws Exception{
		String currDir=System.getProperty("user.dir");
		String propertyPath=currDir+"/src/test/resources/DataFiles/Configuration.properties";
		Properties pro=loadPropertyFile(propertyPath);
		
		String objectRepoPath=currDir+"/src/test/resources/ObjectRepository/ObjectsRepo.properties";
		Properties objPro=loadPropertyFile(objectRepoPath);
		WebElement username= driver.findElement(getLocator("xero.login.username",objPro));		
		enterText(username, "username field",pro.getProperty("username"));
		WebElement password=driver.findElement(getLocator("xero.login.password",objPro));
		enterText(password, "password field",pro.getProperty("password"));
		WebElement loginButton= driver.findElement(By.xpath("//button[@id='submitButton']"));
		clickElement(loginButton, "Login Button");

		return driver;
		
	}
			
	
}	
	
	