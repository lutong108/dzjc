package com.chinacreator.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.chinacreator.c2.config.ConfigManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtil {

	private static final ComboPooledDataSource DATA_SOURCE =new ComboPooledDataSource();
	private static final String DRIVER_CLASS = "oracle.jdbc.OracleDriver";
	private static final String URL = ConfigManager.getInstance().getConfig("accept_db_url");
	private static final String USERNAME = ConfigManager.getInstance().getConfig("accept_db_username");
	private static final String PASSWORD = ConfigManager.getInstance().getConfig("accept_db_password");
	
	/**
	 * 获取连接的方法
	 * @throws SQLException 
	 * 
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {
			DATA_SOURCE.setDriverClass(DRIVER_CLASS);
			DATA_SOURCE.setJdbcUrl(URL);
			DATA_SOURCE.setUser(USERNAME);
			DATA_SOURCE.setPassword(PASSWORD);
			
			connection = DATA_SOURCE.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * 关闭链接的方法
	 */
	public static void closeConnection(Connection connection,PreparedStatement preparedStatement){
		if(preparedStatement != null){
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
