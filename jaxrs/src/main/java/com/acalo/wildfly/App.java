package com.acalo.wildfly;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import com.acalo.wildfly.resources.TestResource;

public class App {
	public static void main(String[] args) throws Exception{
		Container container = new Container();
		
		JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
		deployment.addPackage(TestResource.class.getPackage());
		
		container.start().deploy(deployment);
		
	}
}
