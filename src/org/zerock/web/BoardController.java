package org.zerock.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.anno.RequestMapping;
import org.zerock.dao.BoardDAO;
import org.zerock.dao.BoardDAOImpl;
import org.zerock.domain.Criteria;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")
public class BoardController extends AbstractController {
	private static final long serialVersionUID = 1L;

	private BoardDAO dao = new BoardDAOImpl();

	@RequestMapping("/view")
	public void view(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setAttribute("vo", dao.read(Integer.parseInt(request.getParameter("bno"))));

	}

	@RequestMapping(value = "/delete", method = "POST")
	public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("���� ó��................");

		request.setAttribute("result", "DEL_SUCCESS");

		// return "success";

		return "redirect:list?page=1&result=s";

	}

	@RequestMapping("/list")
	public void list(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int page = Integer.parseInt(request.getParameter("page"));

		request.setAttribute("list", dao.listPage(page));

		int total = dao.getCount(page);

		request.setAttribute("pm", new PageMaker(page, total));

	}

	@RequestMapping("/listSearch")
	public String listSearch(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int page = Integer.parseInt(request.getParameter("page"));

		Criteria cri = new Criteria(page, request.getParameter("sType"), request.getParameter("keyword"));

		request.setAttribute("list", dao.listSearch(cri));

		int total = dao.getCountSearch(cri);

		request.setAttribute("pm", new PageMaker(page, total));

		return "list";
	}

	@RequestMapping("/regist")
	public void regGET(HttpServletRequest request, HttpServletResponse response) {

	}

	@RequestMapping(value = "/regist", method = "POST")
	public String regPOST(HttpServletRequest request, HttpServletResponse response) {

		System.out.println("......................regPOST");

		return "success";
	}

}
