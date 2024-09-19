package com.ssk.web.student;

import java.util.List;
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
 * Servlet implementation class GetStudentListServlet
 */
@WebServlet("/getStudentList.do")
public class GetStudentListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("GetStudentListServlet ==> /getStudentList.do");

		// 1. DB 연동 처리
		HttpSession session = request.getSession();
		String adminId = (String) session.getAttribute("adminId");

		StudentVO stvo = new StudentVO();

		stvo.setAdminId(adminId);
		System.out.println("GetStudentListServlet ==> 학생 목록을 가져올 관리자 : " + adminId);

		StudentDAO stdao = new StudentDAO();
		List<StudentVO> studentList = stdao.getStudentList(stvo);

		// 2. 응답 화면 구성
		PrintWriter out = response.getWriter();

		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("    <title>학생 등록</title>");
		out.println("    <link rel=\"stylesheet\" href=\"./css/style.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("    <div class=\"container\">");
		out.println("        <button class=\"menu-button\" onclick=\"window.location.href='/toMain.do';\">홈</button>");
		out.println("");
		out.println("        <h1>전체 출력 : 총 " + studentList.size() + "명</h1>");
		out.println("        <hr>");
		out.println("        <div class=\"card-container\">");

		// studentList에 아무 값이 없으면 응답 화면을 따로 보여준다.
		if (!studentList.isEmpty()) {
			// 셀 내용
			for (StudentVO getStvo : studentList) {
				out.println("            <div class=\"card\">");
				out.println("                <img src=\"./images/profile.png\" alt=\"학생 사진\">");
				out.println("                <div class=\"card-content\">");
				out.println("                    <h2>" + getStvo.getName() + "</h2>");
				out.println("                    <p><strong>학번:</strong> " + getStvo.getNum() + "</p>");
				out.println("                    <p><strong>학과:</strong> " + getStvo.getMajor() + "</p>");
				out.println("                    <p><strong>전화번호:</strong> " + getStvo.getPhone() + "</p>");
				out.println("                </div>");
				out.println("            </div>");
			}
		} else {
			out.println("<h2>등록된 학생이 없습니다.</h2>");
		}
		out.println("        </div>");
		out.println("    </div>");
		out.println("</body>");
		out.println("</html>");

	}

}
