package com.ssk.web.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ToMainServlet
 */
@WebServlet("/toMain.do")
public class ToMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("ToMainServlet ==> /toMain.do");

		// 0. 상태 정보 체크
		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("adminId");
		System.out.println("ToMainServlet ==> adminId : " + adminId);

		if (adminId == null) {
			response.sendRedirect("/");
		}

		// 1. 응답 화면 구성
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("<title>학생 관리 웹</title>");
		out.println("<link rel=\"stylesheet\" href=\"./css/style.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println("<button class=\"menu-button\" onclick=\"window.location.href='/logout.do';\">로그아웃</button>");
		out.println(
				"<button class=\"menu\" id=\"info-button\" onclick=\"window.location.href='/adminInfo.do';\">내 정보</button>");
		out.println("<h1>학생 관리 웹</h1>");
		out.println("<hr>");
		out.println("<div class=\"menu\">");
		out.println("<a href=\"/toInsertStudent.do\" class=\"menu-item\">");
		out.println("<h2>학생 등록</h2>");
		out.println("<p>새로운 학생을 등록합니다.</p>");
		out.println("</a>");
		out.println("<a href=\"/getStudentList.do\" class=\"menu-item\">");
		out.println("<h2>전체 출력</h2>");
		out.println("<p>모든 학생의 정보를 출력합니다.</p>");
		out.println("</a>");
		out.println("<a href=\"searchStudent.html\" class=\"menu-item\">");
		out.println("<h2>학생 조회</h2>");
		out.println("<p>특정 학생을 조회합니다.</p>");
		out.println("</a>");
		out.println("<a href=\"editStudentSearch.html\" class=\"menu-item\">");
		out.println("<h2>정보 수정</h2>");
		out.println("<p>학생 정보를 수정합니다.</p>");
		out.println("</a>");
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");

	}

}
