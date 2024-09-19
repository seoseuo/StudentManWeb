package com.ssk.web.admin;

import java.io.IOException;

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

		// 3. DB 연동 처리.
		AdminVO advo = new AdminVO();
		advo.setId(id);
		advo.setPassword(password);
		advo.setName(name);
		advo.setCount(Integer.parseInt(count));

		AdminDAO adao = new AdminDAO();
		adao.insertAdmin(advo);
		
		System.out.println("InsertAdminServlet ==> 관리자 회원 가입 완료.");

		// 4. 화면 이동
		response.sendRedirect("login.html");
	}

}
