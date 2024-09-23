package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import POM.accountRegistrationPage;
import POM.homepage;
import testBase_open.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass
{
    @Test(groups={"regression","master"})
	public void verify_account_registration()
	{
    	
    	logger.info("*******Starting the test case*************");
    	
    try
       {
    	homepage hp = new homepage(driver);
		
		hp.clickmyaccount();
		
		hp.clickregistration();
		
		
		accountRegistrationPage regpage = new accountRegistrationPage(driver);
		
		logger.info("*******Feeding the data to test case*************");
		
		regpage.setfirstname(randomData().toUpperCase());
		regpage.setlastname(randomData().toUpperCase());
		regpage.setemail(randomData()+"@yopmail.com");
		
		
		regpage.settelephonenum(randomint());
		
		String Password =randomAlphaNumeric();
		regpage.setpassword(Password);
		regpage.setconfirmPass(Password);
		
		
		regpage.chkbox();
		regpage.agreementt();
		regpage.ContinueBtn();
		
		
		String cnf =regpage.confirmation();
		Assert.assertEquals(cnf, "Your Account Has Been Created!");
		
    	}
    	
    	catch(Exception e) 
    	{ 
    		logger.error("Test failed");
    		
    	}
}
    	
		
	}
	
	

	
	
