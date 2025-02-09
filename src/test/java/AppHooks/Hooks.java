package AppHooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Driver.Driver_Factory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.ConfigReader;

public class Hooks {
	
	private Driver_Factory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	
	@Before(order = 0)
	public void getProperty(){
	configReader = new ConfigReader();
	prop = configReader.init_prop();
	
	}
	@Before(order =1)
	public void launchBrowser() {
	String browserName = prop.getProperty("browser");
	driverFactory = new Driver_Factory();
	driver = driverFactory.init_driver(browserName);
	}
	@After(order = 0)
	public void quitBrowser(){
	driver.quit();
	}
	@After(order = 1)
	public void teardown(Scenario scenario){
	 if(scenario.isFailed()){
	  //take screenshot:
	   String screenshotName = scenario.getName().replaceAll(" ", "_");
	byte[] sourcePath = ((TakesScreenshot)driver). getScreenshotAs(OutputType.BYTES);
	scenario.attach(sourcePath, "image/png", screenshotName);
	}
	}
}
	
	

