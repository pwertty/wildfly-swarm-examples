package com.acalo.wildfly.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateful;

@Remote
@Stateful
public class RemoteEJB {

	public String printRemote(){
		return "print remote value!!!!";
	}
}
