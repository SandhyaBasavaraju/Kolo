package com.b2btesters.runtime;

public class DriverCapabilitiesFactory {

	public DriverCapabilities getCapabilities(String environmentType, String deviceIndex) {

		if (environmentType == null) {
			return null;
		} else if (environmentType.equalsIgnoreCase(ServerConfigEnum.BROWSER_STACK.getServerName())) {
			return new BrowserStackCapabilities(ConfigFilePathEnum.BROWSER_STACK.getConfigFilePath(), deviceIndex);
		} else if (environmentType.equalsIgnoreCase(ServerConfigEnum.LOCAL.getServerName())) {
			return new LocalCapabilities(ConfigFilePathEnum.LOCAL.getConfigFilePath(), deviceIndex);
			}

		return null;
	}

}
