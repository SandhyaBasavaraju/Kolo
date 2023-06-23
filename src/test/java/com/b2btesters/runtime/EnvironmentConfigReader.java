package com.b2btesters.runtime;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EnvironmentConfigReader {
	private String server = null;
	private String serverURL = null;
	private JSONObject config = null;
	private JSONObject config2 = null;

	public static final Logger logger = Logger.getLogger(EnvironmentConfigReader.class.getName());
	public EnvironmentConfigReader() throws FileNotFoundException, IOException, ParseException{
		JSONParser parser = new JSONParser();
		this.config = (JSONObject) parser.parse(new FileReader(ConfigFilePathEnum.SERVER_CONFIG.getConfigFilePath()));
		logger.info(config.toJSONString());
	}
	public String getServer() {
		logger.info("Starting of getServer method");
		this.server = (String) config.get("server");
		logger.info("Ending of getServer method");
		return this.server;
	}
	public String getUdid() {
		logger.info("Starting of getUdid method");
		this.server = (String) config.get("udid");
		logger.info("Ending of getUdid method");
		return this.server;
	}
	public String getbrowser() {
		logger.info("Starting of getbrowser method");
		this.server = (String) config.get("browser");
		logger.info("Ending of getbrowser method");
		return this.server;
	}
	
	public String getServerURL() {
		logger.info("Starting of getServerURL method");
		logger.info(this.server);
		ArrayList<JSONObject> serverURLs = (ArrayList<JSONObject>) config.get("urls");
		if (server.equalsIgnoreCase(ServerConfigEnum.BROWSER_STACK.getServerName())) {
			this.serverURL = (String) serverURLs.get(0).get("browserstackURL");
		} else if (server.equalsIgnoreCase(ServerConfigEnum.LOCAL.getServerName())) {
			this.serverURL = (String) serverURLs.get(1).get("localURL");
			logger.info(this.serverURL);
		}
		logger.info("Ending of getServerURL method");
		return this.serverURL;
	}
}
