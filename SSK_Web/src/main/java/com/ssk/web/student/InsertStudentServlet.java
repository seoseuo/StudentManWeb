package com.ssk.web.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssk.biz.student.StudentDAO;
import com.ssk.biz.student.StudentVO;

/**
 * Servlet implementation class InsertStudentServlet
 */

@WebServlet("/insertStudent.do")
public class InsertStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String encoding;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("InsertStudentServlet ==> /insertStudent.do");
		
		// 0. 상태 정보 체크
				HttpSession session = request.getSession();
				String adminId = (String) session.getAttribute("adminId");
				System.out.println("InsertStudentServlet ==> adminId : " + adminId);

				if (adminId == null) {
					response.sendRedirect("/");
				}

		// 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ServletContext context = getServletContext();
		encoding = context.getInitParameter("boardEncoding");

		// printWriter 객체 생성.
		PrintWriter out = response.getWriter();

		System.out.println("InsertStudentServlet ==> 학생 등록 실행.");

		// 1. 프론트에서 값 얻어냄.
		String num = request.getParameter("num");
		String name = request.getParameter("name");
		String major = request.getParameter("major");
		String phone = request.getParameter("phone");
		
		session = request.getSession();
		adminId = (String) session.getAttribute("adminId");

		// 2. DB 연동 처리.
		StudentVO stvo = new StudentVO();
		stvo.setNum(Integer.parseInt(num));
		stvo.setName(name);
		stvo.setMajor(major);
		stvo.setPhone(phone);
		stvo.setAdminId(adminId);

		try {
			StudentDAO stdao = new StudentDAO();
			stdao.insertStudent(stvo);
		} catch (SQLException e) {
			// 2-1 예외가 발생하면 프론트 단으로 경고 문구 띄워주기.

			System.out.println("InsertStudentServlet ==> 학번 중복.");
			out.println("<script>");
			out.println("alert('중복된 학번입니다.');");
			out.println("history.back();"); // 이전 페이지로 돌아가기
			out.println("</script>");
			return; // doPost 종료.

		}
		// 3. 등록을 마친 후 메인화면으로 이동.
		System.out.println("InsertStudentServlet ==> 학생 등록 완료.");
		response.sendRedirect("/toMain.do");

	}

}
