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

public class PDPForPostType2_AffiliateProductTest extends BaseKoloAutomationTest{
	public static final Logger logger = Logger.getLogger(PDPForPostType2_AffiliateProductTest.class.getName());
	public WebDriver driver;
	private ProductDetailPage productDetailPage = null;
	public static Properties PDPForPostType2_AffiliateProductTest=filesetup(PDPForPostType2_AffiliateProductTest.class);
	public static final String PRODUCT_DETTAILOF_WHATSAPPFLOW=getUrlProperty("Product.detail.affliateflow");
	public static final String PRODUCT_TITLE=PDPForPostType2_AffiliateProductTest.getProperty("product.Title.Text");
	public static final String PRODUCT_OFFERED_PRICE=PDPForPostType2_AffiliateProductTest.getProperty("product.offered.price");
	public static final String PRODUCT_MRP_PRICE=PDPForPostType2_AffiliateProductTest.getProperty("product.MRP.price");
	public static final String PRODUCT_DISCOUNT_PRICE=PDPForPostType2_AffiliateProductTest.getProperty("product.discount.price");
	public static final String BUYNOWTYPE_BUYONAMAZON=PDPForPostType2_AffiliateProductTest.getProperty("buynowtype.buyonamazon.text");



	@BeforeClass
	@Parameters({"deviceIndex"})
	public void initialMethods(String deviceIndex) throws Exception {
		logger.info("Starting of initialMethods ");
		driver = this.getWebDriver(deviceIndex, WEB_DRIVER.PDPFORPOSTTYPE2_AFFILIATEPRODUCTTEST);
		driver.get(PRODUCT_DETTAILOF_WHATSAPPFLOW);
		this.productDetailPage = new ProductDetailPage(driver);

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
		//productDetailPage.getProductPriceText(PRODUCT_OFFERED_PRICE, PRODUCT_MRP_PRICE, PRODUCT_DISCOUNT_PRICE);
		Assert.assertEquals(productDetailPage.getBuyNowTypeBuyOnAmazonText(),BUYNOWTYPE_BUYONAMAZON);


		logger.info("Ending of VerifyingTheProductDetailsOnTheWhatsappFlow method");

	}
	
	@Parameters({ "deviceIndex" })
	@Test(priority = 1)
	@Description("Test Case #1,Verifying the product details on the whatsapp flow")
	@Severity(SeverityLevel.BLOCKER)
	@Story("Verifying the product details on the whatsapp flow")
	public void VerifyingTheProductDetailsOnTheWhatsappFlowOnWeb() {

		logger.info("Starting of VerifyingTheProductDetailsOnTheWhatsappFlow method");
       
		this.setResolution(RESOLUTION_VIEW.WEB_VIEW);
		Assert.assertEquals(productDetailPage.getProductTitleText(),PRODUCT_TITLE);
		//productDetailPage.getProductPriceText(PRODUCT_OFFERED_PRICE, PRODUCT_MRP_PRICE, PRODUCT_DISCOUNT_PRICE);
		Assert.assertEquals(productDetailPage.getBuyNowTypeBuyOnAmazonText(),BUYNOWTYPE_BUYONAMAZON);


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
