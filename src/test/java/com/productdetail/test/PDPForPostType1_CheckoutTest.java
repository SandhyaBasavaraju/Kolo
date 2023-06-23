package com.productdetail.test;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kolo.base.test.BaseKoloAutomationTest;
import com.productdetail.page.ProductDetailPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class PDPForPostType1_CheckoutTest extends BaseKoloAutomationTest {
	public static final Logger logger = Logger.getLogger(PDPForPostType1_CheckoutTest.class.getName());
	public WebDriver driver;
	private ProductDetailPage productDetailPage = null;
	public static Properties PurchasingScreenTest=filesetup(PDPForPostType1_CheckoutTest.class);
	public static final String PURCHASING_SITEURL=getUrlProperty("purchasing.product.site.url");
	public static final String PRODUCT_TITLE=PurchasingScreenTest.getProperty("product.Title.Text");
	public static final String PRODUCT_PRICE=PurchasingScreenTest.getProperty("price.Text");
	public static final String BUYNOW=PurchasingScreenTest.getProperty("buynow.text");


	@BeforeClass
	@Parameters({"deviceIndex"})
	public void initialMethods(String deviceIndex) throws Exception {
		logger.info("Starting of initialMethods ");
		driver = this.getWebDriver(deviceIndex, WEB_DRIVER.PDPFORPOSTTYPE1_CHECKOUTTEST);
		driver.get(PURCHASING_SITEURL);
		this.productDetailPage = new ProductDetailPage(driver);
		logger.info("================= Screen Resolution"+ driver.manage().window().getSize());

		logger.info("Ending of initialMethods");

	}

	@Parameters({ "deviceIndex" })
	@Test(priority = 1)
	@Description("Test Case #1,Verifying the product details on the purchase screen")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verifying the product details on the purchase screen")
	public void VerifyingTheProductDetailsOnThePurchaseScreen() {

		logger.info("Starting of VerifyingTheProductDetailsOnThePurchaseScreen method");

		this.setResolution(RESOLUTION_VIEW.MOBILE_VIEW);
		logger.info("================= Screen Resolution"+ driver.manage().window().getSize());
		Assert.assertEquals(productDetailPage.getProductTitleText(),PRODUCT_TITLE);
		Assert.assertEquals(productDetailPage.getPriceText(),PRODUCT_PRICE);
		Assert.assertEquals(productDetailPage.getBuyNowText(),BUYNOW);
		
		logger.info("Ending of VerifyingTheProductDetailsOnThePurchaseScreen method");

	}
	
	@Parameters({ "deviceIndex" })
	@Test(priority = 2)
	@Description("Test Case #1,Verifying the product details on the purchase screen")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verifying the product details on the purchase screen")
	public void VerifyingTheProductDetailsOnThePurchaseScreenOnWeb() {

		logger.info("Starting of VerifyingTheProductDetailsOnThePurchaseScreen method");
       
		this.setResolution(RESOLUTION_VIEW.WEB_VIEW);
		logger.info("================= Screen Resolution"+ driver.manage().window().getSize());
		Assert.assertEquals(productDetailPage.getProductTitleText(),PRODUCT_TITLE);
		Assert.assertEquals(productDetailPage.getPriceText(),PRODUCT_PRICE);
		Assert.assertEquals(productDetailPage.getBuyNowText(),BUYNOW);
	
		logger.info("Ending of VerifyingTheProductDetailsOnThePurchaseScreen method");

	}
	
	  @AfterClass
	  @Parameters({ "deviceIndex" }) 
	  public void quitDriver(){
	  logger.info("Starting of quitDriver method"); 
	  driver.quit();
	  logger.info("Starting of quitDriver method");
	  
	  
	  }
	 	 
	
	

}
