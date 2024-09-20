package com.ssk.web.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssk.biz.admin.AdminDAO;
import com.ssk.biz.admin.AdminVO;
import com.ssk.biz.student.StudentVO;

/**
 * Servlet implementation class AdminInfoServlet
 */

@WebServlet("/adminInfo.do")
public class AdminInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("AdminInfoServlet ==> /adminInfo.do");

		// 1. 관리자 아이디를 세션에서 가져온다.
		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("adminId");

		// 2. DB연동하여 값 가져오기.
		AdminVO advo = new AdminVO();
		advo.setId(adminId);

		AdminDAO adao = new AdminDAO();

		AdminVO getAdvo = adao.getAdmin(advo);

		// 3. 응답화면 구성

		PrintWriter out = response.getWriter();

		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("    <title>학생 관리 웹 - 내 정보</title>");
		out.println("    <link rel=\"stylesheet\" href=\"./css/style.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println("    <button class=\"menu-button\" onclick=\"window.location.href='toMain.do';\">홈</button>");
		out.println("");
		out.println("    <h1>학생 조회</h1>");
		out.println("    <hr>");
		// studentList에 아무 값이 없으면 응답 화면을 따로 보여준다.
		if (getAdvo != null) {
			// 셀 내용
			out.println("            <div class=\"card\">");
			out.println("                <img src=\"./images/profile.png\" alt=\"학생 사진\">");
			out.println("                <div class=\"card-content\">");
			out.println("                    <h2>" + getAdvo.getName() + "</h2>");
			out.println("                    <p><strong>아이디:</strong> " + getAdvo.getId() + "</p>");
			out.println("                    <p><strong>등록할 학생 수:</strong> " + getAdvo.getCount() + "</p>");
			out.println("                </div>");
			out.println("            </div>");
			out.println("</div>");
			System.out.println("AdminInfoServlet ==> 내 정보 이동 완료.");

		} else {
			out.println("<h2>등록된 학생이 없습니다.</h2>");
			System.out.println("AdminInfoServlet ==> 내 정보 이동 미완료, 정보 결과 없음.");
		}
		out.println("        </div>");
		out.println("    </div>");
		out.println("</body>");
		out.println("</html>");

	}

}
