package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class patientAPI
 */
@WebServlet("/patientAPI")
public class patientAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	patient itemObj = new patient();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public patientAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = itemObj.insertPatient(request.getParameter("userName"), request.getParameter("password"),
				request.getParameter("mobile"), request.getParameter("email"), request.getParameter("address"));
		
		System.out.println("awilla inne");
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Map paras = getParasMap(request);
		
		String output = itemObj.updatePatient(paras.get("hidItemIDSave").toString(), paras.get("userName").toString(),
				paras.get("password").toString(), paras.get("mobile").toString(), paras.get("email").toString(), paras.get("address").toString());
		response.getWriter().write(output);
		
		

	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 Map paras = getParasMap(request);
		 String output = itemObj.deletePatient(paras.get("userId").toString());
		 response.getWriter().write(output); 
		 System.out.println("awilla inne servlet");
		
	}
	
	// Convert request parameters to a Map

	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		
		}
	 

}
