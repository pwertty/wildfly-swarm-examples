package com.acalo.wildfly;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.config.datasources.DataSource;
import org.wildfly.swarm.config.datasources.JDBCDriver;
import org.wildfly.swarm.container.Container;
import org.wildfly.swarm.container.JARArchive;
import org.wildfly.swarm.datasources.DatasourcesFraction;

import com.acalo.wildfly.ejb.RemoteEJB;

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
		
		JARArchive deployment = ShrinkWrap.create(JARArchive.class);
		deployment.addPackage(RemoteEJB.class.getPackage());
		
		container.start().deploy(deployment);
		
		Thread.sleep(1500);


		Context context = new InitialContext(); 
		Object dataSource = context.lookup("java:jboss/jdbc/mysqlDS");
		
		Method method = dataSource.getClass().getMethod("getConnection");
		Connection conn = (Connection)method.invoke(dataSource);
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM new_table");
		
		while (rs.next()){
			System.out.println(rs.getString("name"));
			System.out.println("------");
		}
		
		
		
        InitialContext ctx = new InitialContext();
        NamingEnumeration<NameClassPair> list = ctx.list("java:jboss/mysqlDS");
        while (list.hasMore()) {
            System.out.println(list.next().getName());
        }
		
	}
}
