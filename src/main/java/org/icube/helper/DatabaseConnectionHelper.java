package org.icube.helper;

import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.icube.helper.DatabaseConnectionHelper;

public class DatabaseConnectionHelper {
	public DataSource masterDS;

	private final static String MASTER_URL = UtilHelper
			.getConfigProperty("master_sql_url");
	private final static String MASTER_USER = UtilHelper
			.getConfigProperty("master_sql_user");
	private final static String MASTER_PASSWORD = UtilHelper
			.getConfigProperty("master_sql_password");

	public DatabaseConnectionHelper() {
		PoolProperties p = new PoolProperties();
		p.setUrl(MASTER_URL);
		p.setDriverClassName("com.mysql.jdbc.Driver");
		p.setUsername(MASTER_USER);
		p.setPassword(MASTER_PASSWORD);
		p.setJmxEnabled(true);
		p.setTestWhileIdle(true); // this was false : RM
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(100);
		p.setInitialSize(10);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setConnectionProperties("connectionTimeout=\"300000\"");
		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.ResetAbandonedTimer");
		masterDS = new DataSource();
		masterDS.setPoolProperties(p);
	}

	@Override
	public void finalize() {
		org.apache.log4j.Logger.getLogger(DatabaseConnectionHelper.class)
				.debug("Shutting down databases ...");
		try {
			if (!masterDS.getConnection().isClosed()) {
				try {
					masterDS.getConnection().close();
					masterDS.close();
					org.apache.log4j.Logger.getLogger(
							DatabaseConnectionHelper.class).debug(
							"Connection to master database closed!!!!");
				} catch (SQLException e) {
					org.apache.log4j.Logger
							.getLogger(DatabaseConnectionHelper.class)
							.error("An error occurred while closing the mysql connection",
									e);
				}
			}

		} catch (SQLException e) {
			org.apache.log4j.Logger
					.getLogger(DatabaseConnectionHelper.class)
					.error("An error occurred while attempting to close db connection",
							e);
		}
	}
}
