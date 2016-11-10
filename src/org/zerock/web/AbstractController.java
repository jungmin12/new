package org.zerock.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.anno.RequestMapping;

/**
 * Servlet implementation class AbstractController
 */

public abstract class AbstractController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String PRE = "/WEB-INF";
	private static final String APP = ".jsp";
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AbstractController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getRequestURI();
		String contextName = request.getContextPath();
		
//		System.out.println(contextName);
//		System.out.println(path);
		
		String wantedPath = path.substring(contextName.length()); //path���� contextName���̸�ŭ �߶�
		System.out.println(wantedPath);
		
		String uri = request.getPathInfo();
		String callMethod = request.getMethod(); // get or post

		System.out.println("URI : " + uri);
		
		String jspPath = PRE;


		try {
			Class clz = this.getClass(); // this�� �׻� �����ϴ� �༮�̴�.
			Method[] methods = clz.getDeclaredMethods(); // ��� �޼ҵ带 �ҷ��´�.

			for (Method method : methods) {
				RequestMapping mapping = method.getAnnotation(RequestMapping.class);
				if (mapping == null) {
					continue;
				}
				if (uri.equals(mapping.value()) && callMethod.equals(mapping.method())) {
					Object obj = method.invoke(this, request, response);
					
					if(obj == null){
						System.out.println("return type is void");
						jspPath += wantedPath+APP;
					}else{
						wantedPath = wantedPath.substring(0,wantedPath.lastIndexOf("/"));
						System.out.println("return type is String");

						jspPath += wantedPath + "/" + (String)(obj)+APP;
					}
					
					System.out.println(jspPath);
					request.getRequestDispatcher(jspPath).forward(request, response);
					break;

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}