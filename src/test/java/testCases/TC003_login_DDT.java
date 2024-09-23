package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import POM.homepage;
import POM.loginPage;
import POM.myaccountpage;
import testBase_open.BaseClass;
import utilities.Dataproviders_open;

public class TC003_login_DDT extends  BaseClass
{
	@Test(dataProvider="LD" , dataProviderClass = Dataproviders_open.class, groups="dataDriven") 
	// second parameter is needed when data provider belongs to other class
	public void login_DDT(String email , String pwd , String exp)
	{
		
		//Home
	//	homepage hm = new homepage(driver);
		//hm.clickmyaccount();
		//hm.login();
		
		//Login
		loginPage lg= new loginPage(driver);
		lg.set_txt_email(email.toString());
		lg.set_txt_pss(pwd.toString());
		lg.Button_login();
		
		//My_Account
		myaccountpage map = new myaccountpage(driver);
		boolean page_exist =map.verification();
		//Assert.assertEquals(page_exist, true ," Login Failed");
		//Assert.assertTrue(page_exist);
		//map.myprofile();
	    //map.logout();
		
		
		
		/*
		 Data is valid ==> login success --> Test pass
		               ==> login unsuccess --> Test fail 
		               
		 Data is Invalid ==> login success --> Test fail
		               ==> login unsuccess --> Test pass 
		 
		 Below assertion accordingly
		  */
		
		
		
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(page_exist== true)
			{
				map.myprofile();
				map.logout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(page_exist== true)
			{
				map.myprofile();
				map.logout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
	  
		 
	

}}
