package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginPage extends basepage {
	
	
	public loginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement username;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement pass;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement login;
	

	public void set_txt_email(String usnm)
	{
		username.sendKeys(usnm);
	}
	
	public void set_txt_pss(String pss)
	{
		pass.sendKeys(pss);
	}
	public void Button_login()
	{
		login.click();;
	}
	
	
}






