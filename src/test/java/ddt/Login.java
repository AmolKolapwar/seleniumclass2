package ddt;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Login extends LoadableComponent<Login> {
	
	WebDriver driver;
	String url = "https://www.google.com/intl/en-GB/gmail/about/#";
	String pagetitle = "Gmail - Free Storage and Email from Google";
	
	
	@FindBy (xpath ="//a[@class='gmail-nav__nav-link gmail-nav__nav-link__sign-in']")
	@CacheLookup
	private WebElement Sign_Button;
	
	@FindBy (xpath="//input[@type='email']")
	@CacheLookup
	private WebElement  email_id;
	
	@FindBy (xpath="//span[@class='RveJvd snByac'][contains(text(),'Next')]")
	@CacheLookup
	private WebElement  Next_Button;
	
	

	@FindBy (xpath="//input[@type='password']")
	@CacheLookup
	private WebElement   Password;
	
	public Login(){
		   System.setProperty("webdriver.chrome.driver","F:/Selenium_Class/selenium/resource/chromedriver.exe");
		    driver = new ChromeDriver();   
		PageFactory.initElements(driver, this);
	}
	

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		
		Assert.assertEquals(driver.getTitle(), pagetitle, "Page Not Loaded");
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
		driver.get(url);
		
	}
	
	
	public String getTitle(){
		
		System.out.println(driver.getTitle());
		return driver.getTitle();
	}
	
	
	public String getlogintitle(){
		
		return driver.getCurrentUrl();
	}
	
	public void  logintest(String  username,String passwd){
		
		Sign_Button.click();
		WebDriverWait wait = new WebDriverWait (driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']")));
        email_id.clear();
        email_id.sendKeys(username);
        Next_Button.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='password']")));

        Password.sendKeys(passwd);
        
		
	}
	

}
