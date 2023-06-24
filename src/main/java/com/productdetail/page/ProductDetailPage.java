package com.productdetail.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.kolo.base.pages.BaseKoloAutomationPage;

public class ProductDetailPage extends BaseKoloAutomationPage {

	@FindBy(xpath = "//h1[@data-testid='product-title']")
	private WebElement lblProductTitle;

	@FindBy(xpath = "//span[@data-testid='offered-price']")
	private WebElement lblOfferedPrice;

	@FindBy(xpath = "//span[@data-testid='MRP']")
	private WebElement lblMRPPrice;

	@FindBy(xpath = "//span[@data-testid='discount']")
	private WebElement lblDiscountPrice;

	@FindBy(xpath = "//button[@data-testid='buy-now-type-0']")
	private WebElement lblBuyNowTypeWhatsapp;

	@FindBy(xpath = "//button[@data-testid='buy-now-type-2']")
	private WebElement lblBuyNowTypeBuyOnAmazon;

	@FindBy(xpath = "//button[@data-testid='buy-now-type-1']")
	private WebElement lblBuyNow;

	private static final Logger logger = Logger.getLogger(ProductDetailPage.class.getName());

	public ProductDetailPage(WebDriver driver) {
		super(driver);

		logger.info("Starting of PurchasingScreenPage method");

		PageFactory.initElements(driver, this);

		logger.info("Ending of AddCategoriesPage method");

	}

	public String getProductTitleText() {
		logger.info("Starting of getProductTitleText method");
		logger.info("Ending of getProductTitleText method");

		System.out.println(lblProductTitle.getText());

		return lblProductTitle.getText();
	}

	public String getPriceText() {
		logger.info("Starting of getPriceText method");
		logger.info("Ending of getPriceText method");

		System.out.println(lblOfferedPrice.getText());

		return lblOfferedPrice.getText();
	}


	public String getBuyNowTypeWhatsappText() {
		logger.info("Starting of getPriceText method");
		logger.info("Ending of getPriceText method");
		this.scrolltoView(lblBuyNowTypeWhatsapp);
		System.out.println(lblBuyNowTypeWhatsapp.getText());

		return lblBuyNowTypeWhatsapp.getText();
	}

	public String getBuyNowTypeBuyOnAmazonText() {
		logger.info("Starting of getPriceText method");
		logger.info("Ending of getPriceText method");
		this.scrolltoView(lblBuyNowTypeBuyOnAmazon);
		System.out.println(lblBuyNowTypeBuyOnAmazon.getText());

		return lblBuyNowTypeBuyOnAmazon.getText();
	}
	
	public String getBuyNowText() {
		logger.info("Starting of getPriceText method");
		logger.info("Ending of getPriceText method");
		this.scrolltoView(lblBuyNow);
		System.out.println(lblBuyNow.getText());

		return lblBuyNow.getText();
	}
}
