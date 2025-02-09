package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver_Factory {
	
	public WebDriver driver;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
	public WebDriver init_driver(String browser){
	System.out.println("browser value is: " + browser);
	if(browser.equals("chrome")){
		
	WebDriverManager.chromedriver().setup();
	tlDriver.set(new ChromeDriver());
	
	}
	 else if(browser.equals("firefox")){
	WebDriverManager.firefoxdriver().setup();
	tlDriver.set(new FirefoxDriver());
	}
	if(browser.equals("safari")){
	//WebDriverManager.safaridriver().setup();
	tlDriver.set(new SafariDriver());
	}
	
	else{
	    System.out.println("Please pass the correct browser value: " + browser);
	}
	getDriver().manage().deleteAllCookies();
	getDriver().manage().window().maximize();
	getDriver().get("https://dsportalapp.herokuapp.com/");
	return getDriver();
	}
	/**
	* this is used to get the driver with ThreadLocal
	*@return
	*/
	public static synchronized WebDriver getDriver(){
	return tlDriver.get();
	}
	

}
