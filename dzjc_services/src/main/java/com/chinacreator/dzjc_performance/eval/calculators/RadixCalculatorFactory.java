package com.chinacreator.dzjc_performance.eval.calculators;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.chinacreator.c2.config.ConfigManager;
import com.chinacreator.c2.dao.DaoFactory;
import com.chinacreator.c2.dao.mybatis.util.JDBCUtils;
import com.chinacreator.dzjc_performance.DicEvalPoint;
import com.chinacreator.dzjc_performance.DicEvalRadix;
import com.chinacreator.dzjc_performance.EvalInstance;
import com.chinacreator.util.JDBCUtil;

/**
 * 绩效基数分数计算的类
 * @author Administrator
 *
 */
public class RadixCalculatorFactory {
	private static final RadixCalculatorFactory raduxCalculatorFactory = new RadixCalculatorFactory();
	
	private static final String DATA_CONNECTION = ConfigManager.getInstance().getConfig("accept_db_url");
	private static final String USERNAME = ConfigManager.getInstance().getConfig("accept_db_username");
	private static final String PASSWORD = ConfigManager.getInstance().getConfig("accept_db_password");
	
	//private static  Logger log = LoggerFactory.getLogger(RadixCalculatorFactory.class);
	
	public static RadixCalculatorFactory getInstance() {
		return raduxCalculatorFactory;
	}
	
	/**
	 * 根据基数名称查询基数信息
	 * @throws SQLException 
	 */
	public Double calclulateRadix(DicEvalPoint evalPoint,EvalInstance evalTable,String radixName,Date startime,Date endTime){
		//Connection connection = getDataSource();
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			
			DicEvalRadix radix = getRadixByName(radixName);//根据名称得到基数
			
			//获得基数的sql
			String radixSql = radix.getRadixSql();
			String sql = toPreparedSQL(radixSql);//用”?”替换模板中的变量
			
			prepareStatement = connection.prepareStatement(sql);
			
			List<String> variables = getVariables(radix);//从模板中提取变量
			int i = 1;
			for(String var : variables){
				if("startDate".equals(var)){//测评开始时间
					prepareStatement.setTimestamp(i, new java.sql.Timestamp(startime.getTime()));
				}else if("endDate".equals(var)){//测评结束时间
					prepareStatement.setTimestamp(i, new java.sql.Timestamp(endTime.getTime()));
				}else if("orgId".equals(var)){//考核对象的机构id
					prepareStatement.setString(i, evalTable.getOrgId());
				}else if("pointId".equals(var)){//当前指标的id
					prepareStatement.setString(i, evalPoint.getPointId());
				/*}else if("appUser".equals(var)){
					prepareStatement.setString(i, dataName);*/
				}
				i++;
			}
			//执行基数的sql语句，得到一个数值
			resultSet = prepareStatement.executeQuery();
			Double double1 =  null;
			while (resultSet.next()) {
				double1 = resultSet.getDouble(1);
				
			}
			return double1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("没有查询到基数信息==="+e.getMessage());
		} finally {
			//关闭连接
			closeConnection(connection,prepareStatement);
			JDBCUtils.closeResultset(resultSet);
			JDBCUtils.closeStatement(prepareStatement);
			JDBCUtils.closeConnection(connection);
		}
		
		
	}

	//根据名称得到基数
	public DicEvalRadix getRadixByName(String radixName) {
		DicEvalRadix radix = new DicEvalRadix();
		radix.setRadixName(radixName);
		DicEvalRadix evalRadix = DaoFactory.create(DicEvalRadix.class).selectOne(radix);
		return evalRadix;
	}
	
	/**
	 * 用”?”替换模板中的变量
	 * @param template
	 * @return
	 */
	public String toPreparedSQL(String radixSql) {
		Pattern pattern = Pattern.compile("\\$\\{([^\\}]*)\\}");//
		Matcher matcher = pattern.matcher(radixSql);
		return matcher.replaceAll("?");
	}
	
	/**
	 * 从模板中提取变量
	 * @param template
	 * @return
	 */
	public List<String> getVariables(DicEvalRadix radix) {
		List<String> resultList = new ArrayList<String>(); 
		Pattern pattern = Pattern.compile("\\$\\{([^\\}]*)");//
		Matcher matcher = pattern.matcher(radix.getRadixSql());
		while(matcher.find()){
			resultList.add(matcher.group(1));
		}
		return resultList;
	}
	
	/**
	 * 获取jdbc链接的方法
	 */
	public Connection  getDataSource(){
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(DATA_CONNECTION,USERNAME,PASSWORD);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭链接的方法
	 */
	public void closeConnection(Connection connection,PreparedStatement preparedStatement){
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
