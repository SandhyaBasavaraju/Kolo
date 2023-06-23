package com.b2btesters.runtime;

public enum ConfigFilePathEnum {
	BROWSER_STACK("src/main/resources/browserstack.conf.json"),
	LOCAL("src/main/resources/local.conf.json"), SERVER_CONFIG("src/main/resources/config.json");

	private final String path;

	ConfigFilePathEnum(String string) {
		this.path = string;
	}

	public String getConfigFilePath() {
		// (otherName == null) check is not needed because name.equals(null) returns
		// false
		return path;
	}
}
