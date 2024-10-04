package com.ssk.web.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssk.biz.admin.AdminDAO;
import com.ssk.biz.admin.AdminVO;

/**
 * Servlet implementation class insertAdminServlet
 */

@WebServlet(urlPatterns = "/insertAdmin.do")
public class InsertAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String encoding;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("InsertAdminServlet ==> /insertAdmin.do");

		// 2. 프론트에서 값 얻어냄.
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String count = request.getParameter("count");

		// 2. 해당 아이디의 중복 검사 실시.
		
		AdminVO advo = new AdminVO();
		advo.setId(id);
		advo.setPassword(password);
		advo.setName(name);
		advo.setCount(Integer.parseInt(count));
		
		AdminDAO adao = new AdminDAO();
		
		if(adao.getAdmin(advo)==null) {
			// 해당 아이디의 관리자가 없으면 회원가입 진행.
			adao.insertAdmin(advo);
			System.out.println("InsertAdminServlet ==> 관리자 회원 가입 완료.");
			// 4. 화면 이동
			response.sendRedirect("login.html");
		} else {
			// 아니라면 경고문구 후 뒤로가기.
			System.out.println("InsertAdminServlet ==> 관리자 회원 가입 미완료, 아이디 중복.");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('중복된 아이디 입니다.');");
			out.println("history.back();"); // 이전 페이지로 돌아가기
			out.println("</script>");
		}	
	}

}
