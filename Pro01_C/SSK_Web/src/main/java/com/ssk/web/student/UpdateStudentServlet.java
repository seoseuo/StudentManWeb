package com.ssk.web.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssk.biz.student.StudentDAO;
import com.ssk.biz.student.StudentVO;

/**
 * Servlet implementation class updateStudentServlet
 */

@WebServlet("/updateStudent.do")
public class UpdateStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("updateStudentServlet ==> /updateStudent.do");

		// 1. 프론트 단에서 입력한 정보들 가져옴.
		String num = request.getParameter("num");
		String name = request.getParameter("name");
		String major = request.getParameter("major");
		String phone = request.getParameter("phone");

		// 2. 해당 학생의 관리자가 아닌 자가 수정 하려하면, 반려함.
		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("adminId");

		StudentVO stvo = new StudentVO();
		
		stvo.setNum(Integer.parseInt(num));
		stvo.setName(name);
		stvo.setMajor(major);
		stvo.setPhone(phone);

		StudentDAO stdao = new StudentDAO();
		String stvoAdminId = stdao.getStudent(stvo).getAdminId();

		System.out.println("updateStudentServlet ==> 수정하는 관리자 : " + adminId + "해당 학생의 관리자 : " + stvoAdminId);

		if (adminId.equals(stvoAdminId)) {
			// 같다면
			// 3. DB 연동 후 업데이트.
			stdao.updateStudent(stvo);

			// 4. 정보 수정 후 메인 화면으로 이동.
			System.out.println("updateStudentServlet ==> 학생 정보 수정 완료.");
			response.sendRedirect("/toMain.do");
		} else {
			// 다르다면 알림 후 뒤로 보내기.
			PrintWriter out = response.getWriter();
			System.out.println("updateStudentServlet ==> 학생 정보 수정 미완료, 해당 학생 관리자 권한 없음.");
			out.println("<script>");
			out.println("alert('해당 학생의 관리자 권한이 없습니다.');");
			out.println("history.back();"); // 이전 페이지로 돌아가기
			out.println("</script>");
		}

	}

}
