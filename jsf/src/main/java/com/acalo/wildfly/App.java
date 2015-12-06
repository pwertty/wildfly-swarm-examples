package com.acalo.wildfly;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.undertow.WARArchive;

import com.acalo.wildfly.beans.Message;

public class App {
	
	private static final String PATH_WEBAPP =  App.class.getClassLoader().getResource("").getPath();
	
	public static void main(String[] args) throws Exception{
		Container container = new Container();
		
		WARArchive deployment = ShrinkWrap.create(WARArchive.class);
		
//		File file = new File(PATH_WEBAPP);
//		recursiveFilesDeployment(file,deployment);
		deployment.addAsWebResource(new ClassLoaderAsset("index.xhtml", App.class.getClassLoader()), "index.xhtml");
		
		deployment.addAsWebInfResource(new ClassLoaderAsset("WEB-INF/template.xhtml", App.class.getClassLoader()), "template.xhtml");
		deployment.addAsWebInfResource(new ClassLoaderAsset("WEB-INF/web.xml", App.class.getClassLoader()),"web.xml");
		
		deployment.addPackage(Message.class.getPackage());
		
		deployment.addAllDependencies();
		
		container.start().deploy(deployment);
	}
	
	/*private static void recursiveFilesDeployment(File file, WARArchive deployment){
		if (file.isDirectory()){
			for (File fileIt:file.listFiles()){
				recursiveFilesDeployment(fileIt,deployment);
			}
		}else{
			if (file.getPath().contains("WEB-INF")){
				deployment.addAsWebInfResource(new ClassLoaderAsset("WEB-INF/template.xhtml", App.class.getClassLoader()), "template.xhtml");
			}else{
				deployment.addAsWebResource(new ClassLoaderAsset("index.xhtml", App.class.getClassLoader()), "index.xhtml");
			}
		}
	}*/
}
