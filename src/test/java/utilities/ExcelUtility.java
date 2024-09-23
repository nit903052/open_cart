package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	static String path;
	
	public ExcelUtility(String path)
	   {
		this.path=path;
		}

	
	public static int getRowCount (String sheetName) throws IOException
	{
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetName);
		int RowCount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return RowCount;
	}
	
	
	public static int getcellCount (String sheetName, int rownum) throws IOException
	{
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetName);
        row= ws.getRow(rownum);
        int   cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	
	public static String getcellData (String sheetName, int rownum , int cellnum) throws IOException
	{
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetName);
        row= ws.getRow(rownum);
        cell = row.getCell(cellnum);
        
        String data;
      try {  
       // data = cell.toString();  // first method
        DataFormatter formatter = new DataFormatter();
        data = formatter.formatCellValue(cell); // Returns formatted value of a cell as a string regardless of the cell type
      }
      catch(Exception e)
        
      {
    	  data="";  // Datanotfoundexception
      }
        
		wb.close();
		fi.close();
		return data;
	}
	

	
	public static void SetCellData (String sheetName, int rownum , int colnum , String data) throws IOException
	{
		//Reading and writing the same sheet
	/*	fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetName);
        row= ws.getRow(rownum);          */
		
		
		File xlfile = new File (path);   
		if(!xlfile.exists())      // if file does not exist then create new file 
		{
			wb= new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}
       
		fi= new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(sheetName)==-1)  // if sheet does not exist create new one
		{
			wb.createSheet(sheetName);
			ws = wb.getSheet(sheetName);
		}
        
        if(ws.getRow(rownum)==null)    // if row not exists create new one
        	ws.createRow(rownum);
        row=ws.getRow(rownum);
        
        
        
        
        //Reading the data
        cell = row.createCell(colnum);
        cell.setCellValue(data);
        
        
        // Creating a new cell
        fo= new FileOutputStream(path);
        wb.write(fo);
        //writing the data
        wb.close();
		fi.close();
		fo.close();
	}
	
	public static void fillgreencolor (String xlfile , String xlsheet, int rownum , int colnum) throws IOException
	{
		//Reading and writing the same sheet
		fi= new FileInputStream(xlfile);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(xlsheet);
        row= ws.getRow(rownum);
        cell= row.getCell(colnum);
        style= wb.createCellStyle();
        
        
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        cell.setCellStyle(style);
        fo= new FileOutputStream(xlfile);
        
        wb.write(fo);
        wb.close();
		fi.close();
		fo.close();
	}
	
	
	public static void fillredcolor (String xlfile , String xlsheet, int rownum , int colnum) throws IOException
	{
		//Reading and writing the same sheet
		fi= new FileInputStream(xlfile);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(xlsheet);
        row= ws.getRow(rownum);
        cell= row.getCell(colnum);
        style= wb.createCellStyle();
        
        
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        cell.setCellStyle(style);
        fo= new FileOutputStream(xlfile);
        
        wb.write(fo);
        wb.close();
		fi.close();
		fo.close();
	}
	
	

}
