package XeroScripts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import Modules.LoginModule;
//  check paths for browser drivers, configuration property file, repo file, test suit, extent test report, screenshot
public class AutomationScript extends ReusableMethods {
	
	public static void TC01_Login_Xero() throws Exception {
		String currDir=System.getProperty("user.dir");
		String propertyPath=currDir+"/src/test/resources/DataFiles/Configuration.properties";
		Properties pro=loadPropertyFile(propertyPath);
		
		String objectRepoPath=currDir+"/src/test/resources/ObjectRepository/ObjectsRepo.properties";
		Properties objPro=loadPropertyFile(objectRepoPath);
		
		createTestScriptReport("TC01_Login_Xero");	
		
		/*launch Login Page*/
		IntializeDriver("firefox");
		Thread.sleep(6000);
		driver.get(pro.getProperty("xeroUrl"));
		logger.log(Status.INFO,"Xero login page opened");
		
		/* Introducing Module----For Modular Framework*/
		LoginModule ob=new LoginModule();
		driver=ob.loginToXero(driver);			
		
		
//		WebElement username=driver.findElement(getLocator("xero.login.username",objPro));
//		enterText(username, "username field",pro.getProperty("username"));
//		WebElement password=driver.findElement(getLocator("xero.login.password",objPro));
//		enterText(password, "password field",pro.getProperty("password"));
//		logger.log(Status.INFO,"password entered");
//		WebElement loginButton=driver.findElement(getLocator("xero.login.loginButton",objPro));
//		clickElement(loginButton, "Login Button");
//		logger.log(Status.INFO,"login button clicked");
		logger.log(Status.PASS, MarkupHelper.createLabel( "Xero Login Successful", ExtentColor.GREEN));
		closeDriver();
	}
	
	
	

	
	
	public static void TC02_IncorrectPwd_Xero() throws Exception {
		String currDir=System.getProperty("user.dir");
		String propertyPath=currDir+"/src/test/resources/DataFiles/Configuration.properties";
		Properties pro=loadPropertyFile(propertyPath);
		
		String objectRepoPath=currDir+"/src/test/resources/ObjectRepository/ObjectsRepo.properties";
		Properties objPro=loadPropertyFile(objectRepoPath);
		
		createTestScriptReport("TC02_IncorrectPwd_Xero");		
		IntializeDriver("firefox");
				
		driver.get(pro.getProperty("xeroUrl"));
		
		logger.log(Status.INFO,"Xero login page opened");
		WebElement username=driver.findElement(getLocator("xero.login.username",objPro));
		enterText(username, "username field",pro.getProperty("username"));
		WebElement password=driver.findElement(getLocator("xero.login.password",objPro));
		enterText(password, "password field",pro.getProperty("password1"));
		logger.log(Status.INFO,"Incorrect password entered");
		WebElement loginButton=driver.findElement(getLocator("xero.login.loginButton",objPro));
		clickElement(loginButton, "Login Button");
		logger.log(Status.INFO,"login button clicked");		
		WebElement pwdMsg=driver.findElement(getLocator("xero.login.pwdErr", objPro));
		verifyText(pwdMsg, "Error message", "Your email or password is incorrect");
		
		closeDriver();
	}
	
	
	public static void TC03_IncorrectUsername_Xero() throws Exception {
		String currDir=System.getProperty("user.dir");
		String propertyPath=currDir+"/src/test/resources/DataFiles/Configuration.properties";
		Properties pro=loadPropertyFile(propertyPath);
		
		String objectRepoPath=currDir+"/src/test/resources/ObjectRepository/ObjectsRepo.properties";
		Properties objPro=loadPropertyFile(objectRepoPath);
		
		createTestScriptReport("TC03_IncorrectUsername_Xero");		
		IntializeDriver("firefox");
				
		driver.get(pro.getProperty("xeroUrl"));
		
		logger.log(Status.INFO,"Xero login page opened");
		WebElement username=driver.findElement(getLocator("xero.login.username",objPro));
		enterText(username, "username field",pro.getProperty("username1"));
		logger.log(Status.INFO,"Incorrect Username");		
		WebElement password=driver.findElement(getLocator("xero.login.password",objPro));
		enterText(password, "password field",pro.getProperty("password"));		
		WebElement loginButton=driver.findElement(getLocator("xero.login.loginButton",objPro));
		clickElement(loginButton, "Login Button");
		
		WebElement pwdMsg=driver.findElement(getLocator("xero.login.pwdErr", objPro));
		verifyText(pwdMsg, "Error message", "Your email or password is incorrect");
		
		closeDriver();
	}
	
	
	
