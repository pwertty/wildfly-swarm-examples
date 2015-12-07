package com.acalo.wildfly;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.config.datasources.DataSource;
import org.wildfly.swarm.config.datasources.JDBCDriver;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.container.JARArchive;
import org.wildfly.swarm.datasources.DatasourcesFraction;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import com.acalo.wildfly.context.ConfigEnum;
import com.acalo.wildfly.context.Configuration;
import com.acalo.wildfly.ejb.LocalEJB;
import com.acalo.wildfly.resources.RestService;
import com.mysql.fabric.xmlrpc.base.Data;


public class App {
	public static void main(String[] args) throws Exception{
		Container container = new Container();
		
/*		container.fraction(new DatasourcesFraction()
				.jdbcDriver("mysql", (d) -> {
                	d.driverModuleName("com.mysql");
                    d.driverName("mysql");
                })
                .dataSource("mysqlDS", (ds) -> {
                    ds.driverName("mysql");
                    ds.connectionUrl("jdbc:mysql://localhost:3306/test");
                    ds.userName("root");
                    ds.password("saoloi32");
                })
);*/
		
		Configuration config = Configuration.getInstance();
		String value = config.getValue(ConfigEnum.SERVER.getValue(), ConfigEnum.PORT.getValue());
		System.setProperty(ConfigEnum.PORT.getValue(),value);
		
		container.fraction(new DatasourcesFraction()
		 .jdbcDriver(new JDBCDriver("com.mysql")
                 .driverName("com.mysql")
                 .driverModuleName("com.mysql")
                 .xaDatasourceClass("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource"))
         .dataSource(new DataSource("mysqlDS")
                 .driverName("com.mysql")
                 .jndiName("java:jboss/jdbc/mysqlDS")
                 .connectionUrl("jdbc:mysql://localhost:3306/test")
                 .userName("root")
                 .password("saoloi32")));
 //container.fraction(new TransactionsFraction());
		
		//JARArchive deployment = ShrinkWrap.create(JARArchive.class);
		JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
		deployment.addPackage(App.class.getPackage());
		deployment.addPackage(LocalEJB.class.getPackage());
		deployment.addPackage(RestService.class.getPackage());
		//deployment.addPackage(MyCustomImpl.class.getPackage());
		
		deployment.addAllDependencies();
		
		container.start().deploy(deployment);
		
		/*InputStream is = App.class.getClassLoader().getResourceAsStream("config/log4j2.xml");
		ConfigurationSource source = new ConfigurationSource(is);
		Configurator.initialize(null, source);*/
		
		//Thread.sleep(1500);

		/*Context context = new InitialContext(); 
		Object dataSource = context.lookup("java:jboss/jdbc/mysqlDS");
		
		//javax.sql.DataSource dataSource = (javax.sql.DataSource)context.lookup("java:jboss/jdbc/mysqlDS");
		
		Method method = dataSource.getClass().getMethod("getConnection");
		Connection conn = (Connection)method.invoke(dataSource);
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM new_table");
		
		while (rs.next()){
			System.out.println(rs.getString("name"));
			System.out.println("------");
		}*/
	}
}
