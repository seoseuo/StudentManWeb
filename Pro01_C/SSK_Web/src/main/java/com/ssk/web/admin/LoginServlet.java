package com.ssk.web.admin;

import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssk.biz.admin.AdminDAO;
import com.ssk.biz.admin.AdminVO;

/**
 * Servlet implementation class loginServlet
 */

@WebServlet("/login.do")

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("LoginServlet ==> /login.do");

		// 1. 관리자 입력 정보 추출
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		// 2. DB 연동 처리
		AdminVO loginAdvo = new AdminVO();
		loginAdvo.setId(id);
		

		// 로그인 확인 용 객체 생성.
		AdminDAO adao = new AdminDAO();
		AdminVO checkAdvo = adao.getAdmin(loginAdvo);

		// 3. 로그인 여부에 따른 응답 화면 구성

		// HTTP 응답 프로토콜 message-body와 연결된 출력 스트림 획득
		PrintWriter out = response.getWriter();

		if (checkAdvo != null) {
			if (checkAdvo.getPassword().equals(password)) { // 로그인 성공 시

				// 상태 정보를 세선에 저장
				HttpSession session = request.getSession();
				session.setAttribute("adminId", checkAdvo.getId());
				session.setAttribute("adminName", checkAdvo.getName());
				session.setAttribute("adminCount", checkAdvo.getCount());

				System.out.println("LoginServlet ==> " + "adminId : " + checkAdvo.getId());
				System.out.println("LoginServlet ==> " + "adminName : " + checkAdvo.getName());
				System.out.println("LoginServlet ==> " + "adminCount : " + checkAdvo.getCount());

				System.out.println("LoginServlet ==> 로그인 성공.");

				// 메인 화면으로 이동한다.
				response.sendRedirect("toMain.do");

			} else { // 로그인 실패 시.
				System.out.println("LoginServlet ==> 로그인 실패.");

				// 로그인 화면으로 다시 이동한다.
				out.println("<script>");
				out.println("alert('아이디 혹은 비밀번호가 틀렸습니다.');");
				out.println("history.back();"); // 이전 페이지로 돌아가기
				out.println("</script>");
			}
		} else { // 회원 정보가 없을
			// 로그인 화면으로 다시 이동한다.
			out.println("<script>");
			out.println("alert('회원 정보가 없습니다.');");
			out.println("history.back();"); // 이전 페이지로 돌아가기
			out.println("</script>");
		}
	}

}
