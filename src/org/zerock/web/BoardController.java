package org.zerock.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.anno.RequestMapping;
import org.zerock.dao.BoardDAO;
import org.zerock.dao.BoardDAOImpl;

/**
 * Servlet implementation class HelloController
 */
@WebServlet("/board/*")
public class BoardController extends AbstractController {
	private static final long serialVersionUID = 1L;
	BoardDAO dao = new BoardDAOImpl();

	// GET����̸� void�ص� ��
	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardController list called");

		int page = Integer.parseInt(request.getParameter("page"));

		request.setAttribute("list", dao.listPage(page));

		// ��Ż ī��Ʈ �����´�
		int total = dao.getCount(page);

		request.setAttribute("pm", new PageMaker(page, total)); // PageMaker
																// ��ü��
																// �־������.

	}

	@RequestMapping("/register")
	public void regGET(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardController register called");

	}

	@RequestMapping(value = "/register", method = "POST")
	public String regPOST(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardController register called");

		return "success";
	}

	@RequestMapping("/view")
	public void view(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int page = Integer.parseInt(request.getParameter("page"));

		request.setAttribute("list", page);
			
		System.out.println(page);
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		request.setAttribute("vo", dao.read(bno));

		

	}

}
