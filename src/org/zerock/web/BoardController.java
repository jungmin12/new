package org.zerock.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.anno.RequestMapping;
import org.zerock.dao.BoardDAO;
import org.zerock.dao.BoardDAOImpl;
import org.zerock.domain.Criteria;

/**
 * Servlet implementation class HelloController
 */
@WebServlet("/board/*")
public class BoardController extends AbstractController {
	private static final long serialVersionUID = 1L;
	BoardDAO dao = new BoardDAOImpl();

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

	@RequestMapping("/listSearch")
	public String listSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("BoardController list called");

		int page = Integer.parseInt(request.getParameter("page"));

		Criteria cri = new Criteria(page, request.getParameter("sType"), request.getParameter("keyword"));

		System.out.println("Page" + page);

		request.setAttribute("list", dao.listSearch(cri));

		// 토탈 카운트 가져온다
		int total = dao.getCountSearch(cri);

		request.setAttribute("pm", new PageMaker(page, total)); // PageMaker 자체를
		// 넣어버린다.

		return "list";
	}

	@RequestMapping(value = "/register", method = "POST")
	public String regPOST(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BoardController register called");

		return "success";
	}

	@RequestMapping("/view")
	public void view(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int bno = Integer.parseInt(request.getParameter("bno"));

		request.setAttribute("vo", dao.read(bno));

	}

	@RequestMapping(value = "/list", method = "POST")
	public void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int bno = Integer.parseInt(request.getParameter("bno"));
		System.out.println(bno);
		dao.delete(bno);

	}

}
