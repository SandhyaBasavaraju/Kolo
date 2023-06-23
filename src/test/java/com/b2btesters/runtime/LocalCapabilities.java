package com.b2btesters.runtime;


import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeSuite;

public class LocalCapabilities implements DriverCapabilities {
	
	private String deviceIndex = null;
	private String configFilePath = null;
	private CapabilityBuider capabilities = null;

	public LocalCapabilities(String configFilePath, String deviceIndex) {
		
		this.configFilePath = configFilePath;
		this.deviceIndex = deviceIndex;
	}
	
	@BeforeSuite
	public DesiredCapabilities capabilities() throws Exception {
		this.capabilities = new CapabilityBuider();
		return capabilities.setUp(this.configFilePath, this.deviceIndex);
	}
}
