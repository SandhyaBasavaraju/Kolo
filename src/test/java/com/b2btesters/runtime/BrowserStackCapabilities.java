package com.b2btesters.runtime;

import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserStackCapabilities implements DriverCapabilities {

	private String configFilePath = null;
	private String deviceIndex = null;
	private CapabilityBuider capabilities = null;

	public BrowserStackCapabilities(String configFilePath, String deviceIndex) {

		this.configFilePath = configFilePath;
		this.deviceIndex = deviceIndex;
		

	}

	@Override
	public DesiredCapabilities capabilities() throws Exception {
		this.capabilities = new CapabilityBuider();
		return capabilities.setUp(this.configFilePath, deviceIndex);
	}
}
