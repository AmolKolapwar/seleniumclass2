package ddt.poi;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Login_test  {
	WebDriver driver;
	Login logintest;
	String expected ="Gmail";

  @BeforeMethod
  public void beforeMethod() {
	  
	   logintest = new Login();
	   logintest.get();
	   
  }
  
  
  

  @DataProvider (name="NTLoginData")
  public Object[][] dataProviderMethod() {
      Object[][] retObjArr=getDataFromXLSUsingJXL("C:\\Users\\abc\\Desktop\\New Microsoft Office Excel 97-2003 Worksheet.xls",
              "LoginData", "StartAndEnd");
        return(retObjArr);	  
  }
	

  public String[][] getDataFromXLSUsingJXL(String xlFilePath, String sheetName, String tableName){
      String[][] tabArray=null;
      try{
    	  FileInputStream fis = new FileInputStream("");
    	  XSSFWorkbook SS = new  XSSFWorkbook(fis);

    	 // XSSFWorkbook workbook = XSSFWorkbook.g(new File(xlFilePath));
          //Workbook class is provied by jxl.jar
          //WebDriver provided by Selenium 
          //File is class provided by Java to read a physical file
    	  XSSFSheet  sheet = workbook.getS(sheetName);
          Cell tableStart=sheet.findCell(tableName);
          
          int startRow,startCol, endRow, endCol,ci,cj;
          
          startRow=tableStart.getRow();//2
          startCol=tableStart.getColumn();//1

          Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);
          endRow=tableEnd.getRow();//6
          endCol=tableEnd.getColumn();//4
          System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                  "startCol="+startCol+", endCol="+endCol);
          tabArray=new String[endRow-startRow-1][endCol-startCol-1];//5,4
          ci=0; //array row
          //ci=0,i=3, j=3,cj=1
          for (int i=startRow+1;i<endRow;i++,ci++){//i represents xls row
              cj=0;//array column
              for (int j=startCol+1;j<endCol;j++,cj++){//j represents xls column
                  tabArray[ci][cj]=sheet.getCell(j,i).getContents();
              }
          }
      }
      catch (Exception e)    {
          System.out.println("Please check if file path, sheet name and tag name are correct");
          
      }

      return(tabArray);
  }
  
	




@Test(dataProvider="")
  public void f() {
	  
	  
	  logintest.logintest("amol.kolapwar92", ""); 
	 // Assert.assertEquals(logintest.getTitle(), expected);
	  
	  Assert.assertTrue(logintest.getlogintitle().contains("https://mail.google.com"),"Contains not match");
  }

  @AfterMethod
  public void afterMethod() {
	  
	//driver.quit();
	  
  }

}
