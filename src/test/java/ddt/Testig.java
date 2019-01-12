package ddt;
import static org.testng.Assert.assertTrue;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Testig {


	

		WebDriver driver;
		@Test(dataProvider = "NTLoginData")
	  public void loginTest(String userName, String password) {
			  driver.get("http://selenium-examples.nichethyself.com");
			  driver.findElement(By.name("username")).sendKeys(userName);
			  driver.findElement(By.name("password")).sendKeys(password);
			  try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  driver.findElement(By.name("loginform")).findElement(By.tagName("button")).click();
			  boolean isSignOutThere = false;
			  try {
			  	WebElement signOutLink = driver.findElement(By.xpath("//a[@href='home.html']"));
			  	isSignOutThere = true;
			  } catch(NoSuchElementException e){
				  System.out.println("SignOut not found.");
			  }
			  assertTrue(isSignOutThere);
	  }
	  @BeforeMethod
	  public void beforeMethod() {
			System.setProperty("webdriver.gecko.driver", "test\\resources\\geckodriver64bit.exe");
			System.setProperty("webdriver.chrome.driver", "test\\resources\\chromedriver.exe");

		//	driver = new FirefoxDriver();
			driver = new ChromeDriver();

	  }

	  @AfterMethod
	  public void afterMethod() {
		  driver.quit();
	  }


	  @DataProvider (name="NTLoginData")
	  public Object[][] dataProviderMethod() {
	      Object[][] retObjArr=getDataFromXLSUsingJXL("test\\resources\\data\\login.xls",
	              "LoginData", "StartAndEnd");
	        return(retObjArr);	  
	  }
	  
	  @DataProvider (name="NTLoginDataFromCSV")
	  public Object[][] dataProviderMethodCSV() {
	      Object[][] retObjArr=getDataFromXLSUsingJXL("test\\resources\\data\\login.xls",
	              "LoginData", "StartAndEnd");
	        return(retObjArr);	  
	  }
	  
	 
	  
	  public String[][] getDataFromXLSUsingJXL(String xlFilePath, String sheetName, String tableName){
	      String[][] tabArray=null;
	      try{
	          Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
	          //Workbook class is provied by jxl.jar
	          //WebDriver provided by Selenium 
	          //File is class provided by Java to read a physical file
	          Sheet sheet = workbook.getSheet(sheetName);
	          Cell tableStart=sheet.findCell(tableName);
	          
	          int startRow,startCol, endRow, endCol,ci,cj;
	          
	          startRow=tableStart.getRow();//2
	          startCol=tableStart.getColumn();//1
	            Cell tableEnd= sheet.findCell(tableName);                

	        
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
	  
		

	}


