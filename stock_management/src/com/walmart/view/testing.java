package com.walmart.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.walmart.utils.DbUtil;


public class testing {

	public static void main(String[] args) throws SQLException {
		
		Connection con = DbUtil.getConnection("stock_mgmt");
		Statement st = con.createStatement();
//		String com1 = "create table ab(col int)";
		String com2 = "show tables like 'ab'";
//		int r1 = st.executeUpdate(com1);
		ResultSet r2 = st.executeQuery(com2);
//		System.out.println(r1);
		System.out.println(r2.getString(1));
		while(r2.next()) {
//			if("ab".equals(r2.getString(1)))
				System.out.println(r2.getString(1));
		}
	}

}
