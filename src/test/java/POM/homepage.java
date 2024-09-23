package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class homepage extends basepage{

	
	
	
   public homepage(WebDriver driver) {
		super(driver);
	}
  
   
   @FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement Myaccount;
   
   
   @FindBy(xpath="//a[normalize-space()='Register']")
  	WebElement Register;
   
   @FindBy(xpath="//a[normalize-space()='Login']")
  	WebElement Login;
   
	
   public void clickmyaccount ()
   {
	   (Myaccount).click();
   }
   
   public void clickregistration ()
   {
	   (Register).click();
   }
	
   public void login()
   {
	   Login.click();
   }
   
   
}
