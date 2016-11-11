package org.zerock.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.anno.RequestMapping;

@WebServlet("/reply/*")
public class ReplyController extends AbstractController {

	@RequestMapping("/test")
	public List<String> getTest(HttpServletRequest request, HttpServletResponse response){
	
		List<String> list = new ArrayList<>();
		
		for(int i = 0; i < 10; i++){
			list.add("AAA" + i);
			
		}
		
		return list;
	
	}
	//이런 코드를 만나면 야근....
	@RequestMapping(value = "/add", method = "POST")
	public List<String> add(HttpServletRequest request, HttpServletResponse response){
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String reply = request.getParameter("reply");
		String replyer = request.getParameter("replyer");
		
		System.out.println("--------추가내용 시작-------------");
		System.out.println(bno);
		System.out.println(reply);
		System.out.println(replyer);
		System.out.println("---------추가내용 끝------------");
		
		
		List<String> list = new ArrayList<>();
		
		for(int i = 0; i < 10; i++){
			list.add("AAA" + i);
			
		}
		
		return list;
		
		
	}
	
	
}
