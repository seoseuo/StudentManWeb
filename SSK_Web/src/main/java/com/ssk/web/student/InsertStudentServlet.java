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

		// 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ServletContext context = getServletContext();
		encoding = context.getInitParameter("boardEncoding");

		// printWriter 객체 생성.
		PrintWriter out = response.getWriter();

		// 회원 가입 시, 관리자가 설정한 등록 가능한 학생 수를 기준으로,
		// 해당 관리자가 등록할 학생 수가 설정한 학생 수를 초과하는지 여부를 확인한다.
		// 만약 등록 가능한 학생 수를 초과하면 등록이 불가능하다.

		// 1. 현재 세션에 로그인 하고 있는 관리자가 초기에 설정한 등록 학생 수 를 가져온다.
		HttpSession session = request.getSession();

		int adminCount = (Integer) session.getAttribute("adminCount");

		System.out.println("InsertStudentServlet ==> adminCount : " + adminCount);

		// 2. 해당 학생을 관리하는 관리자가 실질적으로 등록한 등록 학생 수를 가져온다.
		StudentVO stvo = new StudentVO();
		stvo.setAdminId((String) session.getAttribute("adminId"));

		StudentDAO stdao = new StudentDAO();

		// 3. 실질적으로 해당 관리자가 등록한 학생들의 인원수를 얻어냄.
		int studentCount = stdao.getStudentCountByAdminId(stvo);
		System.out.println("InsertStudentServlet ==> studentCount : " + studentCount);

		// 4. 두 값의 크기를 비교하여 등록 가능 여부를 확인한다.
		if (studentCount < adminCount) { // 실질적으로 등록 된 학생 수가, 초기에 관리자가 설정한 등록 학생 수 보다 적으면 학생 등록이 가능하다.
			System.out.println("InsertStudentServlet ==> 학생 등록 실행.");

			// 1. 프론트에서 값 얻어냄.
			String num = request.getParameter("num");
			String name = request.getParameter("name");
			String major = request.getParameter("major");
			String phone = request.getParameter("phone");
			String adminId = (String) session.getAttribute("adminId");

			// 2. DB 연동 처리.
			stvo = new StudentVO();
			stvo.setNum(Integer.parseInt(num));
			stvo.setName(name);
			stvo.setMajor(major);
			stvo.setPhone(phone);
			stvo.setAdminId(adminId);

			try {
				stdao.insertStudent(stvo);
			} catch (SQLException e) {
				// 2-1 예외가 발생하면 프론트 단으로 경고 문구 띄워주기.

				System.out.println("InsertStudentServlet ==> 학번 중복.");
				out.println("<script>");
				out.println("alert('중복된 학번입니다.');");
				out.println("history.back();"); // 이전 페이지로 돌아가기
				out.println("</script>");
				return; //doPost 종료.
				
			}
			// 3. 등록을 마친 후 메인화면으로 이동.
			System.out.println("InsertStudentServlet ==> 학생 등록 완료.");
			response.sendRedirect("index.html");

		} else {
			// 알람 창을 띄운 후, index.html 화면으로 이동.
			System.out.println("InsertStudentServlet ==> 학생 등록 불가.");

			// 메인 화면으로 다시 이동한다.
			out.println("<script>");
			out.println("alert('가입 시 설정한 등록할 학생 수를 초과하는 등록입니다.');");
			out.println("history.back();"); // 이전 페이지로 돌아가기
			out.println("</script>");
		}
	}

}
