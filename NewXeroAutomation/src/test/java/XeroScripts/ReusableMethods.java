package XeroScripts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReusableMethods extends SalesforceBaseClass{
	 public static ExtentHtmlReporter htmlReporter;
	 public static ExtentReports extent=null;
	 public static ExtentTest logger=null;
//	 public static Properties pro;      // I was trying to define property instances pro and objPro just once ....thru this
//	 String currDir=System.getProperty("user.dir");
//	 String propertyPath=currDir+"/src/test/resources/DataFiles/Configuration.properties";
//				
//	 String objectRepoPath=currDir+"/src/test/resources/ObjectRepository/ObjectsRepo.properties";
		
	 public static void enterText (WebElement webElement,String webElementName,String text)
		{
			if (webElement.isDisplayed())
			{		
				webElement.sendKeys(text);
				logger.log(Status.PASS,text+" entered in " +webElementName);		
			}
			else
				logger.log(Status.FAIL, MarkupHelper.createLabel(webElementName+" Text box not found ", ExtentColor.RED));


		}
	 
	 public static void clickElement (WebElement element, String elementName)
		{
			if (element.isDisplayed())
			{
				logger.log(Status.PASS,elementName+" is clicked");
				element.click();
			}
			else
				logger.log(Status.FAIL, MarkupHelper.createLabel( elementName+" is not found", ExtentColor.RED));
		}
	 
	 public static void verifyText(WebElement element,String elementName,String expectedText ) throws IOException
		{
			if (element.isDisplayed())
			{	
				if (element.getText().toString().equals(expectedText))
					logger.log(Status.PASS, MarkupHelper.createLabel( elementName+" is displayed as expected", ExtentColor.GREEN));
				else{
					logger.log(Status.FAIL, MarkupHelper.createLabel( elementName+"is NOT as expected", ExtentColor.RED));
				String imagePath=takeScreenShot();
				logger.addScreenCaptureFromPath(imagePath);
				}
			}
			else
			{
				logger.log(Status.FAIL, MarkupHelper.createLabel( elementName+" not found", ExtentColor.RED));
			}
		}



	 
	public static String takeScreenShot() throws IOException{
		String reportPath=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String curDir=System.getProperty("user.dir");
		String destination=curDir+"/TestReports/screenshots/"+reportPath+"image.PNG";
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(destination),true);
		return destination;
	}
	public static void initializeExtentReport(String reportName){
		 String reportPath=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/TestReports/"+reportPath+reportName+".html");
		 extent = new ExtentReports ();
		 extent.attachReporter(htmlReporter);
		 extent.setSystemInfo("Host Name", "TekArch");
		 extent.setSystemInfo("Environment", "QA Automation");
		 extent.setSystemInfo("User Name", "Divyashree");
		 
		 htmlReporter.config().setDocumentTitle("My Simple report");
		 htmlReporter.config().setReportName("First execution");
		 htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		 htmlReporter.config().setTheme(Theme.STANDARD);
	}
	public static void createTestScriptReport(String testScriptName){
	//	logger = extent.createTest("Login_Error_Message_1");
		logger = extent.createTest(testScriptName);
	}
	public static void endExtentReport(){
		extent.flush();
	}
	
	public static String[][] readxlData(String path,String sheetName) throws IOException{
		
		FileInputStream fs=new FileInputStream(new File(path));
		HSSFWorkbook workbook=new HSSFWorkbook(fs);
		HSSFSheet sheet=workbook.getSheet(sheetName);
		int rows=sheet.getLastRowNum()+1;
		int cols=sheet.getRow(0).getLastCellNum();
		String[][] data=new String[rows][cols];
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				//data[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				HSSFCell cell=sheet.getRow(i).getCell(j);
				if(cell.getCellType()==CellType.STRING)
					data[i][j]=cell.getStringCellValue();
				else if(cell.getCellType()==CellType.NUMERIC)
					data[i][j]=String.valueOf(cell.getNumericCellValue());
			
			}
		}
		return data;
		
	}
	public static Properties loadPropertyFile(String path) throws IOException{
		Properties pro=new Properties();
		BufferedReader reader = new BufferedReader(new FileReader(path));
		pro.load(reader);
		return pro;
	}
	
	public static By getLocator(String strElement,Properties propertyFile) throws Exception {
        
        // retrieve the specified object from the object list
        String locator = propertyFile.getProperty(strElement);
         
        // extract the locator type and value from the object
        String locatorType = locator.split(":")[0];
        String locatorValue = locator.split(":")[1];
         
        // for testing and debugging purposes
        System.out.println("Retrieving object of type '" + locatorType + "' and value '" + locatorValue + "' from the object map");
         
        // return a instance of the By class based on the type of the locator
        // this By can be used by the browser object in the actual test
        if(locatorType.toLowerCase().equals("id"))
            return By.id(locatorValue);
        else if(locatorType.toLowerCase().equals("name"))
            return By.name(locatorValue);
        else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
            return By.className(locatorValue);
        else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
            return By.className(locatorValue);
        else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
            return By.linkText(locatorValue);
        else if(locatorType.toLowerCase().equals("partiallinktext"))
            return By.partialLinkText(locatorValue);
        else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
            return By.cssSelector(locatorValue);
        else if(locatorType.toLowerCase().equals("xpath"))
            return By.xpath(locatorValue);
        else
            throw new Exception("Unknown locator type '" + locatorType + "'");
    }

}
