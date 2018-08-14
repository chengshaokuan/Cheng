package com.imooc.utils.util;

import java.sql.*;

public class JdbcCon {

	//private static String url = "jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER = yes)(ADDRESS = (PROTOCOL = TCP)(HOST =10.150.60.84)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST =10.150.60.82)(PORT = 1521))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = ecif2)))";
//	private static String url = "jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER = yes)(ADDRESS = (PROTOCOL = TCP)(HOST =10.100.148.41)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST =10.100.148.41)(PORT = 1521))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = ecif2)))";
	private static String url = "jdbc:oracle:thin:@(description=(address_list= (address=(host=10.100.148.41) (protocol=tcp)(port=1521))(address=(host=10.100.148.41)(protocol=tcp) (port=1521)) (failover=yes))(connect_data=(service_name=cedb )))";
//	private static String url = "jdbc:oracle:thin:@(DESCRIPTION=(FAILOVER = yes)(ADDRESS = (PROTOCOL = TCP)(HOST =10.174.60.42)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST =10.174.60.44)(PORT = 1521))(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = predb)))";
//	private static String url="jdbc:oracle:thin:@(description=(address_list= (address=(host=10.100.136.16) (protocol=tcp)(port=1521))(address=(host=10.100.136.16)(protocol=tcp) (port=1521)) (failover=yes))(connect_data=(service_name=cedb )))";
	// system为登陆oracle数据库的用户名
//	private static String user = "ecif2";
	private static String user = "ecif1219";	
//	private static String user = "ECIF2";
//	private static String user = "ecif";
	// manager为用户名system的密码
//	private static String password = "bi*$U5";
	private static String password = "ecif20*>";
//	private static String password = "Cu5n,kjHus";
//	private static String password = "(B+2#-I0vQ";
	public static PreparedStatement ps;
	public static ResultSet rs;
	public static Statement st;

	
	Connection conn;
	// 连接数据库的方法
	public Connection getConnection() {
		
		try {
			// 初始化驱动包
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 根据数据库连接字符，名称，密码给conn赋值
			 conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
	}
	
	
	//关闭数据库方法
	public static void closeCon(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 测试能否与oracle数据库连接成功
	public static void main(String[] args) {
		JdbcCon basedao = new JdbcCon();
		Connection conn = basedao.getConnection();
		if (conn == null) {
			System.out.println("与oracle数据库连接失败！");
		} else {
			System.out.println("与oracle数据库连接成功！");
		}
	}

}
