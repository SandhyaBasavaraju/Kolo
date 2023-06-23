package com.b2btesters.runtime;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;

public class CapabilityBuider {
	public static final Logger logger = Logger.getLogger(CapabilityBuider.class.getName());
	public WebDriver driver;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public DesiredCapabilities setUp(String configFilePath, String deviceIndex) throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject config = (JSONObject) parser.parse(new FileReader(configFilePath));
		JSONArray envs = (JSONArray) config.get("environments");

		DesiredCapabilities capabilities = new DesiredCapabilities();

		Map<String, String> envCapabilities = (Map<String, String>) envs.get(Integer.parseInt(deviceIndex) - 1);
		Iterator it = envCapabilities.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
		}

		Map<String, String> commonCapabilities = (Map<String, String>) config.get("capabilities");
		it = commonCapabilities.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (capabilities.getCapability(pair.getKey().toString()) == null) {
				capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
			}
		}

		/*
		 * String username = System.getenv("BROWSERSTACK_USERNAME"); if (username ==
		 * null) { username = (String) config.get("username"); }
		 * 
		 * String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY"); if (accessKey ==
		 * null) { accessKey = (String) config.get("access_key"); }
		 * 
		 * String app = System.getenv("BROWSERSTACK_APP_ID"); if (app != null &&
		 * !app.isEmpty()) { capabilities.setCapability("app", app); }
		 */
		return capabilities;
	}
}
