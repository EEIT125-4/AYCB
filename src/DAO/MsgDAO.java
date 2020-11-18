package DAO;

import java.sql.*;

public class MsgDAO {

	private Connection conn;

	public MsgDAO(Connection conn) {
		this.conn = conn;
	}

	
	public boolean insertMsg(MsgBean msgData) {
		try {
			String sqlString = "insert into message values('" 
					+ msgData.getMsg_id() + "','" 
					+ msgData.getMsg_title()+ "','" 
					+ msgData.getMsg_desc() + "','" 
					+ msgData.getMsg_type() + "','" 
					+ msgData.getMsg_date()
					+ "')";

			Statement stmt = conn.createStatement();
			System.out.println(sqlString);
			int updatecount = stmt.executeUpdate(sqlString);
			stmt.close();
			if (updatecount >= 1) {
				return true;
			} else {
				return false;
				}
		} catch (Exception e) {
			System.err.println("" + e);
			return false;
		}
	}

}