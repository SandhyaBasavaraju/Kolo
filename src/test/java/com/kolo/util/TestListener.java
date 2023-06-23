package com.kolo.util;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.kolo.base.pages.BaseKoloAutomationPage;
import com.kolo.base.test.BaseKoloAutomationTest;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class TestListener extends TestListenerAdapter {
	private static final Logger logger = Logger.getLogger(TestListener.class.getName());
    private static File logFile;

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {

		Allure.addAttachment("Test Log", "text/plain");
		return message;
	}

	// HTML attachments for Allure
	@Attachment(value = "{0}", type = "text/html")
	public static String attachHtml(String html) {
		return html;
	}

	public void saveScreenshotPNG(String screenshotType, WebDriver driver) {
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		Allure.getLifecycle().addAttachment(
				screenshotType + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy_hh:mm:ss")),
				"image/png", "png", screenshot);
	}

	@Override
	public void onStart(ITestContext iTestContext) {
		logger.info("In onStart method " + iTestContext.getName());
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		logger.info(getTestMethodName(iTestResult) + " test is starting.");
		Object testClass = iTestResult.getInstance();
		try {
			WebDriver driver = ((BaseKoloAutomationTest) testClass).getChildWebDriver();
			  JavascriptExecutor jse = (JavascriptExecutor)driver;				   
		} catch (Exception e) {
			
		}
	 
		
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		logger.info(getTestMethodName(iTestResult) + " test is succeed.");
		Allure.attachment("Logger", BaseKoloAutomationPage.getLogList().toString());
		BaseKoloAutomationPage.loggerMessages.clear();
		Object testClass = iTestResult.getInstance();
		 try {
			 WebDriver driver = ((BaseKoloAutomationTest) testClass).getChildWebDriver();
			 String rs2 =String.valueOf(testClass);
		    	String[] rs1 =rs2.split("@");
		    	String[] rs3 =rs1[0].split("test.");
			 JavascriptExecutor jse = (JavascriptExecutor)driver;
		        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\"" +  rs3[1] + "\" }}");
			   jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"passed\", \"reason\": \"Results found!\"}}");
		} catch (Exception e) {
		}
	   
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		logger.info(getTestMethodName(iTestResult) + " test is failed.");
		// Get driver from BaseClassplusAutomationTest and assign to local webdriver
		Object testClass = iTestResult.getInstance();
		 WebDriver driver = ((BaseKoloAutomationTest) testClass).getChildWebDriver();
		// Allure ScreenShotRobot and SaveTestLog
		if (driver != null) {
			logger.info("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG("Test_Failure_Screenshot_", driver);
			
		}
		Allure.attachment("Logger", BaseKoloAutomationPage.getLogList().toString());
	    try {
	    	String rs2 =String.valueOf(testClass);
	    	String[] rs1 =rs2.split("@");
	    	String[] rs3 =rs1[0].split("test.");
		    JavascriptExecutor jse = (JavascriptExecutor)driver;
	        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\"" +  rs3[1] + "\" }}");
		 jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");

		} catch (Exception e) {
		}


	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		logger.info(getTestMethodName(iTestResult) + " test is skipped.");
		Allure.attachment("Logger", BaseKoloAutomationPage.getLogList().toString());
		Object testClass = iTestResult.getInstance();
		 try {
			 WebDriver driver = ((BaseKoloAutomationTest) testClass).getChildWebDriver();
			 String rs2 =String.valueOf(testClass);
		    	String[] rs1 =rs2.split("@");
		    	String[] rs3 =rs1[0].split("test.");
			 JavascriptExecutor jse = (JavascriptExecutor)driver;
		        jse.executeScript("browserstack_executor: {\"action\": \"setSessionName\", \"arguments\": {\"name\":\"" + rs3[0] + "\" }}");
		      jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\":\"failed\", \"reason\": \"Results not found\"}}");

		} catch (Exception e) {
			
		}
		   

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		logger.info("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
	}

	public void onTestFailedWithTimeout(ITestResult iTestResult) {
		logger.info(getTestMethodName(iTestResult) + " test is failed with Timeout.");
		onTestFailure(iTestResult);
	}

		    @Step("{0}")
			  public static void log(final String message) {
			   String attachmentName = "logger"; 
			   String attachmentContent =message;
			  logger.toString(); Allure.attachment(attachmentName, attachmentContent); 
			  }
			 

		}
	  

