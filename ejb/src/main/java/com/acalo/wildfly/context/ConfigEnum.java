package com.acalo.wildfly.context;

public enum ConfigEnum {
	PORT("jboss.http.port"), 
	SERVER("server");

	private String value;
    private ConfigEnum(String value) {
            this.value = value;
    }
    public String getValue() { 
    	return value;
    }
}
