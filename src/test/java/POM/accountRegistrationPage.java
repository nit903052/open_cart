package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class accountRegistrationPage extends basepage{

	public accountRegistrationPage(WebDriver driver)
	{
		super(driver);
    }

	
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement FirstName;
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement lastname;
	@FindBy(xpath="//input[@id='input-email']")
	WebElement email;
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement telephone;
	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement confirmpassword;
	@FindBy(xpath="//label[normalize-space()='Yes']")
	WebElement checkbox;
	@FindBy(xpath="//input[@name='agree']")
	WebElement Agreement;
	@FindBy(xpath="//input[@value='Continue']")
	WebElement continueButton;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement message;
	
	
	
	
	public void setfirstname (String fname)
	{
		FirstName.sendKeys(fname);
	}
	public void setlastname (String lname)
	{
		lastname.sendKeys(lname);
	}
	public void setemail (String Email)
	{
		email.sendKeys(Email);
	}
	public void settelephonenum (String number)
	{
		telephone.sendKeys(number);
	}
	public void setpassword (String pass)
	{
		password.sendKeys(pass);
	}
	
	public void setconfirmPass (String cnf_pass)
	{
		confirmpassword.sendKeys(cnf_pass);
	}
	public void chkbox ()
	{
		checkbox.click();;
	}

	public void agreementt ()
	{
		Agreement.click();;
	}
	public void ContinueBtn ()
	{
		continueButton.click();
	}
	
	
	public String  confirmation()
	{
		try
		{
	      return message.getText();
	    }
	catch (Exception e)
	    {
		return (e.getMessage());
		}
	}
	
	
	
}
