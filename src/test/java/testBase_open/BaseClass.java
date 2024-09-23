package testBase_open;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	  public static  WebDriver driver ;
	  public Logger logger;
	  public Properties p;
	  
	  
	    @SuppressWarnings("deprecation")
		@BeforeClass(groups= {"sanity","regression","master"})
	    @Parameters({"OS","browser"})
		public void setup(String os , String br) throws IOException
		{		  
	    	//Loading Configuration file
	    	
	    	FileReader file = new FileReader("./src//test//resources//config.properties");
	    	p=new Properties();
	    	p.load(file);    // can access all the values from properties files
	    	
	    	
	    	
		    logger = LogManager.getLogger(this.getClass());   //Log4J2
		    
		    
		    
		    
		    
		    if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		   {
		    	DesiredCapabilities capbility = new DesiredCapabilities();
		    //	cap.setPlatform(Platform.WIN11);
		    //  cap.setBrowserName("chrome");
		    		
		    
		    	//os
		    if(os.equalsIgnoreCase("windows"))
		    {
		    	capbility.setPlatform(Platform.WIN11);
		    }
		    else if (os.equalsIgnoreCase("mac"))
		    {
		    	capbility.setPlatform(Platform.MAC);
		    }
		    else
		    {
		    	System.out.println("No Operating system is matched");
		    	return;
		    }
		    
		    //browser
		    
		    
		    driver = new RemoteWebDriver(new URL("http://172.21.16.1:4444"), capbility);
		    switch(br.toLowerCase())
		    {
		    case "chrome" : capbility.setBrowserName("chrome"); break;
		    case "edge" : capbility.setBrowserName("MicrosoftEdge"); break;
		    case "firefox" : capbility.setBrowserName("firefox"); break;
		    default : System.out.println("Invalid browser name"); return;
		    }
		    
		   // String URI_Name= "http://localhost:4444/wb/hub";
		  //  URI_Name
		    
		  //  driver = new RemoteWebDriver(new URL("http://localhost:4444/wb/hub"), capbility);
		   }
		    
		    if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		   
		    {	
		    switch(br.toLowerCase())
		    {
		    case "chrome" : driver= new ChromeDriver(); break;
		    case "edge" : driver= new EdgeDriver(); break;
		    case "firefox" : driver= new FirefoxDriver(); break;
		    default : System.out.println("Invalid browser name"); return;
		    }
			
		    }
			
			
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			driver.get(p.getProperty("appURL"));  // reading url from properties file
			driver.manage().window().maximize();
		}
		
		@AfterClass(groups= {"sanity","regression","master"})
		public void teardown()
		{
			driver.quit();
		}
		
		
		
		
		
		
		public String randomData()
		{
		String generatedString=	RandomStringUtils.randomAlphabetic(5);
		return generatedString;
		}
		
		
		
		public String randomint()
		{
		String generatedint=	RandomStringUtils.randomNumeric(10);
		return generatedint;
		}
		
		
		public String randomAlphaNumeric()
		{
		String generatedString=	RandomStringUtils.randomAlphabetic(3);
		String generatedint=RandomStringUtils.randomNumeric(3);
		return (generatedString + generatedint);
		}
	
	
		public String captureScreenshot(String tname)
		{
		
		String timeStamp= new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;	
		
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\ScreenShot\\"+tname +"_"+ timeStamp + ".png";
		
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		
		}
		
}



