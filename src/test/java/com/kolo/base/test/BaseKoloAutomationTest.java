package com.kolo.base.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.io.FileReader;

import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import com.b2btesters.runtime.DriverCapabilities;
import com.b2btesters.runtime.DriverCapabilitiesFactory;
import com.b2btesters.runtime.EnvironmentConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseKoloAutomationTest {

	protected static final String BASE_DIR = System.getProperty("user.dir");
	protected static final String FILE_SEPARATOR = System.getProperty("file.separator");
	private static final Logger logger = Logger.getLogger(BaseKoloAutomationTest.class.getName());
	protected static String browserDriverPath;
	protected static Map<String, String> chromeDriverMap = new HashMap<String, String>();
	protected static Properties login;
	private static Map<WEB_DRIVER, WebDriver> webDriverPool = new Hashtable<WEB_DRIVER, WebDriver>();
	protected WebDriver webDriver;
	public static Properties properties;
	public static Properties urlProp = null;
	protected static final String URL_PROPERTY = "src/main/resources/url.properties";
	protected static Properties prop = new Properties();
	protected static FileInputStream fis;

	public enum WEB_DRIVER {
		PDPFORPOSTTYPE1_CHECKOUTTEST, PDPFORPOSTTYPE0_WHATSAPPTEST, PDPFORPOSTTYPE2_AFFILIATEPRODUCTTEST

	}

	public static Properties read_Properties(String filePath) {
		try {
			fis = new FileInputStream(filePath);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			logger.info("File path not found{} ", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("IO Exception{} ", e);
			e.printStackTrace();
		}
		return prop;
	}

	public static String getUrlProperty(String key) {
		read_Properties(URL_PROPERTY);
		return (String) prop.get(key);
	}

	protected synchronized void quitDriver(WEB_DRIVER webDriver) {
		logger.info("Starting of method quitDriver in BaseAutomationTest ");
		WebDriver driver = webDriverPool.get(webDriver);
		try {
			if (driver != null) {
				driver.quit();
				driver = null;
				webDriverPool.remove(webDriver);
				logger.debug(webDriver + " Web driver quit successfully in BaseAutomationTest ");
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			driver = null;
		}
		logger.info("Ending of method quitDriver in BaseAutomationTest");
	}

	protected synchronized WebDriver getWebDriver(String deviceIndex, WEB_DRIVER webDriver) throws Exception {
		logger.info("Starting of method getWebDriver");

		EnvironmentConfigReader environmentConfigReader = new EnvironmentConfigReader();
		DriverCapabilitiesFactory capFactory = new DriverCapabilitiesFactory();
		// configFile through getServer to be executed
		DriverCapabilities driverCapabilities = capFactory.getCapabilities(environmentConfigReader.getServer(),
				deviceIndex);
		DesiredCapabilities cap = null;
		cap = driverCapabilities.capabilities();
		String osPath = String.valueOf(cap.getCapability("osPath"));
		logger.info("OS_PATH---------" + osPath);
		String browser = cap.getBrowserName();
		logger.info("BROWSER---------" + browser);
		WebDriver driver = webDriverPool.get(webDriver);

		if (environmentConfigReader.getServer().equalsIgnoreCase("local")) {
			// Use existing driver
			if (driver != null) {
				logger.debug("Using existing web driver " + webDriver);
				this.webDriver = driver;
				return driver;
			}
			if (osPath.contains("Linux")) {
				if (browser.equalsIgnoreCase("Firefox")) {
					WebDriverManager.firefoxdriver().setup();
					FirefoxOptions options = new FirefoxOptions();
					options.setHeadless(true);
					options.addArguments("--no-sandbox");
					driver = new FirefoxDriver(options);
				} else if (browser.equalsIgnoreCase("Chrome")) {
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("enable-automation");
					// options.addArguments("--headless");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-extensions");
					options.addArguments("--dns-prefetch-disable");
					options.addArguments("--disable-gpu");
					options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
					// options.setHeadless(true);
					options.addArguments("--headless"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
					// options.addArguments("--window-size=300,600");
					options.setPageLoadStrategy(PageLoadStrategy.EAGER);// del
					options.addArguments("--disable-browser-side-navigation"); // del
					// options.addArguments("--disable-dev-shm-usage"); // del
					options.addArguments("--disable-gpu");
					options.addArguments("--no-sandbox");
					// options.setBinary("/opt/google/chrome/google-chrome");
					Map<String, Object> prefs = new HashMap<String, Object>();
					prefs.put("profile.default_content_settings.popups", 0);
					options.setExperimentalOption("prefs", prefs);
					DesiredCapabilities capabilities = new DesiredCapabilities();
					capabilities.setBrowserName("CHROME");
					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
					// capabilities.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
					capabilities.setCapability("applicationCacheEnabled", "true");
					driver = new ChromeDriver(options);
				}
			} else if (osPath.contains("Mac")) {
				// browserDriverPath = "/usr/bin/safaridriver";
				if (browser.contains("safaridriver")) {
					System.setProperty("webdriver.safari.driver", browserDriverPath);
					driver = new SafariDriver();
					logger.debug("Safari driver path " + browserDriverPath);
				} else if (browser.equalsIgnoreCase("Chrome")) {
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("enable-automation");
					// options.addArguments("--headless");
					// options.addArguments("--window-size=300,600");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-extensions");
					options.addArguments("--dns-prefetch-disable");
					options.addArguments("--disable-gpu");
					// options.setHeadless(true);
					options.setPageLoadStrategy(PageLoadStrategy.EAGER);// del
					options.addArguments("--disable-browser-side-navigation"); // del
					options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
					driver = new ChromeDriver(options);
				}
			} else if (osPath.contains("Windows")) {
				if (browser.equalsIgnoreCase("Chrome")) {
					WebDriverManager.chromedriver().setup();
					ChromeOptions options = new ChromeOptions();
					options.addArguments("enable-automation");
					// options.addArguments("--headless");
					// options.addArguments("--window-size=300,600");
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-extensions");
					options.addArguments("--dns-prefetch-disable");
					options.addArguments("--disable-gpu");
					// options.setHeadless(true);
					options.setPageLoadStrategy(PageLoadStrategy.EAGER);// del
					options.addArguments("--disable-browser-side-navigation"); // del
					options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
					driver = new ChromeDriver(options);
				} else if (browser.equalsIgnoreCase("Firefox")) {
					WebDriverManager.firefoxdriver().setup();
					driver = new FirefoxDriver();
				} else if (browser.equalsIgnoreCase("Chromium")) {
					WebDriverManager.chromiumdriver().setup();
					driver = new EdgeDriver();
				} else if (browser.equalsIgnoreCase("IEDriverServer")) {
					WebDriverManager.iedriver().setup();
					driver = new InternetExplorerDriver();
				}
			}
		} else if (environmentConfigReader.getServer().equalsIgnoreCase("browserstack")) {
			driver = new RemoteWebDriver(new URL(environmentConfigReader.getServerURL()), cap);

		}

		// Set the browser window size to the specified resolution
		// driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		logger.info("***************** Driver Successfully Created **************** " + driver.getTitle());
		logger.info("End of method getWebDriver");
		this.webDriver = driver;
		webDriverPool.put(webDriver, driver);
		return driver;
	}

	public WebDriver getChildWebDriver() {
		return this.webDriver;
	}

	public static Properties filesetup(Class<?> testClass) {
		Properties properties = new Properties();
		String packageName = testClass.getPackageName().replace(".", "/");
		String className = testClass.getSimpleName();
		logger.info("FILEPATH------>>>src/test/java/" + packageName + "/" + className + ".properties");
		String filePath = "src/test/java/" + packageName + "/" + className + ".properties";
		try {
			properties.load(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}

	public enum RESOLUTION_VIEW {
		MOBILE_VIEW, WEB_VIEW
	}

	public void setResolution(RESOLUTION_VIEW views){ 
		switch(views){
		case MOBILE_VIEW :
	  Dimension resolution = new Dimension(375, 667);
	  webDriver.manage().window().setSize(resolution); 
	  break; 
	  case WEB_VIEW: 
		  Dimension resolutions = new Dimension(1920, 1080);
		  webDriver.manage().window().setSize(resolutions); 
	  break;
		}
	  }
	
	 protected Actions scrolltoView(WebElement element) {
		  try { 
			  Actions actions = new Actions(webDriver);
	  
	  actions.moveToElement(element).perform(); 
	  return actions;
	  } catch ( MoveTargetOutOfBoundsException e) {
	        return null;
		   }
	  }
	  
	 
}
