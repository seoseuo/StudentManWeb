package com.ssk.web.student;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssk.biz.student.StudentDAO;
import com.ssk.biz.student.StudentVO;

/**
 * Servlet implementation class searchStudentServlet
 */

@WebServlet("/searchStudent.do")
public class searchStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("searchStudentServlet ==> /insertStudent.do");

		// 1.프론트 단에서 입력한 학번 정보를 가져온다.
		String num = request.getParameter("num");

		// 2.DB 연동.
		StudentVO stvo = new StudentVO();
		stvo.setNum(Integer.parseInt(num)); // 주입 시 정수형으로 형 변환.

		StudentDAO stdao = new StudentDAO();

		// 3. 학생 객체 받아오기.
		StudentVO getStvo = stdao.getStudent(stvo);

		// 4. 응답 화면 구성.

		PrintWriter out = response.getWriter();

		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
		out.println("    <title>학생 등록</title>");
		out.println("    <link rel=\"stylesheet\" href=\"./css/style.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println("    <button class=\"menu-button\" onclick=\"window.location.href='searchStudent.html';\">학생조회</button>");
		out.println("");
		out.println("    <h1>학생 조회</h1>");
		out.println("    <hr>");
		// studentList에 아무 값이 없으면 응답 화면을 따로 보여준다.
		if (getStvo != null) {
			// 셀 내용

			out.println("            <div class=\"card\">");
			out.println("                <img src=\"./images/profile.png\" alt=\"학생 사진\">");
			out.println("                <div class=\"card-content\">");
			out.println("                    <h2>" + getStvo.getName() + "</h2>");
			out.println("                    <p><strong>학번:</strong> " + getStvo.getNum() + "</p>");
			out.println("                    <p><strong>학과:</strong> " + getStvo.getMajor() + "</p>");
			out.println("                    <p><strong>전화번호:</strong> " + getStvo.getPhone() + "</p>");
			out.println("                </div>");
			out.println("            </div>");
			out.println("</div>");

		} else {
			out.println("<h2>검색한 학생이 없습니다.</h2>");
		}
		out.println("        </div>");
		out.println("    </div>");
		out.println("</body>");
		out.println("</html>");

	}

}
