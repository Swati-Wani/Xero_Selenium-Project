package XeroScripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class SalesforceBaseClass {
	public static WebDriver driver=null;
	public static WebDriver IntializeDriver(String name){
		//System.setProperty("webdriver.firefox.bin","C:/Program Files/Mozilla Firefox53/firefox.exe");
		if(name.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver","E:/Automation_Installables/geckodriver-v0.22.0-win64/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(name.equalsIgnoreCase("chrome")){
			System.out.println("chrome entered");
			System.setProperty("webdriver.chrome.driver","C:/unzipped/chromedriver_win32/chromedriver.exe");
			driver=new ChromeDriver();
			//driver.manage().window().maximize();
		}
		else if(name.equalsIgnoreCase("ie")){
			System.out.println("chrome entered");
			System.setProperty("webdriver.ie.driver","C:/unzipped/IEDriverServer_x64_3.14.0/IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		return driver;
	}
	public static void closeDriver(){
		driver.close();
	}

}
