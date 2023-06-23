package com.kolo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

public class DBUtil {

	protected Statement stmt;
	protected PreparedStatement ps;
	public String headervalue;
	private static final Logger logger = Logger.getLogger(DBUtil.class.getName());
	private static String query = "SELECT * from users";
	String host = "localhost";
	String port = "3306";

	public Map<String, String> getSelectQueryData() throws ClassNotFoundException, SQLException {
		Map<String, String> resultDataMap = new HashMap<String, String>();
		logger.info("Starting of getSelectQueryData method");

		try {

			Connection con = null;
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "spandana@123");

			logger.debug("connection status:" + con);

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();

			logger.info("ResultSet Columncount: " + columnCount);
			{
				while (rs.next()) {
					for (int i = 1; i <= columnCount; i++) {

						String headerName = rsmd.getColumnLabel(i);
						String value = rs.getString(i);
						logger.info(headerName + "::" + value);
						headervalue = value;
						resultDataMap.put(headerName, headervalue);
						logger.debug(headerName + "===>" + headervalue);
					}
				}
			}
		} catch (SQLException e) {

			System.out.println(e);

		} finally {
			// con.close();
		}

		logger.info("Ending of getSelectQueryData method");
		return resultDataMap;
	}
	
	  public static String randomNumberWithLength(int digits) {
	        return RandomStringUtils.randomNumeric(digits);
	    }


}
