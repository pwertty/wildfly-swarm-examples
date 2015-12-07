package com.acalo.wildfly.services;

import com.acalo.wildfly.context.ConfigEnum;

public class SomeServices {
	public String calculateSomething(String type, int value1, int value2){
		int total = 0;
		if (ConfigEnum.PORT.getValue().equals(type)){
			total = value1 + value2;
		}else if (ConfigEnum.SERVER.getValue().equals(type)){
			total = value1 - value2;
		}
		
		return "The result is: "+total;
	}
	
	public int calculateSomethingInt(String type, int value1, int value2){
		int total = 0;
		if (ConfigEnum.PORT.getValue().equals(type)){
			total = value1 + value2;
		}else if (ConfigEnum.SERVER.getValue().equals(type)){
			total = value1 - value2;
		}
		
		return total;
	}
}
