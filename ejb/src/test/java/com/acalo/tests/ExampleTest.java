package com.acalo.tests;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import com.acalo.wildfly.context.ConfigEnum;
import com.acalo.wildfly.services.SomeServices;

public class ExampleTest {

	private SomeServices someServices;
	
	@Before
	public void initUnit(){
		someServices = new SomeServices();
	}
	
	@Test
	public void test() {
		String result = someServices.calculateSomething("jboss.http.port", 10, 5);
		assertThat(result, is("The result is: 15"));
	}

}
