package com.ssk.biz.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssk.biz.common.JDBCUtil;

public class AdminDAO {
	// JDBC 관련 변수
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	private final String ADMIN_INSERT = "INSERT INTO ADMIN VALUES(?,?,?,?)";	// 관리자 회원 가입 
	
	// 관리자 회원 가입 
		public void insertAdmin(AdminVO advo) {

			try {
				conn = JDBCUtil.getConnection();
				stmt = conn.prepareStatement(ADMIN_INSERT);

				stmt.setString(1, advo.getId());
				stmt.setString(2, advo.getPassword());
				stmt.setString(3, advo.getName());
				stmt.setInt(4, advo.getCount());

				System.out.println(advo.getName()+"관리자님, 회원가입" + stmt.executeLargeUpdate() + " 건 데이터 처리 성공!");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JDBCUtil.close(stmt, conn);
			}

		}

}
