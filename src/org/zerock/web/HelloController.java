package org.zerock.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.anno.RequestMapping;
import org.zerock.dao.TimeDAO;
import org.zerock.dao.TimeDAOImpl;

/**
 * Servlet implementation class HelloController
 */
@WebServlet("/hello/*")
public class HelloController extends AbstractController {
	private static final long serialVersionUID = 1L;
	private TimeDAO dao = new TimeDAOImpl();
	
	
	@RequestMapping("/hi")
	public void hi(HttpServletRequest request, HttpServletResponse response){
		System.out.println("HelloController hi called");
		try {
			request.setAttribute("now",dao.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/bye")
	public String bye(HttpServletRequest request, HttpServletResponse response){
		System.out.println("HelloController bye called");
		return "bye";
	}
	

}
