package com.ssk.web.student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class GetStudentListServlet
 */
@WebServlet("/getStudentList.do")
public class GetStudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 0. 상태 정보 체크
				HttpSession session = request.getSession();
				String adminId = (String) session.getAttribute("adminId");
				System.out.println("ToMainServlet ==> adminId : " + adminId);

				if (adminId == null) {
					response.sendRedirect("/");
				}
				
		// 1. DB 연동 처리 

	}

}
