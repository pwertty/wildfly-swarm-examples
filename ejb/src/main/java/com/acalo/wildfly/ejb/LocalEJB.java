package com.acalo.wildfly.ejb;

import javax.ejb.Local;
import javax.ejb.Stateful;

@Local
@Stateful
public class LocalEJB{

	public String printRemote(){
		
		
		
		return "print remote value!!!!";
	}
}
