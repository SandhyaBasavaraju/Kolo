package com.b2btesters.runtime;

public enum ServerConfigEnum {
	BROWSER_STACK("browserstack"), MOBILE_LAB("mobilelab"),	LOCAL("local");

	private final String serverType;

	ServerConfigEnum(String filePath) {
		this.serverType = filePath;
	}

	public String getServerName() {
		return serverType;
	}
}
