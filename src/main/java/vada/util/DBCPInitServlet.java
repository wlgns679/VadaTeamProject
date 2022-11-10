package vada.util;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import vada.constants.DBConnPoolConstants;
import vada.constants.VADAConstants;
 
public class DBCPInitServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	} // init

	private void loadJDBCDriver() {
		try {
			Class.forName((String)DBConnPoolConstants.props.get("JDBC_DRIVER_NAME"));
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
	} // ladJDBCDriver

	private void initConnectionPool() {

		try {

			String JDBC_URL = (String)DBConnPoolConstants.props.get("JDBC_URL");
			String JDBC_USER = (String)DBConnPoolConstants.props.get("JDBC_USER");
			String JDBC_PASS = (String)DBConnPoolConstants.props.get("JDBC_PASS");

			ConnectionFactory connFactory = new DriverManagerConnectionFactory(JDBC_URL, JDBC_USER, JDBC_PASS);

			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			poolableConnFactory.setValidationQuery(" select 1 ");

			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);

			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool(poolableConnFactory, poolConfig);
			poolableConnFactory.setPool(connectionPool);

			Class.forName((String) DBConnPoolConstants.props.get("JDBC_POOLING_DRIVER_NAME"));

			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver((String)DBConnPoolConstants.props.get("JDBC_POOLING_DRIVER"));
			driver.registerPool((String)DBConnPoolConstants.props.get("JDBC_POOL_NAME"), connectionPool);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	} // initConnectionPool

} // class
