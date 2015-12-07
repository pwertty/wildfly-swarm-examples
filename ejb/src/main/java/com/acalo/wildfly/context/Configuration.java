package com.acalo.wildfly.context;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.esotericsoftware.yamlbeans.YamlReader;

public class Configuration {
	private static Configuration instance;
	private Map<String,?> configMap;
	
	public Configuration() throws Exception{
		InputStream is = getClass().getClassLoader().getResourceAsStream("config/configuration.yaml");
		YamlReader reader = new YamlReader(new InputStreamReader(is));
		configMap = (Map<String, ?>)reader.read();
	}
	
	private synchronized static void createInstance() throws Exception{
		if (instance == null){
			instance = new Configuration();
		}
	}
	
	public static Configuration getInstance() throws Exception{
		if (instance == null){
			createInstance();
		}
		return instance;
	}

	public Map<String, ?> getConfigMap() {
		return configMap;
	}

	public void setConfigMap(Map<String, ?> configMap) {
		this.configMap = configMap;
	}
	
	public String getValue(String... keys) throws Exception{
		Object val = this.configMap;
		for (String key: keys){
			val = ((Map)val).get(key);
		}
		if (val instanceof String){
			return val.toString();
		}else{
			throw new Exception();
		}
	}
}
