package com.acalo.wildfly.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Message implements Serializable{

	private static final long serialVersionUID = 7682866021607181995L;
	
	private String name = "Valor";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
