package org.zerock.web;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.anno.RequestMapping;

import com.google.gson.Gson;

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
		// System.out.println(path);
		// System.out.println(contextName);

		String wantedPath = path.substring(contextName.length());

		System.out.println(wantedPath);

		String uri = request.getPathInfo();
		String callMethod = request.getMethod();

		System.out.println("URI:" + uri);

		String jspPath = PRE;

		try {

			Class clz = this.getClass();
			Method[] methods = clz.getDeclaredMethods();

			for (Method method : methods) {
				RequestMapping mapping = method.getAnnotation(RequestMapping.class);

				if (mapping == null) {
					continue;
				}

				if (uri.equals(mapping.value()) && callMethod.equals(mapping.method())) {

					Object obj = method.invoke(this, request, response);

					Class returnType = method.getReturnType();
					
					if (returnType == void.class) {
						System.out.println("return type is voi d");
						jspPath += wantedPath + APP;
					} else if(returnType == String.class) {

						String str = (String) obj;
								
						if (str.startsWith("redirect:")) {
							response.sendRedirect(str.substring(9));
							return;
						}

						wantedPath = wantedPath.substring(0, wantedPath.lastIndexOf("/"));

						System.out.println("return type is string");
						jspPath += wantedPath + "/" + (String) (obj) + APP;
					}else {
						
						if(obj == null){
							System.out.println("NULL DATA");
							return;
						}
						
						System.out.println("Make JSON Data");
			
						Gson gson = new Gson();
						
						String jsonStr= gson.toJson(obj);
						
						response.setContentType("application/json; charset=UTF-8"); 
						response.getOutputStream().write(jsonStr.getBytes("UTF-8"));
						
						return;
						
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
