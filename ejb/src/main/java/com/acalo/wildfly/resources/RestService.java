package com.acalo.wildfly.resources;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.acalo.wildfly.ejb.LocalEJB;

@Path("/testEJB")
public class RestService {

	private static final Logger logger = LogManager.getLogger("File_Log");

	@Resource(name = "java:jboss/jdbc/mysqlDS")
	DataSource ds;

	@Inject
	LocalEJB ejb;

	@GET
	@Path("/suma/{param1}/{param2}")
	@Produces(MediaType.TEXT_PLAIN)
	public Integer calcular(@PathParam(value = "param1") Integer valor1, @PathParam(value = "param2") Integer valor2) {
		return (valor1 + valor2);
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {

		logger.debug("Hello world - debug log");
		logger.info("Hello world - info log");
		logger.warn("Hello world - warn log");
		logger.error("Hello world - error log");

		StringBuilder build = new StringBuilder();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM new_table");

			while (rs.next()) {
				build.append(rs.getString("name")).append(" - ");

			}

			build.append(ejb.printRemote() + "xxxxx");

		} catch (SQLException e) {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException ex) {
			}

		}
		return build.toString();
	}

}
