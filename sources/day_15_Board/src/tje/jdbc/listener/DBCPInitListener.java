package tje.jdbc.listener;

import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.tomcat.dbcp.dbcp2.ConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;
import org.apache.tomcat.dbcp.dbcp2.PoolableConnectionFactory;


public class DBCPInitListener implements ServletContextListener {

	// 웹 서버의 시작 시, 자동 호출되는 메소드
	public void contextInitialized(ServletContextEvent sce) {
		String poolConfig = 
				sce.getServletContext().getInitParameter("POOL_CONFIG");
		Properties prop = new Properties();
		try {
			prop.load(new StringReader(poolConfig));
		} catch (IOException e) {
			throw new RuntimeException("config load fail", e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);
	}

	private void loadJDBCDriver(Properties prop) {
		String driverClass = prop.getProperty("JDBC_DRIVER");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}

	private void initConnectionPool(Properties prop) {
		try {
			String url = prop.getProperty("JDBC_URL");
			String user = prop.getProperty("JDBC_USER");
			String password = prop.getProperty("JDBC_PASSWORD");

			// ConnectionFactory : 입력된 url, user, password
			// 정보를 사용하여 커넥션을 생산할 수 있는 클래스
			ConnectionFactory connFactory = 
					new DriverManagerConnectionFactory(
							url, user, password);

			// PoolableConnectionFactory : 커넥션 풀에 DB 커넥션 객체를
			// 추가할 수 있는 클래스			
			PoolableConnectionFactory poolableConnFactory = 
					new PoolableConnectionFactory(connFactory, null);
			
			// 커넥션의 유효성을 확인하기 위한 쿼리 지정
			String validationQuery = prop.getProperty("VALIDATION_QUERY");
			if (validationQuery != null && !validationQuery.isEmpty()) {
				poolableConnFactory.setValidationQuery(validationQuery);
			}
			
			// GenericObjectPoolConfig : POOL 내부에 저장된
			// 객체들을 관리하기 위한 설정을 제공하는 클래스
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			// 풀 내부의 객체를 소멸시키기 위한 동작의 주기 
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			// 풀 내부의 객체를 소멸시킬 때 유효성을 검증하는 과정의 실행 여부
			poolConfig.setTestWhileIdle(true);
			int minIdle = getIntProperty(prop, "MIN_IDEL", 5);
			// 풀 내부에 저장할 객체의 최소값(기본 커넥션 개수)
			poolConfig.setMinIdle(minIdle);
			int maxTotal = getIntProperty(prop, "MAX_TOTAL", 50);
			// 풀로부터 생성될 수 있는 최대 값(특정 시점에 한정)
			poolConfig.setMaxTotal(maxTotal);

			// DB 커넥션을 저장할 풀 객체 생성
			GenericObjectPool<PoolableConnection> connectionPool = 
					new GenericObjectPool<>(poolableConnFactory, poolConfig);
			// 생상되는 커넥션들을 저장하기 위한 풀을 설정
			poolableConnFactory.setPool(connectionPool);
			
			// DBCP 드라이버 클래스 로딩
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			// DBCP 드라이버 클래스의 객체 반환
			PoolingDriver driver = (PoolingDriver)
				DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			String poolName = prop.getProperty("POOL_NAME");
			// DBCP 풀을 등록(사용자가 지정한 이름으로 등록함)
			driver.registerPool(poolName, connectionPool);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private int getIntProperty(Properties prop, String propName, int defaultValue) {
		String value = prop.getProperty(propName);
		if (value == null) return defaultValue;
		return Integer.parseInt(value);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
