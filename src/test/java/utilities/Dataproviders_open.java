package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataproviders_open {
	
	//Dataprovider   1 
	@DataProvider (name ="LD")
	public String [][] getData() throws IOException
	{
		String path =".\\TestData\\OpenCart_DDT.xlsx";  // taking xls from testdata
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getcellCount("Sheet1",1);
		
		String logindata[][]= new String[totalrows][totalcols];
		
		for (int i = 1 ; i<=totalrows; i ++)
		{
			for (int j=0; j<totalcols ; j++)
			{
				logindata[i-1][j]= xlutil.getcellData("Sheet1", i, j);
			}
		}
		
		return logindata;
		
		
	}
	
	

}
