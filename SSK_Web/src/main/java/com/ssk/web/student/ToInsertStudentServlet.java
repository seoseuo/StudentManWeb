package com.ssk.web.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssk.biz.student.StudentDAO;
import com.ssk.biz.student.StudentVO;

/**
 * Servlet implementation class ToInsertStudentServlect
 */

@WebServlet("/toInsertStudent.do")
public class ToInsertStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("ToInsertStudentServlet ==> /toInsertStudent.do");

		response.setContentType("text/html; charset=UTF-8");

		// printWriter 객체 생성.
		PrintWriter out = response.getWriter();

		// 회원 가입 시, 관리자가 설정한 등록 가능한 학생 수를 기준으로,
		// 해당 관리자가 등록할 학생 수가 설정한 학생 수를 초과하는지 여부를 확인한다.
		// 만약 등록 가능한 학생 수를 초과하면 등록이 불가능하다.

		// 1. 현재 세션에 로그인 하고 있는 관리자가 초기에 설정한 등록 학생 수 를 가져온다.
		HttpSession session = request.getSession();

		int adminCount = (Integer) session.getAttribute("adminCount");

		System.out.println("ToInsertStudentServlet ==> adminCount : " + adminCount);

		// 2. 해당 학생을 관리하는 관리자가 실질적으로 등록한 등록 학생 수를 가져온다.
		StudentVO stvo = new StudentVO();
		stvo.setAdminId((String) session.getAttribute("adminId"));

		StudentDAO stdao = new StudentDAO();

		// 3. 실질적으로 해당 관리자가 등록한 학생들의 인원수를 얻어냄.
		int studentCount = stdao.getStudentCountByAdminId(stvo);
		System.out.println("ToInsertStudentServlet ==> studentCount : " + studentCount);

		// 4. 두 값의 크기를 비교하여 등록 가능 여부를 확인한다.
		if (studentCount < adminCount) { // 실질적으로 등록 된 학생 수가, 초기에 관리자가 설정한 등록 학생 수 보다 적으면 학생 등록이 가능하다.
			System.out.println("ToInsertStudentServlet ==> 학생 등록 가능, 등록 페이지로 이동.");
			response.sendRedirect("insertStudent.html");
		}

		else {
			// 알람 창을 띄운 후, 이전 화면으로 이동.
			System.out.println("ToInsertStudentServlet ==> 학생 등록 불가능, 메인 페이지로 이동.");

			// 메인 화면으로 다시 이동한다.
			out.println("<script>");
			out.println("alert('가입 시 설정한 등록할 학생 수를 초과하는 등록입니다.');");
			out.println("history.back();"); // 이전 페이지, 즉 메인 페이지로 돌아가기
			out.println("</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
