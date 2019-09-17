package com.it.ibm.kafka.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JdbcConnection {
	private static final Logger logger = LogManager.getLogger(JdbcConnection.class);
	private static Connection conn = null;
	
	
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public static Connection connectionOpen(HashMap<String,String> hmConnection) throws SQLException, ClassNotFoundException {
		
		Class.forName(hmConnection.get(Constants.CONN_DRIVER));
		logger.debug(hmConnection.get(Constants.CONN_URL));
		if (conn==null)
			conn = DriverManager.getConnection(hmConnection.get(Constants.CONN_URL), hmConnection.get(Constants.CONN_USER), hmConnection.get(Constants.CONN_PW));
		logger.debug("ConnectionOpen - " + hmConnection.get(Constants.CONN_URL));
		return conn;
	}

	public static void connectionClose() throws SQLException {
		if (conn != null) {
			conn.close();
			conn=null;
		}
	}
	
	public static void stat() throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet resultset = stmt.executeQuery("select * from testtable");
		while (resultset.next()) { 
			logger.debug("");
		}
	}
	
	public ResultSet executeQuery(String sql) throws SQLException {
		Statement s = conn.createStatement();
		ResultSet rs = null;
		try {
			rs = s.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
}
