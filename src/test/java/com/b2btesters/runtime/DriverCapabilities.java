package com.b2btesters.runtime;

import org.openqa.selenium.remote.DesiredCapabilities;

public interface DriverCapabilities {
	
	DesiredCapabilities capabilities() throws Exception;
	
}
