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

	private final String ADMIN_INSERT = "INSERT INTO ADMIN VALUES(?,?,?,?)"; // 관리자 회원 가입
	private final String ADMIN_GET = "SELECT * FROM ADMIN WHERE ID = ?"; // 관리자 정보 가져오기

	// 관리자 회원 가입
	public void insertAdmin(AdminVO advo) {

		try {
			// 연결 얻기.
			conn = JDBCUtil.getConnection();

			// 쿼리문 적용.
			stmt = conn.prepareStatement(ADMIN_INSERT);

			stmt.setString(1, advo.getId());
			stmt.setString(2, advo.getPassword());
			stmt.setString(3, advo.getName());
			stmt.setInt(4, advo.getCount());
			// 정보 기입.

			System.out.println(advo.getId() + " 관리자님, 회원가입" + stmt.executeLargeUpdate() + " 건 데이터 처리 성공!");
			// 출력문으로 확인함과 동시에 stmt.execute 실행.

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}

	}

	// 관리자 정보 조회
	public AdminVO getAdmin(AdminVO advo) {
		// TODO Auto-generated method stub

		// 반환 객체 생성.
		AdminVO getAdvo = null;

		try {
			// 연결 얻기.
			conn = JDBCUtil.getConnection();

			// 쿼리문 적용.
			stmt = conn.prepareStatement(ADMIN_GET);

			// 매개변수로 받은 adminVo 객체의 아이디를 조회할 id 칼럼으로 설정.
			stmt.setString(1, advo.getId());
			rs = stmt.executeQuery();

			// 가져올 객체가 한개 뿐 이지만, 반복문을 사용하는 이유는 , 코드의 일관성 및 예기치 못한 오류를 방지하기 위함이다.
			while (rs.next()) {
				getAdvo = new AdminVO();
				getAdvo.setId(rs.getString("ID"));
				getAdvo.setPassword(rs.getString("PASSWORD"));
				getAdvo.setName(rs.getString("NAME"));
				getAdvo.setCount(rs.getInt("COUNT"));
			}
			// 반환할 객체에 정보들을 설정.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		System.out.println(advo.getId() + " 관리자님, 정보조회 ==> 아이디 : " + getAdvo.getId() + " 이름 : " + getAdvo.getName()
				+ " 등록할 학생 수 : " + getAdvo.getCount() + " | 데이터 처리 성공!");
		return getAdvo;
	}

}
