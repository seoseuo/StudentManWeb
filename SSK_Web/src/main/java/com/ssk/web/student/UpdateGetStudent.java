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
 * Servlet implementation class searchStudentServlet
 */

@WebServlet("/updateGetStudent.do")
public class UpdateGetStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("UpdateGetStudent ==> /updateGetStudent.do");

			
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
		out.println("    <title>학생 관리 웹 - 정보 수정</title>");
		out.println("    <link rel=\"stylesheet\" href=\"./css/style.css\">");
		out.println("<script src=\"./js/insertStudent.js\"></script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println(
				"    <button class=\"menu-button\" onclick=\"window.location.href='updateGetStudent.html';\">정보 수정 검색</button>");
		out.println("");
		out.println("    <h1>정보 수정</h1>");
		out.println("    <hr>");
		// stvo 에 아무 값이 없으면 응답 화면을 따로 보여준다.
		if (getStvo != null) {
			// 셀 내용

			out.println("<form id=\"insertStudentForm\" method=\"post\" action=\"/updateStudent.do\">");
			out.println("    <label for=\"num\">학번 <span class=\"input_warn\" id=\"num_warn\"></span></label>");
			out.println("    <input type=\"text\" id=\"num\" name=\"num\" value=\"" + getStvo.getNum() + "\"  readonly>");
			out.println("    <label for=\"name\">이름 <span class=\"input_warn\" id=\"name_warn\"></span></label>");
			out.println(
					"    <input type=\"text\" id=\"name\" name=\"name\" value=\"" + getStvo.getName() + "\"  readonly>");
			out.println("    <label for=\"major\">학과 <span class=\"input_warn\" id=\"major_warn\"></span></label>");
			out.println("    <input type=\"text\" id=\"major\" name=\"major\" placeholder=\"전공을 입력하세요. 예시 : **학과\">");
			out.println("    <label for=\"phone\">전화번호 <span class=\"input_warn\" id=\"phone_warn\"></span></label>");
			out.println(
					"    <input type=\"text\" id=\"phone\" name=\"phone\" placeholder=\"전화번호를 입력하세요. 예시 : 010-****-****\">");
			out.println("    <input type=\"submit\" value=\"등록\">");
			out.println("</form>");
			System.out.println("UpdateGetStudent ==> 학생 조회 완료.");
		} else {
			out.println("<h2>검색한 학생이 없습니다.</h2>");
			System.out.println("UpdateGetStudent ==> 학생 조회 미완료, 검색 결과 없음.");
		}
		out.println("        </div>");
		out.println("    </div>");
		out.println("</body>");
		out.println("</html>");

	}

}
