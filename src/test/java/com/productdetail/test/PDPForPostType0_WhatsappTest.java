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
import com.kolo.base.test.BaseKoloAutomationTest.RESOLUTION_VIEW;
import com.productdetail.page.ProductDetailPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class PDPForPostType0_WhatsappTest extends BaseKoloAutomationTest {
	
	public static final Logger logger = Logger.getLogger(PDPForPostType0_WhatsappTest.class.getName());
	public WebDriver driver;
	private ProductDetailPage productDetailPage = null;
	public static Properties ProductDetailOfWhatsAppFlowTest=filesetup(PDPForPostType0_WhatsappTest.class);
	public static final String PRODUCT_DETTAILOF_WHATSAPPFLOW=getUrlProperty("product.detail.whatsappflow");
	public static final String PRODUCT_TITLE=ProductDetailOfWhatsAppFlowTest.getProperty("product.Title.Text");
	public static final String PRODUCT_OFFERED_PRICE=ProductDetailOfWhatsAppFlowTest.getProperty("product.offered.price");
	public static final String PRODUCT_MRP_PRICE=ProductDetailOfWhatsAppFlowTest.getProperty("product.MRP.price");
	public static final String PRODUCT_DISCOUNT_PRICE=ProductDetailOfWhatsAppFlowTest.getProperty("product.discount.price");
	public static final String BUYNOWTYPE_WHTSAPP=ProductDetailOfWhatsAppFlowTest.getProperty("buynowtype.whtsapp.text");



	@BeforeClass
	@Parameters({"deviceIndex"})
	public void initialMethods(String deviceIndex) throws Exception {
		logger.info("Starting of initialMethods ");
		driver = this.getWebDriver(deviceIndex, WEB_DRIVER.PDPFORPOSTTYPE0_WHATSAPPTEST);
		driver.get(PRODUCT_DETTAILOF_WHATSAPPFLOW);
		this.productDetailPage = new ProductDetailPage(driver);
		logger.info("================= Screen Resolution"+ driver.manage().window().getSize());

		logger.info("Ending of initialMethods");

	}
	
	@Parameters({ "deviceIndex" })
	@Test(priority = 1)
	@Description("Test Case #1,Verifying the product details on the whatsapp flow")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verifying the product details on the whatsapp flow")
	public void VerifyingTheProductDetailsOnTheWhatsappFlowOnMobile() {

		logger.info("Starting of VerifyingTheProductDetailsOnTheWhatsappFlow method");

        this.setResolution(RESOLUTION_VIEW.MOBILE_VIEW);
		Assert.assertEquals(productDetailPage.getProductTitleText(),PRODUCT_TITLE);
		productDetailPage.getProductPriceText(PRODUCT_OFFERED_PRICE, PRODUCT_MRP_PRICE, PRODUCT_DISCOUNT_PRICE);
		Assert.assertEquals(productDetailPage.getBuyNowTypeWhatsappText(),BUYNOWTYPE_WHTSAPP);


		logger.info("Ending of VerifyingTheProductDetailsOnTheWhatsappFlow method");

	}
	
	@Parameters({ "deviceIndex" })
	@Test(priority = 2)
	@Description("Test Case #1,Verifying the product details on the whatsapp flow")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verifying the product details on the whatsapp flow")
	public void VerifyingTheProductDetailsOnTheWhatsappFlowOnWeb() {

		logger.info("Starting of VerifyingTheProductDetailsOnTheWhatsappFlow method");

		this.setResolution(RESOLUTION_VIEW.WEB_VIEW);
		Assert.assertEquals(productDetailPage.getProductTitleText(),PRODUCT_TITLE);
		productDetailPage.getProductPriceText(PRODUCT_OFFERED_PRICE, PRODUCT_MRP_PRICE, PRODUCT_DISCOUNT_PRICE);
		Assert.assertEquals(productDetailPage.getBuyNowTypeWhatsappText(),BUYNOWTYPE_WHTSAPP);


		logger.info("Ending of VerifyingTheProductDetailsOnTheWhatsappFlow method");

	}
	
	 @AfterClass
	  @Parameters({ "deviceIndex" })
	  public void quitDriver() {
	  logger.info("Starting of quitDriver method"); 
	  driver.quit();
	  logger.info("Starting of quitDriver method");

	  
	  }
	

}
