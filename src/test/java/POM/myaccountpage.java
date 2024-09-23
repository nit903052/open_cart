package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class myaccountpage extends basepage
  {

	public myaccountpage(WebDriver driver) 
	{
		super(driver);
	}

	   @FindBy(xpath="//a[@title='My Account']")
	  	WebElement Myprofile;
	   @FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
	  	WebElement Logout;
	   @FindBy(xpath="//h2[normalize-space()='My Account']")
	  	WebElement msgHeading;
	   
	   
	public void myprofile()
	{
		Myprofile.click();
	}
	
	public void logout()
	{
		Logout.click();
	}
	
	//String textAtPage= driver.findElement(By.xpath(" //h2[normalize-space()='My Account']")).getText();
	
	public boolean verification()
	{
		try {
		return(msgHeading.isDisplayed());
		}
		catch(Exception e)
		{
		return false;
		}
	}
	
	
	
  }


	
	

