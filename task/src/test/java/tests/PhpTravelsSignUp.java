package tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Listeners;

import org.testng.annotations.Test;
import reader.ExcelReader;
import validation.ValidateField;
import atos.task.*;


@Listeners(ListenerTest.ListenerTest.class)

public class PhpTravelsSignUp {
	private static String SHEETNAME = "Sheet1";

	boolean result;
	
	private static WebDriver driver;
	private Testdata testdata=new Testdata("TestData",SHEETNAME);
	private ObjectRepository objects=new ObjectRepository("ObjectRepository",SHEETNAME);
	private static String screenshotFolderPath="D:\\ATOS_Preperation\\task\\Screenshots";
	WebElement firstName, lastName, password, confirmPassword,email,mobileNumber,signUp;
	


	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser) throws Exception {
		// Check if parameter passed from TestNG is 'firefox'
		if (browser.equalsIgnoreCase("firefox")) {
			// create firefox instance
			System.setProperty("webdriver.gecko.driver", "drivers//geckodriver.exe");
			driver = new FirefoxDriver();
		}
		// Check if parameter passed as 'chrome'
		else if (browser.equalsIgnoreCase("chrome")) {
			// set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver", "drivers//chromedriver.exe");
			// create chrome instance
			driver = new ChromeDriver();
		}
		// Check if parameter passed as 'Edge'
		else if (browser.equalsIgnoreCase("ie")) {
			// set path to Edge.exe
			System.setProperty("webdriver.ie.driver", "drivers//IEDriverServer.exe");
			// create Edge instance
			driver = new InternetExplorerDriver();
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
	}


	@BeforeMethod

	public void navigation() {	
    String url=testdata.getTestData().get("Url");
	driver.navigate().to(url);
	driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
	}
    @Test
	public void fullScenario() {
    	changeLanguage();
		firstName= findBy(objects.getObjects().get("First Name").get(0),objects.getObjects().get("First Name").get(1));
		firstName.sendKeys(testdata.getTestData().get("FirstName"));
		lastName= findBy(objects.getObjects().get("Last Name").get(0),objects.getObjects().get("Last Name").get(1));
		lastName.sendKeys(testdata.getTestData().get("LastName"));
		assertStartWithUppercase();
		mobileNumber= findBy(objects.getObjects().get("Mobile Number").get(0),objects.getObjects().get("Mobile Number").get(1));
		mobileNumber.sendKeys(testdata.getTestData().get("MobileNumber"));
		email= findBy(objects.getObjects().get("Email").get(0),objects.getObjects().get("Email").get(1));
		email.sendKeys(testdata.getTestData().get("Email"));
		password= findBy(objects.getObjects().get("Password").get(0),objects.getObjects().get("Password").get(1));
		password.sendKeys(testdata.getTestData().get("Password"));
		assertPassword();
		confirmPassword= findBy(objects.getObjects().get("Confirm Password").get(0),objects.getObjects().get("Confirm Password").get(1));
		confirmPassword.sendKeys(testdata.getTestData().get("Password"));
	    signUp();
    }
	@AfterMethod
	@Parameters("changedURL")
	public void assertUrl( String changedURL) {
	result = ValidateField.validateUrl(driver, changedURL);
	AssertJUnit.assertTrue(result);
	}
	public void changeLanguage() {
	WebElement languageDropdown,enLanguage;
	languageDropdown=findBy(objects.getObjects().get("DropdownLanguage").get(0),objects.getObjects().get("DropdownLanguage").get(1));	
	languageDropdown.click();
	enLanguage=findBy(objects.getObjects().get("english option").get(0),objects.getObjects().get("english option").get(1));
	enLanguage.click();
	}
	public void assertStartWithUppercase() {
		result = ValidateField.validateFirstName(firstName.getText());
		AssertJUnit.assertTrue(result);
		lastName = findBy(objects.getObjects().get("Last Name").get(0),objects.getObjects().get("Last Name").get(1));
		result = ValidateField.validateFirstName(lastName.getText());
		AssertJUnit.assertTrue(result);

	}
	public void assertPassword() {
		result = ValidateField.validateFirstName(password.getText());
		AssertJUnit.assertTrue(result);
	}
	public void signUp() {
		signUp =findBy(objects.getObjects().get("Sign Up").get(0),objects.getObjects().get("Sign Up").get(1));
		signUp.click();
	}
	@AfterClass
	public void close() {
		driver.close();

	}
	
public static void takeScreenshot() throws IOException {
	 File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
     File ScreenshotsFolder=new File(screenshotFolderPath);
     FileUtils.copyFile(file, ScreenshotsFolder);
}
 private WebElement findBy(String identifier, String value) {
	 if(identifier.equals("id")) 
	  return driver.findElement(By.id(value));
	 else if(identifier.equals("name"))
	 return driver.findElement(By.name(value));
	 else if(identifier.equals("xpath"))
		 return driver.findElement(By.xpath(value));
	 return null; 
	 }
		
}