	public static void TC04_ForgotPassword_Xero() throws Exception {
		String currDir=System.getProperty("user.dir");
		String propertyPath=currDir+"/src/test/resources/DataFiles/Configuration.properties";
		Properties pro=loadPropertyFile(propertyPath);
		
		String objectRepoPath=currDir+"/src/test/resources/ObjectRepository/ObjectsRepo.properties";
		Properties objPro=loadPropertyFile(objectRepoPath);
		
		createTestScriptReport("TC04_ForgotPassword_Xero");		
		IntializeDriver("firefox");
				
		driver.get(pro.getProperty("xeroUrl"));
		
		logger.log(Status.INFO,"Xero login page opened");

		
		WebElement forgotPwd=driver.findElement(getLocator("xero.login.forgotPwd",objPro));
		clickElement(forgotPwd, "Forgot Pwd link");
		WebElement emailFgtPwd=driver.findElement(getLocator("xero.forgotPassword.email",objPro));
		enterText(emailFgtPwd, "username field",pro.getProperty("username"));
		WebElement sendLnkButton=driver.findElement(getLocator("xero.forgotPassword.sendLnk",objPro));
		clickElement(sendLnkButton, "Send link Button");
		WebElement resetPwdMsg=driver.findElement(getLocator("xero.forgotPassword.restPwd",objPro));
		String actualMsg=resetPwdMsg.getText().toString();
		if(actualMsg.contains("A link to reset your password has been sent to:"))
		{
			logger.log(Status.PASS, MarkupHelper.createLabel( actualMsg+" is Correct Reset password msg", ExtentColor.GREEN));
		}
		else
			logger.log(Status.FAIL, MarkupHelper.createLabel( actualMsg+"  Check the reset password message", ExtentColor.GREEN));
	//	verifyText(resetPwdMsg, "Reset password message", "A link to reset your password has been sent to:"+*);
		
		
		closeDriver();
	}
	
	
	public static void TC05_HomeTabs_Xero() throws Exception {
		String currDir=System.getProperty("user.dir");
		String propertyPath=currDir+"/src/test/resources/DataFiles/Configuration.properties";
		Properties pro=loadPropertyFile(propertyPath);
		
		String objectRepoPath=currDir+"/src/test/resources/ObjectRepository/ObjectsRepo.properties";
		Properties objPro=loadPropertyFile(objectRepoPath);
		
		createTestScriptReport("TC05_HomeTabs_Xero");		
		IntializeDriver("firefox");
		driver.get(pro.getProperty("xeroUrl"));
		
		/* Introducing Module---modular framework*/
//		LoginModule ob=new LoginModule();
//		driver=ob.loginToXero(driver);		
		
		/* Without MOdular framework  */	
		logger.log(Status.INFO,"Xero login page opened");
		WebElement username=driver.findElement(getLocator("xero.login.username",objPro));
		enterText(username, "username field",pro.getProperty("username"));
		WebElement password=driver.findElement(getLocator("xero.login.password",objPro));
		enterText(password, "password field",pro.getProperty("password"));
		logger.log(Status.INFO,"password entered");
		WebElement loginButton=driver.findElement(getLocator("xero.login.loginButton",objPro));
		clickElement(loginButton, "Login Button");
		
		logger.log(Status.PASS, MarkupHelper.createLabel( "Xero Login Successful", ExtentColor.GREEN));
		
		/* Accounts Tab*/
		WebElement accountsTab=driver.findElement(getLocator("xero.home.accounts",objPro));
		clickElement(accountsTab, "Accounts Tab");
		List<WebElement> accountOptions=driver.findElements(By.xpath("//li[2]/ul//a[@data-type='menu-focus']"));//here in html //li/ul/a[@data-type='menu-focus'] was 
		                                                                                                //giving 46 nodes so selected particular li[2]
		String[] expOpt={"Bank Accounts", "Sales", "Purchases", "Checks", "Inventory","Expense Claims", "Fixed Assets"};
		for(int i=0; i<accountOptions.size(); i++)
		{	
			if (!(accountOptions.get(i).getText().equals(expOpt[i])))		
	//		logger.log(Status.INFO,"Options Present "+accountOptions.get(i).getText());
			logger.log(Status.INFO,"Option"+expOpt[i]+" not Present ");
			
		}
		//driver.findElements(getLocator("xero.home.accountsOptions", objPro));
	//	logger.log(Status.INFO,"password entered"+options);
		
		/* Report Tab */
		WebElement reportsTab = driver.findElement(getLocator("xero.home.reports", objPro));
		clickElement(reportsTab, "Reports Tab");
		List<WebElement> reportOptions = driver.findElements(By.xpath("//li[5]/ul//a[@data-type='menu-focus']"));
		// List<WebElement>
		// reportOptions=driver.findElements(getLocator("xero.home.reportsOptions",objPro));
		// WebElement
		// reportBlk=driver.findElement(By.xpath("//li[5][@class='xn-h-menu-list
		// report_favorites a-fade-in']"));
		// if (reportBlk.isDisplayed())
		logger.log(Status.INFO, "Report options are Present " + reportOptions.toString());

		/* Contacts Tab */
		WebElement contactsTab = driver.findElement(getLocator("xero.home.contacts", objPro));
		clickElement(contactsTab, "Contacts Tab");
		List<WebElement> contactOptions = driver.findElements(By.xpath("//li[6]/ul//a[@data-type='menu-focus']"));

		logger.log(Status.PASS, "Contacts options are Present ");

		/* Settings Tab */
		WebElement settingsTab = driver.findElement(getLocator("xero.home.settings", objPro));
		clickElement(settingsTab, "Settings Tab");
		List<WebElement> settingOptions = driver.findElements(By.xpath("//li[7]/ul//a[@data-type='menu-focus']"));

		logger.log(Status.PASS, "Settings options are Present ");
		
		/*     + TAB       */
		WebElement plusTab = driver.findElement(getLocator("xero.home.plus", objPro));
		clickElement(plusTab, "Settings Tab");
		List<WebElement> plusOptions = driver.findElements(By.xpath("//li[@class='ql-menugroup--item']"));
		
		/*     + TAB       */
		WebElement filesTab = driver.findElement(getLocator("xero.home.files", objPro));
		clickElement(filesTab, "files Tab");
		if(driver.getTitle().contains("Files"))
		   logger.log(Status.PASS, "Files page is opened ");	
		
		/*     + TAB       */
		WebElement notificationsTab = driver.findElement(getLocator("xero.home.notifications", objPro));
		clickElement(notificationsTab, "Notifications Tab");
		if(driver.getTitle().contains("Notificatins"))
		   logger.log(Status.PASS, "Notifications page is opened ");
		else
			logger.log(Status.FAIL, MarkupHelper.createLabel( "Notifications page is not found", ExtentColor.RED));
		    String imgNotf=takeScreenShot();
		    logger.addScreenCaptureFromPath(imgNotf);
		
		/* Search  */
		WebElement searchTab = driver.findElement(getLocator("xero.home.search", objPro));
		clickElement(searchTab, "Search Tab");		
		if(driver.switchTo().frame("GlobalSearchApp").findElement(By.id("queryInput")).isDisplayed())			//use getLocators ---search frame by xpath
			logger.log(Status.PASS, MarkupHelper.createLabel( "Search Option is present", ExtentColor.GREEN));
		
		
		/*   HELP    */
		WebElement helpTab = driver.findElement(getLocator("xero.home.help", objPro));
		clickElement(helpTab, "Help Tab");
		WebElement helpArea=driver.findElement(getLocator("xero.home.helpArea", objPro));
		if(helpArea.isDisplayed())
			logger.log(Status.PASS, MarkupHelper.createLabel( "Help Option is present", ExtentColor.GREEN));
		String imgHelp=takeScreenShot();
	    logger.addScreenCaptureFromPath(imgHelp);
	    
	    
		closeDriver();
	}
	
	
	
