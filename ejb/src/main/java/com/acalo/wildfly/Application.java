package com.acalo.wildfly;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;

import com.acalo.wildfly.ejb.RemoteEJB;
import com.acalo.wildfly.resources.RestService;

@ApplicationScoped
@ApplicationPath("prueba")
public class Application extends javax.ws.rs.core.Application{

	@Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(RestService.class);
        classes.add(RemoteEJB.class);
        return classes;
    }
	
}
