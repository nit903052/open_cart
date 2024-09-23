package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import POM.homepage;
import POM.loginPage;
import POM.myaccountpage;
import testBase_open.BaseClass;

public class TC002_LoginTest extends BaseClass
{
	
	@Test(groups={"sanity","master"})
	public void login_logout()
	{
		//Home
		homepage hm = new homepage(driver);
		hm.clickmyaccount();
		hm.login();
		
		//Login
		loginPage lg= new loginPage(driver);
		lg.set_txt_email(p.getProperty("email"));
		lg.set_txt_pss(p.getProperty("password"));
		lg.Button_login();
		
		//My_Account
		myaccountpage map = new myaccountpage(driver);
		boolean page_exist =map.verification();
		Assert.assertEquals(page_exist, true ," Login Failed");
		//Assert.assertTrue(page_exist);
		map.myprofile();
		map.logout();
		
		
	}
	
	
	
	
	

}
