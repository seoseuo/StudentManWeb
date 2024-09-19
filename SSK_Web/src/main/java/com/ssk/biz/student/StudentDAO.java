package com.ssk.biz.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssk.biz.admin.AdminVO;
import com.ssk.biz.common.JDBCUtil;

public class StudentDAO {
	// JDBC 관련 변수
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;

	private final String STUDENT_INSERT = "INSERT INTO STUDENT VALUES(?,?,?,?,?)"; // 학생 등록
	private final String STUDENT_GETCOUNT_BY_ADMIN_ID = "SELECT COUNT(*) FROM STUDENT WHERE ADMIN_ID = ?"; // 관리자가 관리하는
																											// // 학생 인원
																											// 수
	// 직접 카운트 : 데이터베이스 서버에서 수행되므로 네트워크와 애플리케이션 서버의 리소스를 절약할 수 있습니다.

	private final String STUDENT_GETLIST = "SELECT * FROM STUDENT WHERE ADMIN_ID = ? ORDER BY NUM ASC"; // 관리자가 관리하는 전체
																										// // 학생
	private final String STUDENT_GET = "SELECT * FROM STUDENT WHERE NUM = ?"; // 학생 정보 가져오기
	private final String STUDENT_UPDATE = "UPDATE STUDENT SET MAJOR = ?, PHONE = ? WHERE NUM = ?"; // 학생 정보 업데이트

	// 학생 등록하기
	public void insertStudent(StudentVO stvo) throws SQLException {
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(STUDENT_INSERT);
			stmt.setInt(1, stvo.getNum());
			stmt.setString(2, stvo.getName());
			stmt.setString(3, stvo.getMajor());
			stmt.setString(4, stvo.getPhone());
			stmt.setString(5, stvo.getAdminId());
			System.out.println(stvo.getAdminId() + " 관리자님, 학생 등록 " + stmt.executeUpdate() + " 건 데이터 처리 성공!");

		} catch (SQLException e) {
			if (e.getSQLState().equals("23505")) { // H2 데이터 베이스 unique 값 중복 에러코드.
				System.out.println("StudentDAO ==> insertStudent : 중복된 학번입니다.");
				throw new SQLException("StudentDAO ==> insertStudent : 중복된 학번입니다.", e);
			} else {
				throw e;
			}
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

	// 해당하는 관리자가 관리하는 학생 수 가져오기.
	public int getStudentCountByAdminId(StudentVO stvo) {

		// 혹시 모를 에러를 대비하여, 기본 값을 0으로 설정하여 데이터 조회가 안될 시. 학생 등록을 막는다.
		int result = 0;

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(STUDENT_GETCOUNT_BY_ADMIN_ID);

			stmt.setString(1, stvo.getAdminId());
			rs = stmt.executeQuery();

			if (rs.next()) { // rs에 담긴 결과 값을 담아준다.
				result = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		System.out.println(stvo.getAdminId() + " 관리자님, 등록한 학생 수는 " + result + " 명 건의 데이터 처리 성공!");
		return result;
	}

	// 학생 전체 출력
	public List<StudentVO> getStudentList(StudentVO stvo) {
		// TODO Auto-generated method stub

		List<StudentVO> studentList = new ArrayList<StudentVO>();

		try {

			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(STUDENT_GETLIST);
			stmt.setString(1, stvo.getAdminId());

			rs = stmt.executeQuery();

			while (rs.next()) {

				StudentVO getStvo = new StudentVO();
				getStvo.setNum(rs.getInt("NUM"));
				getStvo.setName(rs.getString("NAME"));
				getStvo.setMajor(rs.getString("MAJOR"));
				getStvo.setPhone(rs.getString("PHONE"));
				getStvo.setAdminId(rs.getString("ADMIN_ID"));

				studentList.add(getStvo); // 리스트에 한 객체 씩 등록해준다.

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		System.out.println(stvo.getAdminId() + " 관리자님, 전체출력 하는 학생 수 " + studentList.size() + " 명 건의 데이터 처리 성공!");
		return studentList;
	}

	// 학생 정보 조회
	public StudentVO getStudent(StudentVO stvo) {
		// TODO Auto-generated method stub

		// 반환 객체 생성.
		StudentVO getStvo = null;

		try {
			// 연결 얻기.
			conn = JDBCUtil.getConnection();

			// 쿼리문 적용.
			stmt = conn.prepareStatement(STUDENT_GET);

			// 매개변수로 받은 adminVo 객체의 아이디를 조회할 id 칼럼으로 설정.
			stmt.setInt(1, stvo.getNum());
			rs = stmt.executeQuery();

			// 가져올 객체가 한개 뿐 이지만, 반복문을 사용하는 이유는 , 코드의 일관성 및 예기치 못한 오류를 방지하기 위함이다.
			while (rs.next()) {
				getStvo = new StudentVO();
				getStvo.setNum(rs.getInt("NUM"));
				getStvo.setName(rs.getString("NAME"));
				getStvo.setMajor(rs.getString("MAJOR"));
				getStvo.setPhone(rs.getString("PHONE"));
				getStvo.setAdminId(rs.getString("ADMIN_ID"));

			}
			// 반환할 객체에 정보들을 설정.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		System.out.println(
				getStvo.getAdminId() + " 관리자님, 학생 정보 조회 ==> 학번 : " + getStvo.getNum() + " 이름 : " + getStvo.getName()
						+ " 전공 : " + getStvo.getMajor() + " 전화번호 : " + getStvo.getPhone() + " | 데이터 처리 성공!");
		return getStvo;
	}

	public void updateStudent(StudentVO stvo) {
		// TODO Auto-generated method stub

		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(STUDENT_UPDATE);
			stmt.setString(1, stvo.getMajor());
			stmt.setString(2, stvo.getPhone());
			stmt.setInt(3, stvo.getNum());
			System.out.println(stvo.getAdminId() + " 관리자님, 학생 정보 수정 " + stmt.executeUpdate() + " 건 데이터 처리 성공!");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}

}
