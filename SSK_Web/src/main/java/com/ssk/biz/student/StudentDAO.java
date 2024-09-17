package com.ssk.biz.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssk.biz.common.JDBCUtil;

public class StudentDAO {
	// JDBC 관련 변수
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	private static final String BOARD_LIST = "select * from board order by seq desc";

	public void test() {
		try {

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_LIST);

			rs = stmt.executeQuery();

			while (rs.next()) {

				System.out.println(rs.getString("TITLE"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
	}

}