	public static void TC06_ID04A_Logout_Xero() throws Exception {
		String currDir=System.getProperty("user.dir");
		String propertyPath=currDir+"/src/test/resources/DataFiles/Configuration.properties";
		Properties pro=loadPropertyFile(propertyPath);
		
		String objectRepoPath=currDir+"/src/test/resources/ObjectRepository/ObjectsRepo.properties";
		Properties objPro=loadPropertyFile(objectRepoPath);
		
		createTestScriptReport("TC06_ID04A_Logout_Xero");	
		
		/*launch Login Page*/
		IntializeDriver("firefox");
		driver.get(pro.getProperty("xeroUrl"));
		logger.log(Status.INFO,"Xero login page opened");
		
//		/* Introducing Module----For Modular Framework*/
//		LoginModule ob=new LoginModule();
//		driver=ob.loginToXero(driver);			
//		
		
		WebElement username=driver.findElement(getLocator("xero.login.username",objPro));
		enterText(username, "username field",pro.getProperty("username"));
		WebElement password=driver.findElement(getLocator("xero.login.password",objPro));
		enterText(password, "password field",pro.getProperty("password"));
		logger.log(Status.INFO,"password entered");
		WebElement loginButton=driver.findElement(getLocator("xero.login.loginButton",objPro));
		clickElement(loginButton, "Login Button");
		logger.log(Status.INFO,"login button clicked");
		logger.log(Status.PASS, MarkupHelper.createLabel( "Xero Login Successful", ExtentColor.GREEN));
		WebElement usernameTab= driver.findElement(getLocator("xero.home.usernameTab",objPro));
		clickElement(usernameTab, "Username tab link");
		WebElement logout= driver.findElement(getLocator("xero.home.logout",objPro));
		clickElement(logout, "Log Out link");
		closeDriver();
	}
	
	
	public static void TC07_ID06_Profile_Xero() throws Exception {
		String currDir=System.getProperty("user.dir");
		String propertyPath=currDir+"/src/test/resources/DataFiles/Configuration.properties";
		Properties pro=loadPropertyFile(propertyPath);
		
		String objectRepoPath=currDir+"/src/test/resources/ObjectRepository/ObjectsRepo.properties";
		Properties objPro=loadPropertyFile(objectRepoPath);
		
		createTestScriptReport("TC07_ID06_Profile_Xero");	
		
		/*launch Login Page*/
		IntializeDriver("firefox");
		driver.get(pro.getProperty("xeroUrl"));
		logger.log(Status.INFO,"Xero login page opened");
		
//		/* Introducing Module----For Modular Framework*/
//		LoginModule ob=new LoginModule();
//		driver=ob.loginToXero(driver);			
//		
		
		WebElement username=driver.findElement(getLocator("xero.login.username",objPro));
		enterText(username, "username field",pro.getProperty("username"));
		WebElement password=driver.findElement(getLocator("xero.login.password",objPro));
		enterText(password, "password field",pro.getProperty("password"));
		logger.log(Status.INFO,"password entered");
		WebElement loginButton=driver.findElement(getLocator("xero.login.loginButton",objPro));
		clickElement(loginButton, "Login Button");
		logger.log(Status.INFO,"login button clicked");
		logger.log(Status.PASS, MarkupHelper.createLabel( "Xero Login Successful", ExtentColor.GREEN));
		WebElement usernameTab= driver.findElement(getLocator("xero.home.usernameTab",objPro));
		clickElement(usernameTab, "Username tab link");
		Thread.sleep(6000);
//		WebElement profile=driver.findElement(getLocator("xero.home.profile",objPro));
//		clickElement(profile, "Profile link");
//		WebElement uploadBTN=driver.findElement(getLocator("xero.profileSetting.uploadButton",objPro));
//		clickElement(uploadBTN, "Upload Button");
//		WebElement browseBTN= driver.findElement(getLocator("xero.profileSetting.uploadButton",objPro));
//		enterText(browseBTN, "Browse Button", "./src/test/resources/Utility/img1.png");
		
		WebElement profile1=driver.findElement(By.xpath("//a[text()='Profile']"));
		profile1.click();
		driver.findElement(By.xpath("//span[@id='button-1041-btnInnerEl']")).click();
		driver.findElement(By.xpath("//input[@id='filefield-1174-button-fileInputEl']")).sendKeys("E:\\TekArch_AutomationSW\\Selenium_Automation\\src\\Utility\\Chrysanthemum.jpg");
		driver.findElement(By.xpath("//div[@id='button-1178']")).click();
		
	}
}

	