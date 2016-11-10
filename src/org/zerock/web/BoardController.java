package org.zerock.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.anno.RequestMapping;
import org.zerock.dao.BoardDAO;
import org.zerock.dao.BoardDAOImpl;
import org.zerock.domain.BoardVO;
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

		dao.delete(Integer.parseInt(request.getParameter("bno")));
		System.out.println("DELETE SUCCESS...............");
		
		request.setAttribute("result", "DEL_SUCCESS");

//		 return "success";

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

		BoardVO vo = new BoardVO();
		
		try {
			
			vo.setTitle(request.getParameter("title"));
			vo.setContent(request.getParameter("content"));
			vo.setWriter(request.getParameter("writer"));
			dao.create(vo);
			System.out.println(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("......................regPOST");

//		request.setAttribute("result", "REG_SUCCESS");

//		 return "success";

		return "redirect:list?page=1&result=s";
		
	}
	
	@RequestMapping("/test")
	public Map<String, String> test(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, String> map = new HashMap();
		                                                                                                                                                                                
		map.put("id", "aaaa");
		map.put("pw", "bbbb");
		map.put("name", "한글한글");
		
		return map;
	}

}
